CREATE TABLE IF NOT EXISTS foo_reactive (id SERIAL PRIMARY KEY, bar VARCHAR(255), whenn bigint);

postgres=# \dt


docker run -itd --name dev-postgres -e POSTGRES_PASSWORD=you -p 5432:5432 –name pgsql-dev postgres:14.2 

docker exec -it condescending_keller bash

psql -h localhost -U postgres