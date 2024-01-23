package io.picklegames.fungenerator.dto

import jakarta.validation.constraints.NotBlank

data class CreateGameRequest(
    @field:NotBlank var name: String
)