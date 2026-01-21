# kafka-example

A Quarkus application demonstrating Kafka messaging integration.

## Local infrastructure (Podman)

This repo ships a small local stack:

- Kafka (KRaft, no ZooKeeper)
- Kafka Connect (REST on `:8083`)
- Kafbat UI (web UI on `:8080`)
- Keycloak (OIDC, web UI on `:8081`)
- Apicurio Registry (HTTP API on `:8082`, backed by Postgres)
- Postgres (for Apicurio storage, on `:5432`)

### Start

```shell
podman-compose up -d
```

### Stop / cleanup

```shell
podman-compose down
```

### URLs

- Kafka (internal): `kafka:9092`
- Kafka (from your laptop/tools): `localhost:29092`
- Kafka Connect REST: http://localhost:8083
- Kafbat UI: http://localhost:8080
- Keycloak: http://localhost:8081
  - admin user: `admin` / `admin`
  - imported realm: `kafka-example`
- Apicurio Registry:
  - UI/API base: http://localhost:8082
  - v3 API base: http://localhost:8082/apis/registry/v3
- Postgres (optional access): `localhost:5432` (db/user/pass: `registry`)

### OAuth note (Strimzi OAuth)

Keycloak is included so you can use it as an OIDC provider for Strimzi OAuth.

The provided compose file **does not yet enable OAuth on Kafka/Kafka Connect** because the commonly used Strimzi OAuth login module isn’t bundled with the Confluent Kafka/Connect images by default.

If you want, I can:

1. add a custom Kafka/Connect image that includes the Strimzi OAuth login JAR,
2. switch Kafka listeners to SASL/OAUTHBEARER,
3. configure Kafka Connect to authenticate via OAuth.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

Then run:

```shell
java -jar target/quarkus-app/quarkus-run.jar
```

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

You can then execute your native executable with: `./target/kafka-example-1.0.0-SNAPSHOT-runner`

## Related Guides

- [Apache Kafka Client](https://quarkus.io/guides/kafka): Connect to Apache Kafka with its native API
- [Messaging - Kafka Connector](https://quarkus.io/guides/kafka-getting-started): Connect to Kafka with Reactive Messaging
