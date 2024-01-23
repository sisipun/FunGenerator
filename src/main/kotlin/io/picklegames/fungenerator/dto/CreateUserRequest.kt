package io.picklegames.fungenerator.dto

import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(
    @field:NotBlank var name: String
)