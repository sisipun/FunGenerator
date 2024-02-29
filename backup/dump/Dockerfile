FROM neo4j
VOLUME $HOME/neo4j/data:/data
ENTRYPOINT neo4j start && mkdir -p /dumps && sleep 5 && neo4j stop && neo4j-admin database dump neo4j --to-path=/dumps