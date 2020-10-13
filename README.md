# Backmonstrao
![Java CI with Gradle](https://github.com/lsandrade/backmonstrao/workflows/Java%20CI%20with%20Gradle/badge.svg)

Backmonstrao é uma Api que mocka transações financeiras de um usuário.

## Tecnologias
```
Java 11
Gradle
Springboot
Mockito e MockMvc para testes
```

## Comandos
### Buildar aplicação
```
$ ./gradlew clean build
```

## Buildar container
```
$ docker-compose build
```

## Subir container
```
$ docker-compose up
```

### Requisições
```
[GET] /<id>/transacoes/<ano>/<mes>
```

### Exemplo de requisição
```
GET http://localhost:8080/9999/transacoes/2020/12/
```

### Exemplo de resposta
```
[
  {
    "descricao": "colchao desenvolveu com fome",
    "data": 1611124691513,
    "valor": -2243130,
    "duplicated": false
  },
  {
    "descricao": "colchao desenvolveu com fome",
    "data": 1611124691513,
    "valor": -2243130,
    "duplicated": true
  }
]
```

<p>
    <img src="https://github.com/lsandrade/backmonstrao/blob/master/imgs/Captura%20de%20Tela%202020-10-12%20%C3%A0s%2021.56.24.png">
 </p>
 <p>
    <img src="https://github.com/lsandrade/backmonstrao/blob/master/imgs/Captura%20de%20Tela%202020-10-12%20%C3%A0s%2021.56.39.png">
 </p>
