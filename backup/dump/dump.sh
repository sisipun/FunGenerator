docker build -t dump .
docker run --volume=$HOME/neo4j/data:/data --name dump-container -it dump
dump_date=$(date +"%Y_%m_%d_%H_%M_%S")
docker cp dump-container:/dumps/neo4j.dump ./neo4j_$dump_date.dump
docker rm --force dump-container