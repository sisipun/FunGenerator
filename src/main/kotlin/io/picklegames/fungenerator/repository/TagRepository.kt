package io.picklegames.fungenerator.repository

import io.picklegames.fungenerator.entity.Tag
import org.springframework.data.neo4j.repository.Neo4jRepository

interface TagRepository : Neo4jRepository<Tag, Long>