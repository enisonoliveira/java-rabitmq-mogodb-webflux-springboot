

[doc sicredi.pdf](https://github.com/enisonoliveira/sicrediteste/files/5765724/doc.sicredi.pdf)

<span>
Api Pauta de votação

Tecnologias usada Srpging-boot, mogoDB, Gradlew, RabbitMQ,Tomcat



Configuração do projeto

1.Versão java 11
.
2.Gerenciador de dependência Gradlew

3.Banco nosql mongodb

4.spring boot

5.Mensageria RabbitMQ


=======================================file propertie.properties===================================================
Confs  mongodb

Arquivo .properties
#mongodb
spring.data.mongodb.host=
spring.data.mongodb.port=
spring.data.mongodb.database=
spring.data.mongodb.username=
spring.data.mongodb.password=


Confs Mensageria RabbitMQ
#rabbit
spring.rabbitmq.host=
spring.rabbitmq.port=
spring.rabbitmq.username=
spring.rabbitmq.password=

Confs Tomcat

server.port=8080
server.servlet.context-path=/pauta


Inicializando o projeto
GitHub
https://github.com/enisonoliveira/sicrediteste

git clone  https://github.com/enisonoliveira/sicrediteste.git

open ssh

cd sicrediteste
 ./gradlew bootRun


url contexto

http://localhost:8080/pauta/



Diagrama básico modelagem de dados figura  1 uml

</span>
