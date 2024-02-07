package io.picklegames.fungenerator.repository

import io.picklegames.fungenerator.dto.SearchGamesRequest
import io.picklegames.fungenerator.entity.Game
import io.picklegames.fungenerator.entity.Nodes
import io.picklegames.fungenerator.entity.Relations
import org.neo4j.cypherdsl.core.*
import org.springframework.data.neo4j.core.Neo4jTemplate

class GameCustomRepositoryImpl(
    private val template: Neo4jTemplate
) : GameCustomRepository {

    override fun search(request: SearchGamesRequest): List<Game> {
        val game: Node = Cypher.node(Nodes.GAME).named("g")
        var condition: Condition = Conditions.noCondition()
        val relationships: MutableSet<Relationship> = mutableSetOf()

        if (request.name != null) {
            condition = condition.and(
                game.property("name").contains(Cypher.literalOf<String>(request.name))
            )
        }
        if (request.tagId != null) {
            val tag = Cypher.node(Nodes.TAG).named("tag")
            relationships.add(game.relationshipTo(tag, Relations.HAS))
            condition = condition.and(
                tag.elementId().eq(Cypher.literalOf<String>(request.tagId.toString()))
            )
        }
        if (request.companyId != null) {
            val company = Cypher.node(Nodes.COMPANY).named("comp")
            relationships.add(game.relationshipFrom(company, Relations.CREATED))
            condition = condition.and(
                company.elementId().eq(Cypher.literalOf<String>(request.companyId.toString()))
            )
        }

        val match = if (relationships.isEmpty()) Cypher.match(game) else Cypher.match(relationships)
        return template.findAll(match.where(condition).returning("g").build().cypher, Game::class.java)
    }
}