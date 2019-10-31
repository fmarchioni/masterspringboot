# Simple Kafka Producer

## Kafka Topic

```shell
thetechcheck
```

## Creating a Kafka Topic

```cd``` into the Kafka directory, and run the following command to create a new topic:

```shell
./bin/kafka-topics.sh --create --topic thetechcheck --replication-factor 1 --partitions 1 --zookeeper localhost:2181
```

## Running the SpringBoot application

```cd``` into the project directory and run the following command to create a ```.jar``` file of the project:

```shell
mvn clean install
```

This will create a ```.jar``` file in the ```target``` directory, inside the project directory. Now to run the project, run the following command from the same project directory:

```shell
java -jar target/<name_of_jar_file>.jar
```

You should now be seeing the output in the terminal.