# skillZ

Université Paris Nanterre 
©skillZ

[![Build Status](https://www.travis-ci.com/lethuydung0109/skillZ.svg?branch=master)](https://www.travis-ci.com/lethuydung0109/skillZ)
![Release](https://img.shields.io/github/v/release/lethuydung0109/skillZ)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/3a53a12ce2a544b6a8f98d010b416d1d)](https://www.codacy.com/gh/lethuydung0109/skillZ/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=lethuydung0109/skillZ&amp;utm_campaign=Badge_Grade)
![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability/riad-lazli/skillZ)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=bugs)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=code_smells)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=security_rating)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=ncloc)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)


![License](https://img.shields.io/github/license/lethuydung0109/skillZ)



## Qu'est-ce que c'est skillZ ?

SkillZ est une application de validation et suivi des compétences, elle permet d'évaluer de façon concrète le niveau de maitrise d'une compétence donnée.

En outre, elle permet d'un coté aux évaluateurs de créer des questionnaires, et de l'autre, aux candidats de passer ces questionnaires et recevoir des badges de compétences. 

## Table des matières
* [Installation](#Installation)
* [Déploiement](#Installation)
* [Technologies](#Technologies)
* [Utilisation](#Utilisation)


## Installation 

### En local : 
  - Clonez le projet avec la commande : git clone https://github.com/lethuydung0109/skillZ
  - Pour lancer le Banckend utilisez la commande : ./gradlew bootRun
  - Pour lancer le Frontend utilisez la commande : ng serve
  - Pour accéder à l'interface utilisateur, lancez le lien : localhost:4200/
  - Api avec swagger-ui :  http://localhost:8081/swagger-ui/

### En remote : 

  - Connectez vous à l'adresse : http:://34.78.77.112:4200/home

Remarque : il faut créer un schéma de base de données sous MySql avec le nom suivant : skillzdb

## Déploiement : 
Google Cloud VM : [Google Cloud VM](#google-cloud-vm)

### Install Docker 

### Install Java 

### Install Gradle 
```
wget https://downloads.gradle-dn.com/distributions/gradle-7.0-bin.zip
```
```
sudo unzip -d /opt/gradle gradle-7.0-bin.zip
```

https://linuxize.com/post/how-to-install-gradle-on-ubuntu-18-04/

### Run Gradle

- Modifier la connexion à la base de données dans `src/main/resources/application.properties`

==\> `jdbc:mysql://34.78.77.112:3306/skillZ?useSSL=false`

- Build gradle
    `./gradlew clean build`
    
- Run program
    `./gradlew bootRun`
    or
    `java -jar build/libs/*.jar`

### Dockerization 
#### MySQL

`sudo docker pull mysql`

```
sudo docker run --name dbmysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=skillZ -p 3306:3306 --network="bridge" -d mysql 
```

- Config Firewall rules for ingress/egress connections with port TCP 3306

#### Dockerfile
##### 1. Backend

```
FROM openjdk:11-oracle
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} *.jar
ENTRYPOINT ["java","-jar","/*.jar"]
```

##### 2. Front end

```
FROM nginx:1.19.10-alpine
COPY default.conf /etc/nginx/conf.d/
COPY dist/korector /usr/share/nginx/html
COPY Staticfile /usr/share/nginx/html/korector
```

#### Buidl project - Créer un image Docker et lancer le container
##### 1. Backend
- Lancer le script build_app.sh.

```
./build_app.sh
```

##### 2. Frontend
- En raison de la limitation de la VM GCP, nous devons créer une production locale, puis créer une image Docker et la pousser vers Docker Hub

```
./build_frontend.sh
```

- Run

```
sudo docker pull lethuydung0109/skillz-front
sudo docker container stop -f skillz-front
sudo docker container rm -f skillz-front
sudo docker run --name skillz-front -d -p 4200:80 lethuydung0109/skillz-front
```

## Technologies

Ce projet utilise : 

SGBD : MySql

Backend : Java , Spring

Frontend : Angular, TypeScript, HTML, CSS

Moteur de production : Gradle

Déployer :
  - Docker : Tous les composants, y compris le back-end, le front-end, base de données, pgAdmin, … sont contenus dans un container Docker 
  - Nginx : Nginx est utilisé comme un serveur web pour héberger l’application Angular dans un container Docker
  - mySQL Server : Pour le stockage de la base de données de mySQL

## Utilisation 

Une fois que vous avez tout installé, vous pouvez vous connecter avec votre identifiant et mot de passe, Si : 

### Vous êtes un administrateur 

  - Créer/modifier/supprimer des utilisateurs.
  - Créer/modifier/supprimer des compétences.

Connexion au compte admin :
  - Pseudo : admin
  - Mot de passe : adminskillz
### Vous êtes un évaluateur  

- Créer une question et des réponses associées.
- Créer un questionnaire avec une liste de questions, une durée, un seuil de validation. 
- Chercher une liste de candidats pour un poste donnée.

### Vous êtes un participant

- Passer un questionnaire et obtenir un badge de niveau associé si réussi.
- Recommander un participant.


