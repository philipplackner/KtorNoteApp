package com.androiddevs.ktornoteapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.androiddevs.ktornoteapp.data.local.NotesDatabase
import com.androiddevs.ktornoteapp.data.remote.BasicAuthInterceptor
import com.androiddevs.ktornoteapp.data.remote.NoteApi
import com.androiddevs.ktornoteapp.other.Constants.BASE_URL
import com.androiddevs.ktornoteapp.other.Constants.DATABASE_NAME
import com.androiddevs.ktornoteapp.other.Constants.ENCRYPTED_SHARED_PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, NotesDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNoteDao(db: NotesDatabase) = db.noteDao()

    @Singleton
    @Provides
    fun provideBasicAuthInterceptor() = BasicAuthInterceptor()

    @Singleton
    @Provides
    fun provideNoteApi(
        basicAuthInterceptor: BasicAuthInterceptor
    ) : NoteApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(basicAuthInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NoteApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_SHARED_PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}















