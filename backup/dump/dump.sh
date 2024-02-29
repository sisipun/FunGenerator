docker build -t dump .
docker run --name dump-container -it dump
docker cp dump-container:/dumps/neo4j.dump ./neo4j.dump

docker rm --force dump-container