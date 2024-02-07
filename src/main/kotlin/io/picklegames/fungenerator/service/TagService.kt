package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.entity.Tag

interface TagService {

    fun getAll(): List<Tag>

    fun get(id: Long): Tag
}