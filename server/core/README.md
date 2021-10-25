# PolyVille Active: Core application

## Build the application

- To build the application:
```bash
mvn clean package
```

## Run the application

- To run the application:
```bash
./mvnw spring-boot:run
```

## Run the SonarQube scanner

- To run the SonarQube scanner:
```bash
mvn sonar:sonar
```

## Run the PITest mutation tests

- To run the PITest mutation tests:
```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

## Authors

- [João Brilhante](https://github.com/JoaoBrlt)
- [Charly Ducrocq](https://github.com/CharlyDucrocq)
- [Quentin Larose](https://github.com/QuentinLarose)
- [Loïc Rizzo](https://github.com/Loic-Rizzo)

