# Gestion Commercial - Microservice

## Description
Microservice REST pour la gestion commerciale (gestion_commercial) dans le cadre du projet J2EE/Microservices - **Gestion commerciale par points de vente**.

Ce microservice centralise la gestion des prix des produits et l'enregistrement de toutes les commandes provenant des différents points de vente.

## Technologies Utilisées
- **Spring Boot 3.2.0**
- **Spring Data JPA** - Pour l'accès aux données
- **Spring Data REST** - Pour exposer automatiquement les repositories REST
- **Spring Web** - Pour créer les API REST
- **MySQL** - Base de données relationnelle
- **Lombok** - Réduction du code boilerplate
- **Jakarta Validation** - Validation des données
- **Maven** - Gestion des dépendances

## Architecture

### Base de Données: G_Commercial

#### Tables:

1. **produits_prix**
   - `codepdt` (INT, PRIMARY KEY) - Code produit
   - `nompdt` (VARCHAR(20)) - Nom du produit
   - `descpdt` (VARCHAR(200)) - Description du produit
   - `prixpdt` (INT) - Prix du produit

2. **tous_commandes**
   - `codetouscmd` (INT, PRIMARY KEY, AUTO_INCREMENT) - Code de la commande globale
   - `codecmd` (INT) - Code de la commande locale
   - `client` (VARCHAR(20)) - Nom du client
   - `codepdt` (INT) - Code du produit
   - `qtecmd` (INT) - Quantité commandée
   - `datecmd` (DATE) - Date de la commande

## Endpoints REST

### Port: 8082
### Base URL: http://localhost:8082/commercial

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/commercial/produits/all` | Retourne tous les produits avec leurs prix |
| POST | `/commercial/commandes/add` | Ajoute une nouvelle commande |
| GET | `/commercial/commandes/all` | Retourne toutes les commandes |

### Exemples de Requêtes

#### 1. GET - Récupérer tous les produits
```bash
curl -X GET http://localhost:8082/commercial/produits/all
```

**Réponse:**
```json
[
  {
    "codePdt": 1,
    "nomPdt": "Laptop HP",
    "descPdt": "HP Pavilion 15-inch, Intel Core i5, 8GB RAM, 256GB SSD",
    "prixPdt": 6500
  },
  {
    "codePdt": 2,
    "nomPdt": "Mouse Logitech",
    "descPdt": "Logitech MX Master 3 Wireless Mouse",
    "prixPdt": 850
  }
]
```

#### 2. POST - Ajouter une commande
```bash
curl -X POST http://localhost:8082/commercial/commandes/add \
  -H "Content-Type: application/json" \
  -d '{
    "codeCmd": 101,
    "client": "Mohamed Ali",
    "codePdt": 1,
    "qteCmd": 2,
    "dateCmd": "2025-12-15"
  }'
```

**Réponse:**
```json
{
  "codeTousCmd": 1,
  "codeCmd": 101,
  "client": "Mohamed Ali",
  "codePdt": 1,
  "qteCmd": 2,
  "dateCmd": "2025-12-15"
}
```

## Installation et Configuration

### Prérequis
- Java 17 ou supérieur
- MySQL 8.0 ou supérieur
- Maven 3.6 ou supérieur

### Étapes d'installation

#### 1. Créer la base de données
Exécutez le script SQL situé dans `database/schema.sql`:

```bash
mysql -u root -p < database/schema.sql
```

Ou manuellement:
```sql
-- Se connecter à MySQL
mysql -u root -p

-- Exécuter le script
source database/schema.sql
```

#### 2. Configurer la connexion à la base de données
Modifiez le fichier `src/main/resources/application.properties` si nécessaire:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/G_commercial
spring.datasource.username=root
spring.datasource.password=VOTRE_MOT_DE_PASSE
```

#### 3. Compiler le projet
```bash
./mvnw clean compile
```

#### 4. Lancer l'application
```bash
./mvnw spring-boot:run
```

L'application sera accessible sur: `http://localhost:8082`

## Structure du Projet

```
gestion_commerciel/
├── src/
│   ├── main/
│   │   ├── java/com/commercial/Commercial/
│   │   │   ├── Config/
│   │   │   │   └── WebConfig.java
│   │   │   ├── Controller/
│   │   │   │   └── CommercialController.java
│   │   │   ├── Dto/
│   │   │   │   └── CommandeRequest.java
│   │   │   ├── Exception/
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── Models/
│   │   │   │   ├── Produits_Prix.java
│   │   │   │   └── Tous_Commandes.java
│   │   │   ├── Repository/
│   │   │   │   ├── ProduitsPrixRepository.java
│   │   │   │   └── TousCommandesRepository.java
│   │   │   ├── Service/
│   │   │   │   ├── CommandeService.java
│   │   │   │   └── ProduitService.java
│   │   │   └── GestionCommercielApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/commercial/Commercial/
│           └── GestionCommercielApplicationTests.java
├── database/
│   └── schema.sql
├── pom.xml
└── README.md
```

## Bonnes Pratiques Implémentées

### Architecture MVC
- **Model**: Entités JPA avec validation (`Produits_Prix`, `Tous_Commandes`)
- **Service**: Logique métier (`ProduitService`, `CommandeService`)
- **Controller**: API REST (`CommercialController`)

### Design Patterns
- **Repository Pattern**: Accès aux données via Spring Data JPA
- **DTO Pattern**: Séparation des données de requête (`CommandeRequest`)
- **Dependency Injection**: Utilisation de `@RequiredArgsConstructor` avec Lombok

### Validation
- Validation des données avec Jakarta Validation
- Gestion globale des erreurs avec `@RestControllerAdvice`
- Messages d'erreur personnalisés

### Logging
- Utilisation de SLF4J avec Lombok (`@Slf4j`)
- Logs détaillés pour le suivi des opérations

### CORS
- Configuration CORS pour permettre les appels depuis `gestion_vente`

## Consommation par gestion_vente

Ce microservice sera consommé par l'application `gestion_vente` via des Web Services pour:
1. Récupérer les prix des produits
2. Enregistrer les commandes effectuées

## Tests

Pour exécuter les tests (nécessite que la base de données soit créée):
```bash
./mvnw test
```

## Auteur
Projet J2EE 5IIR - EMSI 2025

## Licence
Ce projet est réalisé dans un cadre académique.
