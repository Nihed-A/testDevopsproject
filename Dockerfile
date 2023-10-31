FROM eclipse-temurin:17-alpine
COPY target/etudiant-1.0.jar .
EXPOSE 8089
ENTRYPOINT ["java","-jar","/etudiant-1.0.jar"]