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
    @Relationship(Relations.IS, direction = OUTGOING) val genres: MutableSet<Genre> = mutableSetOf(),
    @Relationship(Relations.CREATED, direction = INCOMING) val companies: MutableSet<Company> = mutableSetOf(),
    @Relationship(Relations.LIKES, direction = INCOMING) val likes: MutableSet<User> = mutableSetOf(),
    @Relationship(Relations.HAS, direction = INCOMING) val owners: MutableSet<User> = mutableSetOf(),
    @Relationship(Relations.RATED, direction = INCOMING) val ratings: MutableSet<Rating> = mutableSetOf()
) {

    constructor(name: String) : this(null, name)
}