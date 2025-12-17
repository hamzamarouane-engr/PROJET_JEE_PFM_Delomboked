# Gestion Stock - Microservice

## Description
Microservice REST pour la gestion des stocks (gestion_stock) dans le cadre du projet J2EE/Microservices - **Gestion commerciale par points de vente**.

Ce microservice gère les quantités de produits en stock et permet de déduire les quantités lors des ventes effectuées par les points de vente.

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

### Base de Données: G_Stock

#### Table:

**produits_stock**
- `codestock` (INT, PRIMARY KEY, AUTO_INCREMENT) - Code unique du stock
- `codepdt` (INT, UNIQUE) - Code du produit
- `qtepdt` (INT) - Quantité en stock

## Endpoints REST

### Port: 8083
### Base URL: http://localhost:8083/stock

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/stock/produits/all` | Retourne tous les produits en stock |
| PUT | `/stock/produits/subtract?codePdt={code}&qteCmd={qte}` | Soustrait une quantité du stock |
| GET | `/stock/produits/{codePdt}` | Retourne le stock d'un produit spécifique |

### Exemples de Requêtes

#### 1. GET - Récupérer tous les produits en stock
```bash
curl http://localhost:8083/stock/produits/all
```

**Réponse:**
```json
[
  {
    "codeStock": 1,
    "codePdt": 1,
    "qtePdt": 50
  },
  {
    "codeStock": 2,
    "codePdt": 2,
    "qtePdt": 100
  }
]
```

#### 2. PUT - Soustraire du stock
```bash
curl -X PUT "http://localhost:8083/stock/produits/subtract?codePdt=1&qteCmd=5"
```

**Réponse:**
```json
{
  "codeStock": 1,
  "codePdt": 1,
  "qtePdt": 45
}
```

#### 3. GET - Obtenir le stock d'un produit spécifique
```bash
curl http://localhost:8083/stock/produits/1
```

**Réponse:**
```json
{
  "codeStock": 1,
  "codePdt": 1,
  "qtePdt": 45
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
mysql -u root -p
source database/schema.sql
```

#### 2. Configurer la connexion à la base de données
Modifiez le fichier `src/main/resources/application.properties` si nécessaire:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/G_Stock
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

L'application sera accessible sur: `http://localhost:8083`

## Structure du Projet

```
gestion_stock/
├── src/
│   ├── main/
│   │   ├── java/com/stock/Stock/
│   │   │   ├── Config/
│   │   │   │   └── WebConfig.java
│   │   │   ├── Controller/
│   │   │   │   └── StockController.java
│   │   │   ├── Exception/
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── Models/
│   │   │   │   └── Produits_Stock.java
│   │   │   ├── Repository/
│   │   │   │   └── ProduitsStockRepository.java
│   │   │   ├── Service/
│   │   │   │   └── StockService.java
│   │   │   └── GestionStockApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/stock/Stock/
│           └── GestionStockApplicationTests.java
├── database/
│   └── schema.sql
├── pom.xml
└── README.md
```

## Logique Métier

### Soustraction de Stock
Lorsque `gestion_vente` effectue une vente:

1. Appel de `PUT /stock/produits/subtract?codePdt=X&qteCmd=Y`
2. Vérification que le produit existe
3. Vérification que la quantité en stock est suffisante
4. Soustraction de la quantité
5. Sauvegarde et retour du stock mis à jour

**Gestion d'erreurs:**
- ❌ Produit inexistant → HTTP 400 Bad Request
- ❌ Stock insuffisant → HTTP 400 Bad Request avec message d'erreur
- ✅ Succès → HTTP 200 OK avec nouveau stock

## Conformité avec PDF Requirements

| Requirement | Status |
|-------------|--------|
| Microservice Spring Boot | ✅ |
| REST API | ✅ |
| Spring Data REST | ✅ |
| Spring Data JPA | ✅ |
| MySQL Database G_Stock | ✅ |
| Table Produits_Stock | ✅ |
| GET /stock/produits/all | ✅ |
| PUT /stock/produits/subtract | ✅ |
| Port 8083 | ✅ |
| MVC Architecture | ✅ |
| Error Handling | ✅ |
| Validation | ✅ |
| Logging | ✅ |
| CORS Configuration | ✅ |

## Intégration avec gestion_vente

Ce microservice est consommé par `gestion_vente` pour:

1. **Afficher les produits disponibles:**
   ```javascript
   GET http://localhost:8083/stock/produits/all
   ```

2. **Mettre à jour le stock après une vente:**
   ```javascript
   PUT http://localhost:8083/stock/produits/subtract?codePdt=1&qteCmd=2
   ```

## Tests

Pour exécuter les tests:
```bash
./mvnw test
```

## Auteur
Projet J2EE 5IIR - EMSI 2025

## Licence
Ce projet est réalisé dans un cadre académique.
