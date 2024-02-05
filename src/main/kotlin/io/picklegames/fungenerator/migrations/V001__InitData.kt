package io.picklegames.fungenerator.migrations

import ac.simons.neo4j.migrations.core.JavaBasedMigration
import ac.simons.neo4j.migrations.core.MigrationContext
import org.neo4j.cypherdsl.core.Cypher
import org.neo4j.cypherdsl.core.Node

class V001__InitData : JavaBasedMigration {

    override fun apply(context: MigrationContext?) {
        context?.session.use { ;
            val alex: Node = Cypher.node("User")
                .withProperties("name", Cypher.literalOf<String>("Alex"))
                .named("alex")
            val max: Node = Cypher.node("User")
                .withProperties("name", Cypher.literalOf<String>("Max"))
                .named("max")
            val john: Node = Cypher.node("User")
                .withProperties("name", Cypher.literalOf<String>("John"))
                .named("john")

            val ea: Node = Cypher.node("Company")
                .withProperties("name", Cypher.literalOf<String>("Electronic Arts"))
                .named("ea")
            val ubisoft: Node = Cypher.node("Company")
                .withProperties("name", Cypher.literalOf<String>("Ubisoft"))
                .named("ubisoft")
            val cdProjectRed: Node = Cypher.node("Company")
                .withProperties("name", Cypher.literalOf<String>("Cd Project Red"))
                .named("cdProjectRed")

            val shooter: Node = Cypher.node("Genre")
                .withProperties("name", Cypher.literalOf<String>("Shooter"))
                .named("shooter")
            val action: Node = Cypher.node("Genre")
                .withProperties("name", Cypher.literalOf<String>("Action"))
                .named("action")
            val rpg: Node = Cypher.node("Genre")
                .withProperties("name", Cypher.literalOf<String>("RPG"))
                .named("rpg")

            val witcher: Node = Cypher.node("Game")
                .withProperties("name", Cypher.literalOf<String>("Witcher"))
                .named("witcher")
            val battlefield: Node = Cypher.node("Game")
                .withProperties("name", Cypher.literalOf<String>("Battlefield"))
                .named("battlefield")
            val assassinsCreed: Node = Cypher.node("Game")
                .withProperties("name", Cypher.literalOf<String>("Assassins Creed"))
                .named("assassinsCreed")
            val massEffect: Node = Cypher.node("Game")
                .withProperties("name", Cypher.literalOf<String>("Mass Effect"))
                .named("massEffect")

            val query = setOf(
                alex,
                max,
                john,
                ea,
                ubisoft,
                cdProjectRed,
                shooter,
                action,
                rpg,
                witcher,
                battlefield,
                assassinsCreed,
                massEffect,
                Cypher.anyNode("battlefield").relationshipFrom(Cypher.anyNode("ea"), "CREATED"),
                Cypher.anyNode("assassinsCreed").relationshipFrom(Cypher.anyNode("ubisoft"), "CREATED"),
                Cypher.anyNode("massEffect").relationshipFrom(Cypher.anyNode("ea"), "CREATED"),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("cdProjectRed`"), "CREATED"),
                Cypher.anyNode("witcher").relationshipTo(Cypher.anyNode("action"), "IS"),
                Cypher.anyNode("witcher").relationshipTo(Cypher.anyNode("rpg"), "IS"),
                Cypher.anyNode("battlefield").relationshipTo(Cypher.anyNode("shooter"), "IS"),
                Cypher.anyNode("assassinsCreed").relationshipTo(Cypher.anyNode("action"), "IS"),
                Cypher.anyNode("massEffect").relationshipTo(Cypher.anyNode("action"), "IS"),
                Cypher.anyNode("massEffect").relationshipTo(Cypher.anyNode("rpg"), "IS"),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("john"), "LIKES"),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("alex"), "LIKES"),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("max"), "LIKES"),
                Cypher.anyNode("battlefield").relationshipFrom(Cypher.anyNode("max"), "LIKES"),
                Cypher.anyNode("assassinsCreed").relationshipFrom(Cypher.anyNode("john"), "LIKES"),
                Cypher.anyNode("massEffect").relationshipFrom(Cypher.anyNode("alex"), "LIKES"),
                Cypher.anyNode("massEffect").relationshipFrom(Cypher.anyNode("john"), "LIKES"),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("john"), "HAS"),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("max"), "HAS"),
                Cypher.anyNode("battlefield").relationshipFrom(Cypher.anyNode("max"), "HAS"),
                Cypher.anyNode("battlefield").relationshipFrom(Cypher.anyNode("john"), "HAS"),
                Cypher.anyNode("assassinsCreed").relationshipFrom(Cypher.anyNode("alex"), "HAS"),
                Cypher.anyNode("assassinsCreed").relationshipFrom(Cypher.anyNode("john"), "HAS"),
                Cypher.anyNode("massEffect").relationshipFrom(Cypher.anyNode("john"), "HAS")
            ).joinToString("\n") { Cypher.create(it).build().cypher }

            it?.run(query)
        }
    }
}