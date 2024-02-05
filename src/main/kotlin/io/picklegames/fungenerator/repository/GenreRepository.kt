package io.picklegames.fungenerator.repository

import io.picklegames.fungenerator.entity.Genre
import org.springframework.data.neo4j.repository.Neo4jRepository

interface GenreRepository : Neo4jRepository<Genre, Long>