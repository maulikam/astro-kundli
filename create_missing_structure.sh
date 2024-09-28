#!/bin/bash



# Create missing directories and files
mkdir -p src/main/java/co/renil/astro/kundli/config
mkdir -p src/main/java/co/renil/astro/kundli/controller
mkdir -p src/main/java/co/renil/astro/kundli/dto
mkdir -p src/main/java/co/renil/astro/kundli/entity
mkdir -p src/main/java/co/renil/astro/kundli/repository
mkdir -p src/main/java/co/renil/astro/kundli/service
mkdir -p src/main/java/co/renil/astro/kundli/util
mkdir -p src/main/java/co/renil/astro/kundli/security
mkdir -p src/main/resources/messages

# Create placeholder Java files for controllers
touch src/main/java/co/renil/astro/kundli/controller/UserController.java
touch src/main/java/co/renil/astro/kundli/controller/PersonController.java
touch src/main/java/co/renil/astro/kundli/controller/KundliController.java
touch src/main/java/co/renil/astro/kundli/controller/MatchMakingController.java
touch src/main/java/co/renil/astro/kundli/controller/SubscriptionController.java

# Create placeholder Java files for entities
touch src/main/java/co/renil/astro/kundli/entity/User.java
touch src/main/java/co/renil/astro/kundli/entity/Person.java
touch src/main/java/co/renil/astro/kundli/entity/Kundli.java
touch src/main/java/co/renil/astro/kundli/entity/MatchMaking.java
touch src/main/java/co/renil/astro/kundli/entity/Subscription.java
touch src/main/java/co/renil/astro/kundli/entity/Payment.java

# Create placeholder Java files for repositories
touch src/main/java/co/renil/astro/kundli/repository/UserRepository.java
touch src/main/java/co/renil/astro/kundli/repository/PersonRepository.java
touch src/main/java/co/renil/astro/kundli/repository/KundliRepository.java
touch src/main/java/co/renil/astro/kundli/repository/MatchMakingRepository.java
touch src/main/java/co/renil/astro/kundli/repository/SubscriptionRepository.java

# Create placeholder Java files for services
touch src/main/java/co/renil/astro/kundli/service/UserService.java
touch src/main/java/co/renil/astro/kundli/service/PersonService.java
touch src/main/java/co/renil/astro/kundli/service/KundliService.java
touch src/main/java/co/renil/astro/kundli/service/MatchMakingService.java
touch src/main/java/co/renil/astro/kundli/service/SubscriptionService.java

# Create other directories and placeholder files
touch src/main/java/co/renil/astro/kundli/util/Utility.java
touch src/main/java/co/renil/astro/kundli/security/SecurityConfig.java

# Create localization directory and placeholder file
touch src/main/resources/messages/messages_en.properties
touch src/main/resources/messages/messages_gu.properties

echo "Missing directories and files created successfully."
