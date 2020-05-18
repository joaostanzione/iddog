package com.joaostanzione.iddog.data.dogs

import com.joaostanzione.iddog.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DogsRemoteRepository(
    private val api: Api
) : DogsRepository {

    override suspend fun getDogs(category: String, token: String?): DogsResponse = withContext(Dispatchers.IO) {
        api.getDogs(category, token)
    }
}
