# Gestion Vente - J2EE Web Application

## Project Overview

**gestion_vente** is a Spring Boot web application for managing sales orders. It integrates with two microservices (**gestion_commercial** and **gestion_stock**) to provide a complete sales management system with JWT authentication and PDF invoice generation.

## Key Features

- **User Authentication**: JWT-based security with role-based access control
- **Order Management**: Create, view, and cancel orders through web interface
- **Microservices Integration**: Consumes REST APIs from gestion_commercial and gestion_stock
- **PDF Generation**: Automatic invoice generation using iText7
- **Responsive UI**: Bootstrap 5-based Thymeleaf templates
- **RESTful API**: Complete REST API for external integrations

## Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| Spring Boot | 3.2.0 | Main framework |
| Spring Security | 3.2.0 | Authentication & Authorization |
| Spring Data JPA | 3.2.0 | Database access |
| Spring Web | 3.2.0 | MVC & REST |
| Thymeleaf | 3.2.0 | Template engine |
| JWT (jjwt) | 0.12.3 | Token-based authentication |
| iText7 | 8.0.2 | PDF generation |
| MySQL | 8.x | Database |
| Lombok | Latest | Reduce boilerplate |
| Bootstrap | 5.3.0 | Frontend styling |

## Project Structure

```
gestion_vente/
├── src/main/java/com/vente/Vente/
│   ├── Config/
│   │   ├── SecurityConfig.java          # Security configuration
│   │   └── WebConfig.java               # Web & WebClient configuration
│   ├── Controller/
│   │   ├── HomeController.java          # Home & Login pages
│   │   ├── AuthController.java          # Authentication endpoints
│   │   ├── CommandeController.java      # MVC order management
│   │   └── ApiController.java           # REST API endpoints
│   ├── DTO/
│   │   ├── CommandeRequest.java         # Order creation request
│   │   ├── CommandeResponse.java        # Order response
│   │   ├── ProduitDTO.java              # Product data from commercial service
│   │   ├── StockDTO.java                # Stock data from stock service
│   │   ├── LoginRequest.java            # Login request
│   │   └── LoginResponse.java           # Login response with JWT
│   ├── Exception/
│   │   ├── ErrorResponse.java           # Standard error response
│   │   └── GlobalExceptionHandler.java  # Global exception handling
│   ├── Models/
│   │   ├── Commandes.java               # Order entity
│   │   └── Users.java                   # User entity
│   ├── Repository/
│   │   ├── CommandesRepository.java     # Order repository
│   │   └── UsersRepository.java         # User repository
│   ├── Security/
│   │   ├── JwtUtil.java                 # JWT utility class
│   │   └── JwtAuthenticationFilter.java # JWT filter
│   ├── Service/
│   │   ├── CommercialService.java       # Commercial microservice client
│   │   ├── StockService.java            # Stock microservice client
│   │   ├── CommandeService.java         # Order business logic
│   │   ├── UserService.java             # User management
│   │   └── PDFService.java              # PDF invoice generation
│   └── GestionVenteApplication.java     # Main application class
├── src/main/resources/
│   ├── templates/
│   │   ├── login.html                   # Login page
│   │   ├── error.html                   # Error page
│   │   └── commandes/
│   │       ├── list.html                # Orders list
│   │       ├── create.html              # Create order form
│   │       └── detail.html              # Order details
│   └── application.properties           # Application configuration
├── database/
│   └── schema.sql                       # Database schema
├── pom.xml                              # Maven dependencies
└── README.md                            # This file
```

## Database Schema

### Database: G_Vente

#### Table: users
```sql
CREATE TABLE users (
    userid BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    fullname VARCHAR(100),
    email VARCHAR(100),
    createdat DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

#### Table: commandes
```sql
CREATE TABLE commandes (
    codecmd BIGINT PRIMARY KEY AUTO_INCREMENT,
    client VARCHAR(100) NOT NULL,
    codepdt INT NOT NULL,
    nompdt VARCHAR(100),
    qtecmd INT NOT NULL,
    prixunitaire INT,
    montanttotal INT,
    datecmd DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PENDING',
    createdby VARCHAR(50)
);
```

## Prerequisites

1. **MySQL Server** running on localhost:3306
2. **gestion_commercial** microservice running on port 8082
3. **gestion_stock** microservice running on port 8083
4. Java 17 or higher
5. Maven 3.6 or higher

## Configuration

### application.properties

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/G_Vente?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=

# JWT
jwt.secret=5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437
jwt.expiration=86400000

# Microservices
microservice.commercial.url=http://localhost:8082/commercial
microservice.stock.url=http://localhost:8083/stock
```

## How to Run

### 1. Start Prerequisites

```bash
# Start MySQL server
# Ensure it's running on localhost:3306

# Start gestion_commercial microservice (port 8082)
cd ../gestion_commercial
./mvnw spring-boot:run

# Start gestion_stock microservice (port 8083)
cd ../gestion_stock
./mvnw spring-boot:run
```

### 2. Run the Application

```bash
cd gestion_vente
./mvnw spring-boot:run
```

The application will start on **http://localhost:8080**

### 3. Access the Application

- **Web Interface**: http://localhost:8080
- **Login Page**: http://localhost:8080/login

### Default Credentials

| Username | Password | Role |
|----------|----------|------|
| admin | password123 | ROLE_ADMIN |
| user1 | password123 | ROLE_USER |
| user2 | password123 | ROLE_USER |

## API Endpoints

