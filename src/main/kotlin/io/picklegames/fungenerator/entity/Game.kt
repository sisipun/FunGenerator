package io.picklegames.fungenerator.entity

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING

@Node
data class Game(
    @Id @GeneratedValue var id: Long?,
    val name: String,
    @Relationship("LIKES", direction = INCOMING) val likes: MutableSet<User> = mutableSetOf(),
    @Relationship("HAS", direction = INCOMING) val owners: MutableSet<User> = mutableSetOf(),
    @Relationship("RATED", direction = INCOMING) val ratings: MutableSet<Rating> = mutableSetOf()
) {

    constructor(name: String) : this(null, name)
}