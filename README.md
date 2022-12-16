pucksmart monorepo
------

Running Locally
======

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