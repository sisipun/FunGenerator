package io.picklegames.fungenerator.repository

import io.picklegames.fungenerator.entity.User
import org.springframework.data.neo4j.repository.Neo4jRepository

interface UserRepository : Neo4jRepository<User, Long>