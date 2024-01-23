package io.picklegames.fungenerator.service

import io.picklegames.fungenerator.dto.CreateUserRequest
import io.picklegames.fungenerator.entity.User
import io.picklegames.fungenerator.exception.NotFoundException
import io.picklegames.fungenerator.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class DefaultUserService(
    private val repository: UserRepository
): UserService {

    override fun get(id: Long): User = repository
        .findById(id)
        .orElseThrow { NotFoundException("Can't find user with id $id") }

    @Transactional
    override fun create(request: CreateUserRequest): User = repository.save(User(request.name))
}