### Authentication

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/auth/login` | User login (returns JWT) | Public |
| GET | `/auth/logout` | User logout | Public |

### Web Interface (MVC)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/` | Home (redirects to /login) | Public |
| GET | `/login` | Login page | Public |
| GET | `/commandes` | List all orders | Required |
| GET | `/commandes/new` | Create order form | Required |
| POST | `/commandes/create` | Submit new order | Required |
| GET | `/commandes/{id}` | View order details | Required |
| GET | `/commandes/{id}/invoice` | Download PDF invoice | Required |
| POST | `/commandes/{id}/cancel` | Cancel order | Required |

### REST API

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/api/produits` | Get all products | Required |
| GET | `/api/produits/{codePdt}` | Get product by code | Required |
| GET | `/api/stock` | Get all stock | Required |
| GET | `/api/stock/{codePdt}` | Get stock by product | Required |
| GET | `/api/commandes` | Get all orders | Required |
| GET | `/api/commandes/{id}` | Get order by ID | Required |
| POST | `/api/commandes` | Create new order | Required |
| GET | `/api/commandes/client/{client}` | Get orders by client | Required |
| GET | `/api/commandes/status/{status}` | Get orders by status | Required |
| PUT | `/api/commandes/{id}/cancel` | Cancel order | Required |

## Usage Examples

### 1. Login (Web)

1. Navigate to http://localhost:8080
2. Enter username and password
3. Click "Se connecter"

### 2. Create Order (Web)

1. After login, click "Nouvelle Commande"
2. Enter client name
3. Select a product from dropdown
4. Enter quantity
5. Review summary
6. Click "Créer la Commande"

### 3. View Orders (Web)

1. Navigate to http://localhost:8080/commandes
2. View list of all orders
3. Click eye icon to view details
4. Click PDF icon to download invoice

### 4. API - Login & Create Order

```bash
# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}'

# Response: { "token": "eyJhbGc...", "username": "admin", "role": "ROLE_ADMIN" }

# Create Order (use token from login)
curl -X POST http://localhost:8080/api/commandes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"client":"Test Client","codePdt":1,"qteCmd":2}'
```

## Features in Detail

### 1. Order Creation Workflow

1. User selects product from dropdown (fetched from gestion_commercial)
2. System displays product info and calculates total
3. Upon submission:
   - Validates stock availability (gestion_stock)
   - Retrieves product price (gestion_commercial)
   - Subtracts stock (gestion_stock)
   - Saves order locally (G_Vente database)
   - Sends order to gestion_commercial
4. Returns order confirmation with invoice option

### 2. JWT Authentication

- Tokens expire after 24 hours (configurable)
- Tokens include username and roles
- Stateless authentication (no server-side sessions)
- Secure with HS256 algorithm

### 3. PDF Invoice Generation

- Professional invoice layout
- Includes company header (ASUSTEC)
- Product details with pricing
- VAT calculation (20%)
- Total TTC (all taxes included)
- Downloadable as PDF file

### 4. Microservices Integration

- **Commercial Service**: Product information and prices
- **Stock Service**: Stock availability and updates
- Uses Spring WebClient for non-blocking calls
- Graceful error handling when services are unavailable

## Error Handling

The application handles various error scenarios:

- **Insufficient Stock**: Returns user-friendly error message
- **Product Not Found**: 404 Not Found
- **Invalid Credentials**: 401 Unauthorized
- **Validation Errors**: 400 Bad Request with field errors
- **Microservice Unavailable**: 500 Internal Server Error with retry message

## Security Features

- **Password Encryption**: BCrypt with strength 10
- **JWT Token Validation**: On every request
- **CSRF Protection**: Disabled for REST API, enabled for web forms
- **Role-Based Access**: ADMIN and USER roles
- **Session Management**: Stateless (JWT-based)

## Testing

### Manual Testing Checklist

- [ ] Login with valid credentials
- [ ] Login with invalid credentials (should fail)
- [ ] Create order with valid data
- [ ] Create order with insufficient stock (should fail)
- [ ] View order details
- [ ] Download PDF invoice
- [ ] Cancel order
- [ ] Logout
- [ ] Access protected page without login (should redirect to login)

## Troubleshooting

### Application won't start

- Check MySQL is running on port 3306
- Verify database credentials in application.properties
- Ensure Java 17+ is installed

### Cannot create orders

- Verify gestion_commercial is running on port 8082
- Verify gestion_stock is running on port 8083
- Check microservice URLs in application.properties

### PDF generation fails

- Verify iText7 dependency in pom.xml
- Check PDFService logs for specific errors

### JWT token not working

- Verify jwt.secret is properly configured
- Check token hasn't expired
- Ensure Authorization header format: "Bearer {token}"

## Best Practices Implemented

- **MVC Architecture**: Clear separation of concerns
- **Repository Pattern**: Data access abstraction
- **Service Layer**: Business logic isolation
- **DTO Pattern**: API contract definitions
- **Dependency Injection**: Spring IoC container
- **Exception Handling**: Global exception handler
- **Validation**: Jakarta Bean Validation
- **Logging**: SLF4J with Logback
- **Transaction Management**: @Transactional where needed
- **Security**: Spring Security with JWT

## Future Enhancements

- [ ] User registration endpoint
- [ ] Email notifications for orders
- [ ] Order history and analytics
- [ ] Multi-language support (i18n)
- [ ] Export orders to Excel
- [ ] Admin dashboard with statistics
- [ ] Password reset functionality
- [ ] Remember me functionality

## License

This project is part of J2EE 5IIR - EMSI 2025 academic coursework.

## Support

For issues or questions, please contact the development team.

---

**Developed with Spring Boot 3.2.0 | Java 17 | MySQL 8**
