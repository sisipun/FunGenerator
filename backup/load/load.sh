docker build -t load .
docker run --volume=$HOME/neo4j/data_new:/data --name load-container -it load
docker rm --force load-container