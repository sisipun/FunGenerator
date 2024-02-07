package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.entity.Tag
import io.picklegames.fungenerator.exception.NotFoundException
import io.picklegames.fungenerator.repository.TagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DefaultTagService(
    private val repository: TagRepository
) : TagService {

    override fun getAll(): List<Tag> = repository.findAll()

    override fun get(id: Long): Tag = repository
        .findById(id)
        .orElseThrow { NotFoundException("Can't find tag with id $id") }
}