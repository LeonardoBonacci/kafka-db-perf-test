```
CREATE TABLE IF NOT EXISTS foo_reactive (id SERIAL PRIMARY KEY, bar VARCHAR, whenn bigint);

postgres=# \dt

docker run -itd -e POSTGRES_PASSWORD=you -p 5432:5432 –name pgsql-dev postgres:14.2 

docker exec -it ??? bash

psql -h localhost -U postgres
```