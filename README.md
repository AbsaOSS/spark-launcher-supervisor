# spark-launcher-supervisor
A Java agent that oversees a Spark launcher and checks/enforces settings

This is a quick PoC of a JVM agent that is aimed to be globally installed on edge nodes
to make sure Spark jobs are submitted with correct settings.

The current version of agent simply checks the following Spark settings and prints a warning message to the console if they don't match the expected values:
1. `master` to be `yarn`
2. `deploy-mode` to be `cluster`

## Supported Spark version
Any

## Todo:
- Flexible definition of rules/checks that can be either passed as an agent parameter, config file, or even managed centrally (remotely)
- Send notifications
- Option to enforce settings
- Shade Byte-Buddy to avoid potential classpath conflicts
- Build RPM
- Improve logging

## Building
```shell
mvn install
```

## Usage
```shell
export JAVA_TOOL_OPTIONS="$JAVA_TOOL_OPTIONS -javaagent:agent/target/agent-0.1.0-SNAPSHOT.jar"

# run Spark as usual
spark-submit ...
pyspark ...
```

