package io.picklegames.fungenerator.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateGameRequest(
    @field:NotBlank var name: String,
    @field:NotNull var genreId: Long,
    @field:NotNull var companyId: Long,
)