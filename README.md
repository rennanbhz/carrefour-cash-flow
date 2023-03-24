# Fluxo de Caixa - Carrefour

This is the REST API for Carrefour. It includes all operations
that supports the management of Cash Flow.

Esta é uma API REST para o teste do projeto Carrefour.
Inclui todas as operações que suportam o gerenciamento de um fluxo de caixa.

## Tecnologias

* Spring Boot
* Java 11
* MongoDB
* Spock (Unit tests)

## Como faço o setup inicial? ###

### Pré requisitos

* [JDK 11](https://www.azul.com/downloads/zulu-community)
* [Maven-3](https://maven.apache.org/download.cgi)
* [Git](https://git-scm.com/downloads)
* [Docker 1.13.0 +](https://www.docker.com/products/overview)
* [Docker Compose 1.18.0 +](https://docs.docker.com/compose/install/)
* [SonarLint plugin](http://www.sonarlint.org)

### Guias de instalação

* [Windows 10](#windows-10-installation-guide)
* [Linux (Ubuntu based)](#linux-installation-guide)

### Comandos

1 - Clone o repositório e troque de diretório

```bash
git clone git@github.com:rennanbhz/carrefour-cash-flow.git
```

### Executando localmente

* Execute o comando docker-compose:

```
 docker compose up
```

Então execute a classe principal **CarrefourCashFlowApplication** que irá iniciar a aplicação.

## Testes and QA ##

### Tecnologias ###

* Camada de testes unitários - Spock.

### Plugins requeridos

##### 1- Google Java Format

No **Intellij**, vá para File > Settings > Plugins e procure por: google-java-format and install.
Depois de instalar, vá para File >
Settings > Other Settings e em Google Java Format mude para Enabled.

##### 2- Save Actions

On **Intellij**, vá para File > Settings > Plugins and e procure por:
Save Actions e instale. depois de instalado, vá para File >
Settings > Other Settings e em Save Actions, habilite estes campos:

* "Activate save actions on save".
* "Activate save actions on shortcut".
* "Optimize imports".
* "Reformat file".

### Exemplo de requisições:

* Criar uma movimentação:

url: localhost:8080/create-financial-posting

```
{
"value": 1000.0,
"type": "DEBIT",
"description": "testing"
}
```

* Buscar movimentações por data:

url: localhost:8080/getFinancialPostingByDate?data=2023-03-21

* Gerar relatório diário:

url: localhost:8080/generateDailyReport?data=2023-03-21
