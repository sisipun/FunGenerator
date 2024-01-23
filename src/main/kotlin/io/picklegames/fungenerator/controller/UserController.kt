package io.picklegames.fungenerator.controller

import io.picklegames.fungenerator.dto.CreateUserRequest
import io.picklegames.fungenerator.dto.UserDto
import io.picklegames.fungenerator.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {

    @PostMapping
    fun create(@Valid @RequestBody request: CreateUserRequest): UserDto {
        val game = service.create(request)
        return UserDto(game.id!!, game.name)
    }
}