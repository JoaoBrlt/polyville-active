# PolyVille Active: Parking innovation plugin

## Build the plugin

- To build the plugin:
```bash
mvn clean package
```

## Run the plugin

- To run the plugin:
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
