package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.dto.CreateGameRequest
import io.picklegames.fungenerator.dto.CreateUserRequest
import io.picklegames.fungenerator.entity.Game
import io.picklegames.fungenerator.entity.User

interface GameService {

    fun getAll(): List<Game>

    fun get(id: Long): Game

    fun create(request: CreateGameRequest): Game

    fun like(id: Long, user: User): Game
}