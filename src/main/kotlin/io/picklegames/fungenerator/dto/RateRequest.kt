package io.picklegames.fungenerator.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.jetbrains.annotations.NotNull

data class RateRequest(
    @field:NotNull @field:Min(1) @field:Max(10) val value: Int
)