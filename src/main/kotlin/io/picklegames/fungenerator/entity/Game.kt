package io.picklegames.fungenerator.entity

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING
import org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING

@Node
data class Game(
    @Id @GeneratedValue var id: Long?,
    val name: String,
    @Relationship("IS", direction = OUTGOING) val genre: Genre,
    @Relationship("CREATED", direction = INCOMING) val company: Company,
    @Relationship("LIKES", direction = INCOMING) val likes: MutableSet<User> = mutableSetOf(),
    @Relationship("HAS", direction = INCOMING) val owners: MutableSet<User> = mutableSetOf(),
    @Relationship("RATED", direction = INCOMING) val ratings: MutableSet<Rating> = mutableSetOf()
) {

    constructor(name: String, genre: Genre, company: Company) : this(null, name, genre, company)
}