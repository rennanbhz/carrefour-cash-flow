# Carrefour Cash Flow

This is the REST API for Carrefour. It includes all operations
that supports the management of Cash Flow.

## Technologies

* Spring Boot
* Java 11
* MongoDB
* Spock (Unit tests)

## How do I get set up? ###

### Prerequisites

* [JDK 11](https://www.azul.com/downloads/zulu-community)
* [Maven-3](https://maven.apache.org/download.cgi)
* [Git](https://git-scm.com/downloads)
* [Docker 1.13.0 +](https://www.docker.com/products/overview)
* [Docker Compose 1.18.0 +](https://docs.docker.com/compose/install/)
* [SonarLint plugin](http://www.sonarlint.org)

### Installation guides

* [Windows 10](#windows-10-installation-guide)
* [Linux (Ubuntu based)](#linux-installation-guide)

### Available commands

1 - Clone the repository and switch to directory

```bash
git clone git@github.com:rennanbhz/carrefour-cash-flow.git
```

### Running Locally

* Run the docker-compose command:

```
 docker-compose up
```

Then run the main class **CarrefourCashFlowApplication** that will start the application.

## Tests and QA ##

### Technologies ###

* Unit tests layer - Spock.

### Required plugins

##### 1- Google Java Format

On **Intellij**, go to File > Settings > Plugins and search for: google-java-format and install.
After installing, go to File >
Settings > Other Settings and in Google Java Format set as Enabled.

##### 2- Save Actions

On **Intellij**, go to File > Settings > Plugins and search for: Save Actions and install. After
installing, go to File >
Settings > Other Settings and in Save Actions, enable these fields:

* "Activate save actions on save".
* "Activate save actions on shortcut".
* "Optimize imports".
* "Reformat file".
