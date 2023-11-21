# Utilise l'image officielle de PostgreSQL version 14.1 avec la variante Alpine Linux
FROM postgres:14.1-alpine

# Copie les fichiers SQL du répertoire local "/sql" vers le répertoire "/docker-entrypoint-initdb.d" de l'image
COPY /sql /docker-entrypoint-initdb.d

# Définit des variables d'environnement pour la configuration de la base de données PostgreSQL
ENV POSTGRES_DB=database_java \
   POSTGRES_USER=root \
   POSTGRES_PASSWORD=1234





