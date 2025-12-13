FROM openjdk:21-ea-1-jdk-oracle

WORKDIR /app

COPY /target/desafio_junior_plano_saude.jar /app/desafio_junior_plano_saude.jar

EXPOSE 8080

CMD [ "java", "-jar", "/app/desafio_junior_plano_saude.jar" ]