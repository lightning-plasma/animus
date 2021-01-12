FROM postgres:13-alpine
ENV LANG ja_JP.utf8

# https://hub.docker.com/_/postgres
# see Initialization scripts
COPY ./schema/*.sql /docker-entrypoint-initdb.d/
COPY ./table/*.sql /docker-entrypoint-initdb.d/
COPY ./init-data/*.sql /docker-entrypoint-initdb.d/