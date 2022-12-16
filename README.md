pucksmart monorepo
======

When importing to IntelliJ, open the backend directory not the whole repo

Dependencies
----

Install SDKMAN to manage your java version, https://sdkman.io/

Once you have SDKMAN set up, install the right JDK with:

```
sdk install java 22.3.r17-grl
```

Running Locally
------

To run the infrastructure, kafka and mongodb:

```
docker-compose up
```

When you are done, shut down with:

```
docker-compose stop
```


To run the application, after you `cd backend`:

```
./gradlew bootRun
```

OR run the main class `BackendApplication` from IntelliJ

Make a `GET` request to: `http://localhost:8080/stats/list-seasons`