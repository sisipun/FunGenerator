FROM neo4j
ENTRYPOINT neo4j start && mkdir -p /dumps && sleep 5 && neo4j stop && neo4j-admin database dump neo4j --to-path=/dumps