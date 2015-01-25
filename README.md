# Flyway-presentation
Presentation of [Flyway](http://flywaydb.org), with code examples.

## Presentation
Just open the project file `./presentation/index.html`. The presentation is in Norwegian, as it was held during a company-internal event at [JProfessionals](http://www.jpro.no), where I work.

The presentation was written using [Reveal.js](https://github.com/hakimel/reveal.js/) from [Hakim El Hattab](http://hakim.se).

## Demo
The functional and technical aspects of the demo is explained in the presentation.

It requires [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and [Maven](http://maven.apache.org/download.cgi) to build and run.

### In-memory tests

Build and run all tests:
```
cd ./demo
mvn test
```

This will run the tests using an in-memory H2 database. The database schema is created by the tests, and all transactions are rolled back.

### Standalone database

To run the demo against a running [H2](http://www.h2database.com) database, first start the database:
```
cd ./demo/src/main/scripts
./start-h2.sh
```

Then migrate the database and run some demo code from a different shell:
```
cd ./demo
mvn flyway:migrate
mvn exec:java
```