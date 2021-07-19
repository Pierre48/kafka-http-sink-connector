This repository contains a proof of concept of a Kafka connect that will allow to push events to a webapi.

To run the POC, you just have to have docker-compose and to run the following command :
````
docker-compose up -d
````

Once the POC is running, events will be automatically generated, and pushed to the webapi. To check that, you just have to refresh the following api : http://localhost:5000/
