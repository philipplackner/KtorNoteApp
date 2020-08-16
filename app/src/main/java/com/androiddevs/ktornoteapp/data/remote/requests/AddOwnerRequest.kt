package com.androiddevs.ktornoteapp.data.remote.requests

data class AddOwnerRequest(
    val owner: String,
    val noteID: String
)