package com.joaostanzione.iddog.data.dogs

internal interface DogsRepository {

    suspend fun getDogs(category: String, token: String?): DogsResponse
}
