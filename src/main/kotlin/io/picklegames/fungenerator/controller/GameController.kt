package io.picklegames.fungenerator.controller

import io.picklegames.fungenerator.dto.CreateGameRequest
import io.picklegames.fungenerator.dto.GameDto
import io.picklegames.fungenerator.dto.RateRequest
import io.picklegames.fungenerator.entity.Game
import io.picklegames.fungenerator.service.GameService
import io.picklegames.fungenerator.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/games")
class GameController(
    private val service: GameService,
    private val userService: UserService
) {

    @GetMapping
    fun getAll(): List<GameDto> = service.getAll().map { GameDto(it.id!!, it.name) }

    @PostMapping
    fun create(@Valid @RequestBody request: CreateGameRequest): GameDto {
        val game = service.create(request)
        return GameDto(game.id!!, game.name)
    }

    @PostMapping("{id}/like")
    fun like(@PathVariable id: Long): GameDto {
        val game: Game = service.like(id, userService.get(0));
        return GameDto(game.id!!, game.name)
    }

    @PostMapping("{id}/rate")
    fun rate(@PathVariable id: Long, @Valid @RequestBody request: RateRequest): GameDto {
        val game: Game = service.rate(id, userService.get(0), request);
        return GameDto(game.id!!, game.name)
    }
}