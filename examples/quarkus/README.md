# Local infrastructure

A local Kafka stack with an associated schema registry and a monitoring instance will be started using:
```
# Run the following command once:
> docker network create -d bridge eda-demo

# Start local stack:
> docker-compose up -d

# Once Kafka is started, create "test-topic" using:
> docker-compose exec kafka kafka-topics --create --topic test-topic --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092

# To start a consumer for a topic, use (flag "--from-beginning" is optional):
> docker-compose exec kafka kafka-console-consumer --topic test-topic --from-beginning --bootstrap-server localhost:9092
```

To reach the monitoring instance, use the following URL: http://localhost:8082.

To destroy the local Kafka stack, use:
```
> docker-compose down
```

# Automatic model generation

Using AsyncAPI contract, it is possible to automatically generate model payloads.

## Generated sources from AsyncAPI contrat

An AsyncAPI contract is defined at the root folder of each project and generate sources from that contract can be made using several tools.

### Docker asyncapi/cli image

Use the following command to generate models from a contract:
```
> docker run --rm -it --user=root -v ${PWD}\\contract_asyncapi_v1.0.yaml:/app/asyncapi.yaml -v ${PWD}\\target\\generated-sources\\com\\ft\\demo\\generated\\model:/app/output asyncapi/cli generate models java /app/asyncapi.yaml -o /app/output --packageName=com.ft.demo.generated.model
```

### Use native asyncapi CLI tool

Following the instruction describe here: https://www.asyncapi.com/docs/tools/cli/installation.

Then use the CLI to generate models from a contract:
```
> asyncapi generate models java .\contract_asyncapi_v1.0.yaml --packageName=com.ft.demo.generated.model -o ${PWD}\\target\\generated-sources\\com\\fr\\demo\\generated\\model
```

## Enable generated sources detection

### IntelliJ settings

In IntelliJ window, mark the "target/generated-sources" directory as "Generated Sources root" using the contextual menu (with a right click on the folder in the explorer).
You can also use the "Project Structure > Project settings > Modules" interactive menu.

### Maven plugin

The "build-helper" plugin has been included in the projects POM. The detection should be automatic.