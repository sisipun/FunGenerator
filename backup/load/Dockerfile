FROM neo4j
COPY neo4j.dump /dumps/neo4j.dump
ENTRYPOINT neo4j-admin database load neo4j --from-path=/dumps