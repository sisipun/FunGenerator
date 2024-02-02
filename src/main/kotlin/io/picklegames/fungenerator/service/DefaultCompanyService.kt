package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.entity.Company
import io.picklegames.fungenerator.exception.NotFoundException
import io.picklegames.fungenerator.repository.CompanyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DefaultCompanyService(
    private val repository: CompanyRepository
) : CompanyService {

    override fun getAll(): List<Company> = repository.findAll()

    override fun get(id: Long): Company = repository
        .findById(id)
        .orElseThrow { NotFoundException("Can't find company with id $id") }
}