package io.picklegames.fungenerator.entity

import org.springframework.data.neo4j.core.schema.RelationshipId
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@RelationshipProperties
data class Rating(
    @RelationshipId var id: Long?,
    @TargetNode val user: User,
    var value: Int
) {

    constructor(user: User, value: Int) : this(null, user, value)
}