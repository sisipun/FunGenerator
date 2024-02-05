package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.entity.Genre
import io.picklegames.fungenerator.exception.NotFoundException
import io.picklegames.fungenerator.repository.GenreRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DefaultGenreService(
    private val repository: GenreRepository
) : GenreService {

    override fun getAll(): List<Genre> = repository.findAll()

    override fun get(id: Long): Genre = repository
        .findById(id)
        .orElseThrow { NotFoundException("Can't find genre with id $id") }
}