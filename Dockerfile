FROM openjdk:8
EXPOSE 8090
ADD target/ProduitMs-0.0.1-SNAPSHOT.jar ProduitMs-docker.jar
ENTRYPOINT ["java","-jar","ProduitMs-docker.jar"]