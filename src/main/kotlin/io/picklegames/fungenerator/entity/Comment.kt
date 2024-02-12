package io.picklegames.fungenerator.entity

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
data class Comment(
    @Id @GeneratedValue var id: Long?,
    val text: String,
    @Relationship(Relations.CREATED, direction = Relationship.Direction.INCOMING) val author: User,
    @Relationship(Relations.TO, direction = Relationship.Direction.OUTGOING) val review: Review
)