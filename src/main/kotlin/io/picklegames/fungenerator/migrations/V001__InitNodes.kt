package io.picklegames.fungenerator.migrations

import ac.simons.neo4j.migrations.core.JavaBasedMigration
import ac.simons.neo4j.migrations.core.MigrationContext
import io.picklegames.fungenerator.entity.Nodes
import org.neo4j.cypherdsl.core.Cypher
import org.neo4j.driver.Session

const val NODES_COUNT = 2000

class V001__InitNodes : JavaBasedMigration {

    override fun apply(context: MigrationContext?) {
        context?.session.use { session ->
            for (i in 1..NODES_COUNT) {
                createEntity("user_$i", Nodes.USER, session)
            }

            for (i in 1..NODES_COUNT) {
                createEntity("company_$i", Nodes.COMPANY, session)
            }

            for (i in 1..NODES_COUNT) {
                createEntity("tag_$i", Nodes.TAG, session)
            }

            for (i in 1..NODES_COUNT) {
                createEntity("game_$i", Nodes.GAME, session)
            }

            for (i in 1..NODES_COUNT) {
                createEntity("review_$i", Nodes.REVIEW, session)
            }

            for (i in 1..NODES_COUNT) {
                createEntity("comment_$i", Nodes.COMMENT, session)
            }
        }
    }

    private fun createEntity(name: String, nodeType: String, session: Session?) {
        val node = Cypher.node(nodeType)
            .withProperties("name", Cypher.literalOf<String>(name))
            .named(name)
        session?.run(Cypher.create(node).build().cypher)
    }
}