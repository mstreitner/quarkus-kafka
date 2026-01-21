# kafka-example

A small Quarkus + Kafka playground that lets you run a local Kafka stack via Podman and push messages through a simple pipeline.

## Local infrastructure (Podman)

This repo ships a local stack defined in `docker-compose.yml`:

- Kafka (KRaft mode, no ZooKeeper)
- Kafka Connect (REST API on `:8083`)
- Kafbat UI (web UI on `:8080`)
- Apicurio Registry (HTTP API/UI on `:8082`, backed by Postgres)
- Postgres (for Apicurio storage, on `:5432`)
- A one-shot init container that creates the demo topics

### Start

```shell
podman-compose up -d
```

### Stop / cleanup

```shell
podman-compose down
```

### URLs

- Kafka (internal / containers): `kafka:9092`
- Kafka (from your laptop/tools): `localhost:29092`
- Quarkus Backend REST: http://localhost:9090
- Kafka Connect REST: http://localhost:8083
- Kafbat UI: http://localhost:8080
- Apicurio Registry:
  - UI/API base: http://localhost:8082
  - v3 API base: http://localhost:8082/apis/registry/v3
  - Confluent-compatible API (used by Kafbat UI): http://localhost:8082/apis/ccompat/v7
- Postgres (optional access): `localhost:5432` (db/user/pass: `registry`)

## Topics

The compose stack auto-creates these topics on startup:

- `example-input-v1`
- `example-output-v1`

## Quarkus application flow

The app demonstrates a minimal end-to-end flow:

1. The REST endpoint publishes to the *input* topic.
2. `InputConsumer` consumes, transforms the payload, then publishes to the *output* topic.
3. (Optional) You can observe output via Kafka tooling / UI.

## Running the application

Dev mode:

```shell
./mvnw quarkus:dev
```

## Publish a message via HTTP

Call the endpoint with a `value` query param.

- It will generate a random UUID as Kafka record key
- It will publish the query param string as the record value

```shell
curl "http://localhost:8080/publish?value=hello"
```

Expected response:

```json
{"key":"<uuid>","value":"hello"}
```

## Packaging and running

```shell
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

## Related Guides

- [Apache Kafka Client](https://quarkus.io/guides/kafka)
- [Messaging - Kafka Connector](https://quarkus.io/guides/kafka-getting-started)
