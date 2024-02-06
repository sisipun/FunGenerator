package io.picklegames.fungenerator.migrations

import ac.simons.neo4j.migrations.core.JavaBasedMigration
import ac.simons.neo4j.migrations.core.MigrationContext
import io.picklegames.fungenerator.entity.Nodes
import io.picklegames.fungenerator.entity.Relations
import org.neo4j.cypherdsl.core.Cypher
import org.neo4j.cypherdsl.core.Node

class V001__InitData : JavaBasedMigration {

    override fun apply(context: MigrationContext?) {
        context?.session.use {session -> ;
            val alex: Node = Cypher.node(Nodes.USER)
                .withProperties("name", Cypher.literalOf<String>("Alex"))
                .named("alex")
            val max: Node = Cypher.node(Nodes.USER)
                .withProperties("name", Cypher.literalOf<String>("Max"))
                .named("max")
            val john: Node = Cypher.node(Nodes.USER)
                .withProperties("name", Cypher.literalOf<String>("John"))
                .named("john")

            val ea: Node = Cypher.node(Nodes.COMPANY)
                .withProperties("name", Cypher.literalOf<String>("Electronic Arts"))
                .named("ea")
            val ubisoft: Node = Cypher.node(Nodes.COMPANY)
                .withProperties("name", Cypher.literalOf<String>("Ubisoft"))
                .named("ubisoft")
            val cdProjectRed: Node = Cypher.node(Nodes.COMPANY)
                .withProperties("name", Cypher.literalOf<String>("Cd Project Red"))
                .named("cdProjectRed")

            val shooter: Node = Cypher.node(Nodes.GENRE)
                .withProperties("name", Cypher.literalOf<String>("Shooter"))
                .named("shooter")
            val action: Node = Cypher.node(Nodes.GENRE)
                .withProperties("name", Cypher.literalOf<String>("Action"))
                .named("action")
            val rpg: Node = Cypher.node(Nodes.GENRE)
                .withProperties("name", Cypher.literalOf<String>("RPG"))
                .named("rpg")

            val witcher: Node = Cypher.node(Nodes.GAME)
                .withProperties("name", Cypher.literalOf<String>("Witcher"))
                .named("witcher")
            val battlefield: Node = Cypher.node(Nodes.GAME)
                .withProperties("name", Cypher.literalOf<String>("Battlefield"))
                .named("battlefield")
            val assassinsCreed: Node = Cypher.node(Nodes.GAME)
                .withProperties("name", Cypher.literalOf<String>("Assassins Creed"))
                .named("assassinsCreed")
            val massEffect: Node = Cypher.node(Nodes.GAME)
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
                Cypher.anyNode("battlefield").relationshipFrom(Cypher.anyNode("ea"), Relations.CREATED),
                Cypher.anyNode("assassinsCreed").relationshipFrom(Cypher.anyNode("ubisoft"), Relations.CREATED),
                Cypher.anyNode("massEffect").relationshipFrom(Cypher.anyNode("ea"), Relations.CREATED),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("cdProjectRed`"), Relations.CREATED),
                Cypher.anyNode("witcher").relationshipTo(Cypher.anyNode("action"), Relations.IS),
                Cypher.anyNode("witcher").relationshipTo(Cypher.anyNode("rpg"), Relations.IS),
                Cypher.anyNode("battlefield").relationshipTo(Cypher.anyNode("shooter"), Relations.IS),
                Cypher.anyNode("assassinsCreed").relationshipTo(Cypher.anyNode("action"), Relations.IS),
                Cypher.anyNode("massEffect").relationshipTo(Cypher.anyNode("action"), Relations.IS),
                Cypher.anyNode("massEffect").relationshipTo(Cypher.anyNode("rpg"), Relations.IS),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("john"), Relations.LIKES),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("alex"), Relations.LIKES),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("max"), Relations.LIKES),
                Cypher.anyNode("battlefield").relationshipFrom(Cypher.anyNode("max"), Relations.LIKES),
                Cypher.anyNode("assassinsCreed").relationshipFrom(Cypher.anyNode("john"), Relations.LIKES),
                Cypher.anyNode("massEffect").relationshipFrom(Cypher.anyNode("alex"), Relations.LIKES),
                Cypher.anyNode("massEffect").relationshipFrom(Cypher.anyNode("john"), Relations.LIKES),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("john"), Relations.HAS),
                Cypher.anyNode("witcher").relationshipFrom(Cypher.anyNode("max"), Relations.HAS),
                Cypher.anyNode("battlefield").relationshipFrom(Cypher.anyNode("max"), Relations.HAS),
                Cypher.anyNode("battlefield").relationshipFrom(Cypher.anyNode("john"), Relations.HAS),
                Cypher.anyNode("assassinsCreed").relationshipFrom(Cypher.anyNode("alex"), Relations.HAS),
                Cypher.anyNode("assassinsCreed").relationshipFrom(Cypher.anyNode("john"), Relations.HAS),
                Cypher.anyNode("massEffect").relationshipFrom(Cypher.anyNode("john"), Relations.HAS)
            ).joinToString("\n") { Cypher.create(it).build().cypher }

            session?.run(query)
        }
    }
}