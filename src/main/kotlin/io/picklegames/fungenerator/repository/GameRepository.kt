package io.picklegames.fungenerator.repository

import io.picklegames.fungenerator.entity.Game
import org.springframework.data.neo4j.repository.Neo4jRepository

interface GameRepository : Neo4jRepository<Game, Long>