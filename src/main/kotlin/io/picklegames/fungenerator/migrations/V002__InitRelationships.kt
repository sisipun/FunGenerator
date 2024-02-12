package io.picklegames.fungenerator.migrations

import ac.simons.neo4j.migrations.core.JavaBasedMigration
import ac.simons.neo4j.migrations.core.MigrationContext
import io.picklegames.fungenerator.entity.Nodes
import io.picklegames.fungenerator.entity.Relations
import org.neo4j.cypherdsl.core.Cypher
import org.neo4j.cypherdsl.core.Node
import org.neo4j.driver.Session
import kotlin.random.Random

class V002__InitRelationships : JavaBasedMigration {

    override fun apply(context: MigrationContext?) {
        context?.session.use { session ->
            for (i in 1..NODES_COUNT) {
                for (j in 1..Random.nextInt(1, 3)) {
                    val gameId: Int = Random.nextInt(1, NODES_COUNT)
                    createRelation(
                        Nodes.COMPANY,
                        "company_$i",
                        Nodes.GAME,
                        "game_$gameId",
                        Relations.CREATED,
                        session
                    )
                }
            }

            for (i in 1..NODES_COUNT) {
                for (j in 1..Random.nextInt(1, 5)) {
                    val tagId: Int = Random.nextInt(1, NODES_COUNT)
                    createRelation(
                        Nodes.GAME,
                        "game_$i",
                        Nodes.TAG,
                        "tag_$tagId",
                        Relations.HAS,
                        session
                    )
                }
            }

            for (i in 1..NODES_COUNT) {
                for (j in 1..Random.nextInt(1, 10)) {
                    val gameId: Int = Random.nextInt(1, NODES_COUNT)
                    createRelation(
                        Nodes.USER,
                        "user_$i",
                        Nodes.GAME,
                        "game_$gameId",
                        Relations.LIKES,
                        session
                    )
                }
            }

            for (i in 1..NODES_COUNT) {
                for (j in 1..Random.nextInt(1, 6)) {
                    val gameId: Int = Random.nextInt(1, NODES_COUNT)
                    createRelation(
                        Nodes.USER,
                        "user_$i",
                        Nodes.GAME,
                        "game_$gameId",
                        Relations.HAS,
                        session
                    )
                }
            }

            for (i in 1..NODES_COUNT) {
                val userId: Int = Random.nextInt(1, NODES_COUNT)
                val gameId: Int = Random.nextInt(1, NODES_COUNT)
                createRelation(
                    Nodes.USER,
                    "user_$userId",
                    Nodes.REVIEW,
                    "review_$i",
                    Relations.CREATED,
                    session
                )
                createRelation(
                    Nodes.REVIEW,
                    "review_$i",
                    Nodes.GAME,
                    "game_$gameId",
                    Relations.TO,
                    session
                )
            }

            for (i in 1..NODES_COUNT) {
                val userId: Int = Random.nextInt(1, NODES_COUNT)
                val reviewId: Int = Random.nextInt(1, NODES_COUNT)
                createRelation(
                    Nodes.USER,
                    "user_$userId",
                    Nodes.COMMENT,
                    "comment_$i",
                    Relations.CREATED,
                    session
                )
                createRelation(
                    Nodes.COMMENT,
                    "comment_$i",
                    Nodes.REVIEW,
                    "review_$reviewId",
                    Relations.TO,
                    session
                )
            }
        }
    }

    private fun createRelation(
        fromType: String,
        fromName: String,
        toType: String,
        toName: String,
        relationType: String,
        session: Session?
    ) {
        val from: Node = Cypher.node(fromType)
            .named("f")
            .withProperties("name", Cypher.literalOf<String>(fromName))
        val to: Node = Cypher.node(toType)
            .named("t")
            .withProperties("name", Cypher.literalOf<String>(toName))
        val relation = Cypher.match(to)
            .match(from)
            .create(to.relationshipFrom(from, relationType))
        session?.run(relation.build().cypher)
    }
}