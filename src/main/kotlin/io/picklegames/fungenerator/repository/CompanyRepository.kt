package io.picklegames.fungenerator.repository

import io.picklegames.fungenerator.entity.Company
import io.picklegames.fungenerator.entity.Game
import org.springframework.data.neo4j.repository.Neo4jRepository

interface CompanyRepository : Neo4jRepository<Company, Long>