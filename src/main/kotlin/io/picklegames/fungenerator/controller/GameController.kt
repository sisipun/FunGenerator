package io.picklegames.fungenerator.controller

import io.picklegames.fungenerator.dto.GameDto
import io.picklegames.fungenerator.dto.RateRequest
import io.picklegames.fungenerator.dto.SearchGamesRequest
import io.picklegames.fungenerator.entity.Game
import io.picklegames.fungenerator.service.GameService
import io.picklegames.fungenerator.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/games")
class GameController(
    private val service: GameService,
    private val userService: UserService
) {

    @PostMapping("/search")
    fun search(
        @Valid @RequestBody request: SearchGamesRequest
    ): List<GameDto> = service.search(request).map { GameDto(it.id!!, it.name) }

    @PostMapping("{id}/like/{userId}")
    fun like(@PathVariable id: Long, @PathVariable userId: Long): GameDto {
        val game: Game = service.like(id, userService.get(userId));
        return GameDto(game.id!!, game.name)
    }

    @PostMapping("{id}/rate")
    fun rate(@PathVariable id: Long, @Valid @RequestBody request: RateRequest): GameDto {
        val game: Game = service.rate(id, userService.get(0), request);
        return GameDto(game.id!!, game.name)
    }
}