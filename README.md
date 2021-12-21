# Building the client
Before proceeding, install a [JDK](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) (must be Java 8 or later) and [Apache Maven](https://maven.apache.org/install.html).

Ensure `JAVA_HOME` is set correctly and the `mvn` executable is available on your PATH.

There are two approaches to building the client.
1. You can compile just the client and publish to a repository that handles dependency management like MavenCentral.  (Recommended)
2. You can build a "fat jar" that contains all required dependencies; this jar can be added to your customers' classpath.

## 1. Building a single jar
Run the following command in a terminal/console.
```bash
mvn package
```

This compiles the client into a jar located at `./target/anprcloud-client-2.0-SNAPSHOT.jar`. Note that this jar does not include any dependencies.

## 2. Building a Standalone Jar

If your customers aren't using Maven or Gradle and you would prefer to distribute a single JAR with all dependencies, you can use the following command to build a fat jar.

```bash
mvn clean package -Pstandalone-jar
```

This will compile the client and package all dependencies into a jar located at `./target/anprcloud-client-2.0-SNAPSHOT.jar`.

