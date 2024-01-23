package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.dto.CreateGameRequest
import io.picklegames.fungenerator.entity.Game
import io.picklegames.fungenerator.entity.User
import io.picklegames.fungenerator.exception.NotFoundException
import io.picklegames.fungenerator.repository.GameRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DefaultGameService(
    private val repository: GameRepository
) : GameService {

    override fun getAll(): List<Game> = repository.findAll()

    override fun get(id: Long): Game = repository
        .findById(id)
        .orElseThrow { NotFoundException("Can't find gake with id $id") }

    @Transactional
    override fun create(request: CreateGameRequest): Game = repository.save(Game(request.name))

    @Transactional
    override fun like(id: Long, user: User): Game {
        val game: Game = get(id)
        game.likes.add(user)
        return repository.save(game)
    }
}