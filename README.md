# Building the sample
Before proceeding, install a [JDK](https://jdk.java.net/archive/) (must be Java 11 or later) and [Apache Maven](https://maven.apache.org/install.html).

Ensure `JAVA_HOME` is set correctly and the `mvn` executable is available on your PATH.

Run the following command in a terminal/console.
```bash
mvn clean package
```

This compiles the sample into a jar located at `./target/anprcloud-sample-3.0-SNAPSHOT.jar`, and copies all the dependenciews into `./target/` directory.

# Running the sample

```bash
$JAVA_HOME/bin/java -jar ./target/anprcloud-sample-3.0-SNAPSHOT.jar api-eu.anpr-cloud.com STAGE REGION API_KEY IMAGE_PATH
```

This will run the sample with the following arguments:
1. STAGE: `free` for the free, and `prod` for the paid (productive) endpoint
2. REGION: for example `eur` for European region
3. API_KEY: your api key (check out the [How To Use](http://eu.anpr-cloud.com/dashboard/how-to-use) page on the ANPR Cloud DevPortal)
4. IMAGE_PATH: the path to the image file that contains the license plate
