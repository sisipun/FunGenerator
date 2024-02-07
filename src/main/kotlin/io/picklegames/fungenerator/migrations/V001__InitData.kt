package io.picklegames.fungenerator.migrations

import ac.simons.neo4j.migrations.core.JavaBasedMigration
import ac.simons.neo4j.migrations.core.MigrationContext
import io.picklegames.fungenerator.entity.Nodes
import io.picklegames.fungenerator.entity.Relations
import org.neo4j.cypherdsl.core.Cypher
import org.neo4j.cypherdsl.core.Node
import kotlin.random.Random

class V001__InitData : JavaBasedMigration {

    override fun apply(context: MigrationContext?) {
        context?.session.use { session ->
            val users: MutableSet<Node> = mutableSetOf()
            for (i in 1..50000) {
                val name = "user_$i"
                val user = Cypher.node(Nodes.USER)
                    .withProperties("name", Cypher.literalOf<String>(name))
                    .named(name)
                users.add(user)
                session?.run(Cypher.create(user).build().cypher)
            }

            val companies: MutableSet<Node> = mutableSetOf()
            for (i in 1..50000) {
                val name = "company_$i"
                val company = Cypher.node(Nodes.COMPANY)
                    .withProperties("name", Cypher.literalOf<String>(name))
                    .named(name)
                companies.add(company)
                session?.run(Cypher.create(company).build().cypher)
            }

            val tags: MutableSet<Node> = mutableSetOf()
            for (i in 1..50000) {
                val name = "tags_$i"
                val tag = Cypher.node(Nodes.TAG)
                    .withProperties("name", Cypher.literalOf<String>(name))
                    .named(name)
                tags.add(tag)
                session?.run(Cypher.create(tag).build().cypher)
            }

            val games: MutableSet<Node> = mutableSetOf()
            for (i in 1..50000) {
                val name = "game_$i"
                val game = Cypher.node(Nodes.GAME)
                    .withProperties("name", Cypher.literalOf<String>(name))
                    .named(name)
                games.add(game)
                session?.run(Cypher.create(game).build().cypher)
            }

            for (i in 1..5000) {
                val gameId: Int = Random.nextInt(1, 50000)
                val companyId: Int = Random.nextInt(1, 50000)
                val game: Node = Cypher.node(Nodes.GAME).named("g")
                val company: Node = Cypher.node(Nodes.COMPANY).named("c")
                val relation = Cypher
                    .match(game)
                    .where(game.property("name").eq(Cypher.literalOf<String>("game_$gameId")))
                    .match(company)
                    .where(company.property("name").eq(Cypher.literalOf<String>("company_$companyId")))
                    .create(
                        Cypher.anyNode("g").relationshipFrom(Cypher.anyNode("c"), Relations.CREATED)
                    )
                session?.run(relation.build().cypher)
            }

            for (i in 1..5000) {
                val gameId: Int = Random.nextInt(1, 50000)
                val tagId: Int = Random.nextInt(1, 50000)
                val game: Node = Cypher.node(Nodes.GAME).named("g")
                val tag: Node = Cypher.node(Nodes.TAG).named("t")
                val relation = Cypher
                    .match(game)
                    .where(game.property("name").eq(Cypher.literalOf<String>("game_$gameId")))
                    .match(tag)
                    .where(tag.property("name").eq(Cypher.literalOf<String>("tag_$tagId")))
                    .create(
                        Cypher.anyNode("g").relationshipTo(Cypher.anyNode("t"), Relations.HAS)
                    )
                session?.run(relation.build().cypher)
            }

            for (i in 1..5000) {
                val gameId: Int = Random.nextInt(1, 50000)
                val userId: Int = Random.nextInt(1, 50000)
                val game: Node = Cypher.node(Nodes.GAME).named("g")
                val user: Node = Cypher.node(Nodes.USER).named("u")
                val relation = Cypher
                    .match(game)
                    .where(game.property("name").eq(Cypher.literalOf<String>("game_$gameId")))
                    .match(user)
                    .where(user.property("user").eq(Cypher.literalOf<String>("user_$userId")))
                    .create(
                        Cypher.anyNode("g").relationshipFrom(Cypher.anyNode("u"), Relations.LIKES)
                    )
                session?.run(relation.build().cypher)
            }

            for (i in 1..5000) {
                val gameId: Int = Random.nextInt(1, 50000)
                val userId: Int = Random.nextInt(1, 50000)
                val game: Node = Cypher.node(Nodes.GAME).named("g")
                val user: Node = Cypher.node(Nodes.USER).named("u")
                val relation = Cypher
                    .match(game)
                    .where(game.property("name").eq(Cypher.literalOf<String>("game_$gameId")))
                    .match(user)
                    .where(user.property("user").eq(Cypher.literalOf<String>("user_$userId")))
                    .create(
                        Cypher.anyNode("g").relationshipFrom(Cypher.anyNode("u"), Relations.HAS)
                    )
                session?.run(relation.build().cypher)
            }
        }
    }
}