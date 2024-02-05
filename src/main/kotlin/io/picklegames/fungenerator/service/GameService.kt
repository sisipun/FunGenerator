package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.dto.RateRequest
import io.picklegames.fungenerator.entity.Game
import io.picklegames.fungenerator.entity.User

interface GameService {

    fun getAll(): List<Game>

    fun get(id: Long): Game

    fun like(id: Long, user: User): Game

    fun rate(id: Long, user: User, request: RateRequest): Game
}