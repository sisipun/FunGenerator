package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.dto.CreateUserRequest
import io.picklegames.fungenerator.entity.User

interface UserService {

    fun get(id: Long): User

    fun create(request: CreateUserRequest): User
}