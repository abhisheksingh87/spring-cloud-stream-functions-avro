# kafka-reactive-starter-avro

This starter provides a simple kafka producer and consumer, based on _Spring Webflux_ and _Spring Cloud Streams_

It can be deployed as a standalone app
It will connect to Kafka on `localhost:9092` unless you provide a `spring.kafka.bootstrap-servers` connection property
in `application.yml` for connecting to a different Kafka cluster.

## Application

> The project is configured for Java 8 in `build.gradle`
>  It produces to `card-events` topic.

You can build the project using the provided gradle wrapper:

```bash
./gradlew clean build -x test
```

The project uses `avro` for data serialization and data exchange:

you can run below command to generate avro data transfer objects:
```bash
./gradlew generateAvroJava
```


### Run the app locally

To run the app locally using the embedded Tomcat server you can run this command:

```bash
./gradlew  bootRun
```

> **NOTE**: The app will connect to Kafka on `localhost:9092` by default

You can access the function using `curl`:

```bash
>curl -H "Content-Type: application/json" -X POST -d {\"id\":\"1\",\"firstName\":\"alex\",\"lastName\":\"smith\",\"phoneNumber\":\"432-245-0000\"} http://localhost:8080/customer/create
```

You should see below output in the console of the app:

```text
2021-05-20 13:26:09.952  INFO 10040 --- [oundedElastic-1] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 2.6.0
2021-05-20 13:26:09.952  INFO 10040 --- [oundedElastic-1] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 62abe01bee039651
2021-05-20 13:26:09.952  INFO 10040 --- [oundedElastic-1] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1621535169952
2021-05-20 13:26:09.955  INFO 10040 --- [ad | producer-2] org.apache.kafka.clients.Metadata        : [Producer clientId=producer-2] Cluster ID: HQET3jSzSxeCDGN92oambQ
2021-05-20 13:26:10.188  INFO 10040 --- [container-0-C-1] s.k.c.f.e.e.CardStatusUpdateEventHandler : Consumed Card Details: {"amountLimit": "100", "cardStatus": "CARD_VERIFYING", "cardType": "Master", "customerId": "48618ceb-6f3d-4555-8c8f-1f9a3edc708e", "customerIncome": null, "id": "542a08cc-9d1d-4464-914b-11b44760cde8"}
```
