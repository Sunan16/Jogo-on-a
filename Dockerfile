# Etapa 1: Construir a aplicação
# Use uma imagem Maven para compilar o projeto
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo de configuração do Maven e as dependências para cache
COPY pom.xml ./
COPY src ./src

# Executa o comando de construção, baixando as dependências e criando o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final para execução
FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR da etapa de build para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Define a variável de ambiente para o servidor do Railway
ENV PORT=8080

# Expor a porta do contêiner
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app/app.jar"]
