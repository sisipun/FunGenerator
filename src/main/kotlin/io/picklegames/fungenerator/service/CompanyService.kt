package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.entity.Company

interface CompanyService {

    fun getAll(): List<Company>

    fun get(id: Long): Company
}