FROM openjdk:17-ea-10-jdk

WORKDIR /app

COPY /target/desafio_junior_plano_saude.jar /app/desafio_junior_plano_saude.jar

EXPOSE 8080

CMD [ "java", "-jar", "/app/desafio_junior_plano_saude.jar" ]