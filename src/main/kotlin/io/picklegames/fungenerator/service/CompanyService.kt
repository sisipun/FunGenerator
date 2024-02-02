package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.dto.CreateGameRequest
import io.picklegames.fungenerator.dto.CreateUserRequest
import io.picklegames.fungenerator.dto.RateRequest
import io.picklegames.fungenerator.entity.Company
import io.picklegames.fungenerator.entity.Game
import io.picklegames.fungenerator.entity.User

interface CompanyService {

    fun getAll(): List<Company>

    fun get(id: Long): Company
}