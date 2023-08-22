# Rental Backend

Rental Backend est un service RESTful construit avec Spring Boot, destiné à gérer et fournir des informations sur des locations.

## Table des matières
- [Introduction](#introduction)
- [Fonctionnalités](#fonctionnalités)
- [Technologies utilisées](#technologies-utilisées)
- [Installation et utilisation](#installation-et-utilisation)
- [Documentation API avec Swagger](#documentation-api-avec-swagger)

## Introduction
Rental Backend offre une API robuste et flexible pour gérer différents aspects des locations. Que vous souhaitiez obtenir des informations sur une location spécifique ou ajouter une nouvelle location à la base de données, Rental Backend est là pour vous.

## Fonctionnalités
- CRUD complet pour les locations.
- *(Ajoutez d'autres fonctionnalités spécifiques si nécessaire)*

## Technologies utilisées
- **Spring Boot**: Facilitant la création d'applications basées sur Spring.
- **Spring Data JPA**: Pour simplifier la mise en place des couches de persistance des données.
- **Springfox Swagger**: Offrant une documentation interactive de l'API.
- **Spring Security**: Garantissant la sécurité de l'application et l'authentification des utilisateurs.
- **MySQL**: Base de données utilisée pour stocker les informations.

## Installation et utilisation

### Prérequis

- Java 11 ou version ultérieure
- Gradle
- MySQL

### Étapes d'installation

1. **Clonage du dépôt**
    ```bash
    git clone https://github.com/jeremyMulet/rentalBackend
    ```

2. **Construction du projet**  
   Accédez au répertoire du projet :
    ```bash
    cd rentalBackend
    ```
   Puis construisez-le :
    ```bash
    gradle clean build
    ```

3. **Configuration de la base de données**  
   Configurez votre base de données MySQL et mettez à jour le fichier `application.properties` avec vos informations de connexion.

4. **Démarrage de l'application**  
   Vous pouvez démarrer l'application de l'une des manières suivantes :
    - En utilisant Gradle :
        ```bash
        ./gradlew bootRun

5. **Accès à l'API**  
   Une fois l'application démarrée, elle devrait être accessible à l'adresse suivante :  
   `http://localhost:8080/`

## Mise en place de la base de données

Pour initialiser la base de données, suivez les étapes ci-dessous :

### Exécution du script SQL

1. **Localisation du script SQL**  
   Le script d'initialisation `script.sql` est situé dans le package `ressources` du projet.

2. **Utilisation de la ligne de commande**  
   Si vous préférez utiliser la ligne de commande, vous pouvez le faire comme suit :

    ```bash
    mysql -u [username] -p rental_db < path_to_your_project/resources/script.sql
    ```
    - Remplacez `[username]` par votre nom d'utilisateur MySQL.
    - Assurez-vous que le chemin (`path_to_your_project`) pointe vers le répertoire principal de votre projet.

3. **Utilisation d'un outil GUI**  
   Si vous utilisez un outil comme MySQL Workbench :
    - Ouvrez le fichier `script.sql` directement depuis l'outil.
    - Exécutez le script pour initialiser les tables et les relations.

### Vérification

Après avoir exécuté le script, assurez-vous que toutes les tables ont été correctement créées et que les relations sont bien en place.

## Documentation API avec Swagger

Après avoir démarré l'application, la documentation interactive Swagger est disponible à : `http://localhost:8080/swagger-ui/`.
