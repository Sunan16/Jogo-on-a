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

# Copia o JAR da etapa de build para a
