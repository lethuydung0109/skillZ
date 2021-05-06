# skillZ

Université Paris Nanterre 
©skillZ

[![Build Status](https://www.travis-ci.com/lethuydung0109/skillZ.svg?branch=master)](https://www.travis-ci.com/lethuydung0109/skillZ)
![Release](https://img.shields.io/github/v/release/lethuydung0109/skillZ)
[![codecov](https://codecov.io/gh/lethuydung0109/skillZ/branch/master/graph/badge.svg?token=WH0A4NP01M)](https://codecov.io/gh/lethuydung0109/skillZ)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/3a53a12ce2a544b6a8f98d010b416d1d)](https://www.codacy.com/gh/lethuydung0109/skillZ/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=lethuydung0109/skillZ&amp;utm_campaign=Badge_Grade)
![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability/riad-lazli/skillZ)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=bugs)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=code_smells)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=coverage)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=security_rating)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=ncloc)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lethuydung0109_skillZ&metric=alert_status)](https://sonarcloud.io/dashboard?id=lethuydung0109_skillZ)

![License](https://img.shields.io/github/license/lethuydung0109/skillZ)



## Qu'est-ce que c'est skillZ ?

SkillZ est une application de validation et suivi des compétences, elle permet d'évaluer de façon concrète le niveau de maitrise d'une compétence donnée.

En outre, elle permet d'un coté aux évaluateurs de créer des questionnaires, et de l'autre, aux des candidats de passer ces questionnaires et recevoir des badges de compétences. 

## Table des matières
* [Installation](#Installation)
* [Technologies](#Technologies)
* [Utilisation](#Utilisation)


## Installation 

### En local : 
  - Pour lancer le Banckend utilisez la commande : ./gradlew bootRun
  - Pour lancer le Frontend utilisez la commande : ng serve
  - Pour accéder à l'interface utilisateur, lancez le lien : localhost:4200/
  - Api avec swagger-ui :  http://localhost:8081/swagger-ui/

### En remote : 

  - Connectez vous à l'adresse : 34.78.77.112:4200/home

Remarque : il faut créer un schéma de base de données sous MySql avec le nom suivant : skillzdb

## Technologies

Ce projet utilise : 

SGBD : MySql

Backend : Java , Spring

Frontend : Angular, TypeScript, HTML, CSS

Moteur de production : Gradle


## Utilisation 

Une fois que vous avez tout installé, vous pouvez vous connecter avec votre identifiant et mot de passe, Si : 

### Vous etes un administrateur 

  - Créer/modifier/supprimer des utilisateurs.
  - Créer/modifier/supprimer des compétences.


### Vous etes un évaluateur  

- Créer une question et des réponses associées.
- Créer un questionnaire avec une liste de questions, une durée, un seuil de validation. 
- Chercher une liste de candidats pour un poste donnée.

### Vous etes un participant

- Passer un questionnaire et obtenir un badge de niveau associé si réussi.
- Recommender un participant.


