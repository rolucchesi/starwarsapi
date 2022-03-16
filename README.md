<img src="https://img.shields.io/badge/java%20corretto-11-blue"/> <img src="https://img.shields.io/badge/IDE-IntelliJ-blue"/>


# Star Wars Api

## Apresentação

Este código se trata de um projeto acadêmico do curso de Programação WEB Full Stack oferecido pela Faculdade Santander. O desafio foi proposto para os alunos desenvolverem uma API com o tema do StarWars utilizando a linguagem de programação JAVA e o framework Spring Boot.

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

### 📋 Pré-requisitos

Baixar e instalar o Java Corretto 11. Seguir as instruções do fabricante.

```
https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html
```
Baixar e instalar a IDE IntelliJ. Seguir as instruções do fabricante.
```
https://www.jetbrains.com/pt-br/idea/download/#section=windows
```

### 🔧 Para Utilizar

1 - Obtenha o código principal. Pode escolher entre usar o git clone ou baixar o arquivo em .zip.

```
https://github.com/rolucchesi/starwarsapi.git
```

2 - Clone ou extraia o arquivo em uma pasta desejada.

```
git clone https://github.com/rolucchesi/starwarsapi.git
```

3 - Abrir a IDE IntelliJ.
```
File > Open > [Escolha a pasta clonada ou extraída]
```

4 - Para executar o projeto, utilize o comando:
```
mvn spring-boot:run
```
O projeto irá subir na porta 8080 (http://localhost:8080)

5 - Para exercutar os testes, utilize o comando:
```
mvn test
```

### 📋 Descrição

- Todas as requisições estão documentadas no swagger. 
Após inicialização do projeto acesse o endereço: http://localhost:8080/swagger-ui.html

- O projeto foi construído com a arquitetura REST, com comunicação feita via JSON.

- Por meio dos endpoints é possível cadastrar novos rebeldes, atualizar sua localização, denunciar um rebelde como traidor, trocar itens de seus inventários e gerar relatório.

- Mais detalhes sobre a descrição do problema podem ser obtidos no link: [Projeto Final do módulo Desenv Web.pdf](https://github.com/rolucchesi/starwarsapi/files/8258773/Projeto.Final.do.modulo.Desenv.Web.pdf)


## 🛠️ Stacks Utilizadas

* Maven
* Java 11
* Spring Boot
* Spring Data
* Spring JPA


## ✒️ Autores

* [Lucas Depollo](https://github.com/ldepollo)
* [Rodrigo da Cunha Lucchesi](https://github.com/rolucchesi)

---

