package com.joaostanzione.iddog.domain.dogs

import com.joaostanzione.iddog.data.dogs.DogsRepository

internal class DogsUseCase(private val dogsRepository: DogsRepository) {
    suspend fun getDogs(category: String, token: String?): List<String>? = dogsRepository.getDogs(category, token).dogsPhotos
}
