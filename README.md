# Evaluation

Projeto para avaliação referente a processo para o time de Dev's da Pismo.io

# Requisitos

* [Java Open JDK 11](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-20-04)
* [Maven](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-18-04)
* [Docker](https://docs.docker.com/engine/install/ubuntu/#installation-methods)
* [Docker Compose](https://docs.docker.com/compose/install/#install-compose-on-linux-systems)
* [H2 Database](http://www.h2database.com/)

# Preparando o ambiente

1. Clone o projeto SSH
```
git@github.com:sabino130690/pismo.io.git
```

# Iniciar a aplicação - easy mode :)

2. Entrar no diretorio docker e executar o comando abaixo utilizando sudo:
```
sudo ./start-me.sh
```

Após executar o script e aparecer os logs do microserviço, você pode acessar os
endpoints pelo endereço http://localhost:8080/swagger-ui

Para acesso ao banco de dados, utilizar o endereço http://localhost:8080/h2-console

```
JDBC Url: jdbc:h2:mem:pismo
Usuario: sa
Senha: password
```

# Parando a aplicação

3. No mesmo diretorio docker e executar o comando abaixo utilizando sudo:
```
sudo docker-compose down
```