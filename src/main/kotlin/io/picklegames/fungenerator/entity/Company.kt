package io.picklegames.fungenerator.entity

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node
class Company(
    @Id @GeneratedValue var id: Long?,
    val name: String
) {

    constructor(name: String) : this(null, name)
}