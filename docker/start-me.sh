#!/usr/bin/env bash
# Build microservice
echo "------------------------------------------------------------------------"
echo "BUILDING evaluation AND IF IT SHOWS ERROR, VERIFY YOUR SOURCE CODE"
echo "------------------------------------------------------------------------"
(cd "./.."; mvn clean install -DskipTests)

# Create custom docker-compose.yml
rm -rf docker-compose.yml; envsubst < "template-docker-compose.yml" > "docker-compose.yml";

# Start microservice
echo 'STARTING MICROSERVICE'
docker-compose up -d --build evaluation

echo 'YOU SHOULD NOW BE ABLE TO RUN THE TESTS!'
echo 'How? Just run the class RunTest.java'
echo 'Cheers!'