docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name camel_jpa -e POSTGRES_USER=camel -e POSTGRES_PASSWORD=camel -e POSTGRES_DB=cameldb -p 5432:5432 postgres:10.5

