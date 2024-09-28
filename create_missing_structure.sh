#!/bin/bash

# Root directory for the project
PROJECT_ROOT="astro-kundli"

# Create missing directories and files
mkdir -p $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/config
mkdir -p $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/controller
mkdir -p $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/dto
mkdir -p $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/entity
mkdir -p $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/repository
mkdir -p $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/service
mkdir -p $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/util
mkdir -p $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/security
mkdir -p $PROJECT_ROOT/src/main/resources/messages

# Create placeholder Java files for controllers
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/controller/UserController.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/controller/PersonController.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/controller/KundliController.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/controller/MatchMakingController.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/controller/SubscriptionController.java

# Create placeholder Java files for entities
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/entity/User.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/entity/Person.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/entity/Kundli.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/entity/MatchMaking.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/entity/Subscription.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/entity/Payment.java

# Create placeholder Java files for repositories
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/repository/UserRepository.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/repository/PersonRepository.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/repository/KundliRepository.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/repository/MatchMakingRepository.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/repository/SubscriptionRepository.java

# Create placeholder Java files for services
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/service/UserService.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/service/PersonService.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/service/KundliService.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/service/MatchMakingService.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/service/SubscriptionService.java

# Create other directories and placeholder files
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/util/Utility.java
touch $PROJECT_ROOT/src/main/java/co/renil/astro/kundli/security/SecurityConfig.java

# Create localization directory and placeholder file
touch $PROJECT_ROOT/src/main/resources/messages/messages_en.properties
touch $PROJECT_ROOT/src/main/resources/messages/messages_gu.properties

echo "Missing directories and files created successfully."
