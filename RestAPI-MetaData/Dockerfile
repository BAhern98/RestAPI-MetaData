FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=newdb
ENV MYSQL_PASSWORD=password

COPY ./src/main/resources/schema.sql /docker-entrypoint-initdb.d/
