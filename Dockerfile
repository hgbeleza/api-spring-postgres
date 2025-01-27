# Usar a imagem do OpenJDK 17 (ou a versão do Java que você preferir)
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o arquivo jar gerado para o contêiner
COPY target/api-postgres-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta 8080 (ou a porta que você configurou)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
