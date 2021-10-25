# PolyVille Active: Common dependencies

## Build the project

- To build the project:
```bash
mvn clean package
```

## Install the project on the local computer

> May be necessary if testing the plugin individually.

- To install the project:
```bash
mvn install:install-file \
    -Dfile=target/common-0.0.1.jar \
    -DgroupId=fr.unice.ps7.al1 \
    -DartifactId=common \
    -Dversion=0.0.1 \
    -Dpackaging=jar
```

## Authors

- [João Brilhante](https://github.com/JoaoBrlt)
- [Charly Ducrocq](https://github.com/CharlyDucrocq)
- [Quentin Larose](https://github.com/QuentinLarose)
- [Loïc Rizzo](https://github.com/Loic-Rizzo)
