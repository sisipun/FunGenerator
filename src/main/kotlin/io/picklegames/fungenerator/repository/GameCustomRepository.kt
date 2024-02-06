package io.picklegames.fungenerator.repository

import io.picklegames.fungenerator.dto.SearchGamesRequest
import io.picklegames.fungenerator.entity.Game

interface GameCustomRepository {

    fun search(request: SearchGamesRequest): List<Game>
}