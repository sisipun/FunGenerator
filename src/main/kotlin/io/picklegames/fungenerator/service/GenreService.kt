package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.entity.Genre

interface GenreService {

    fun getAll(): List<Genre>

    fun get(id: Long): Genre
}