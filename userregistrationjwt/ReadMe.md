#  Spring Boot User Register and JWT Authentication API

A RESTful API built using **Spring Boot** for user registration, login, and authentication using **JWT (JSON Web Token)**.
---

##  Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **MySQL**
- **Maven**

---

## Project Structure

src/
├── config/
│ └── JwtFilter.java # Intercepts HTTP requests to validate JWT
│ └── JwtUtil.java # Utility for token generation and validation
| └──SecurityConfig.java # Configurations for bean creation of spring security
├── controller/
│ └── AuthController.java # APIs for driving login and registration
├── entity/
│ └── User.java # JPA Entity for user data
├── DTOs/
|  └── dto for LoginRequest,RegisterRequest,AuthenticationResponse.java
├── repository/
│ └── UserRepository.java # JPA Repository for users
├── service/
│ └── CustomUserDetailsService.java # Fetches user details from DB
└── UserregistrationjwtApplication.java

##  API Endpoints

| Method | Endpoint         | Description                |
|--------|------------------|----------------------------|
| POST   | `/auth/register` | Register new user          |
| POST   | `/auth/login`    | Login & receive JWT token  |

| Method | Endpoint     | Description            |
|--------|--------------|------------------------|
| GET    | `/users/me`  | Get logged-in user info |

Note : Add this header(token will be generated from login api):  
`Authorization: Bearer <jwt-token>`

Test: 


1. User Registration
URL: POST /api/register
Description: Register a new user.

Request Body:

{
  "username": "testuser",
  "password": "test123",
  "email": "test@example.com"
}
Response:
{
  "message": "User registered successfully"
}

2. User Login (Generate JWT)
URL: POST /api/login
Description: Authenticate user and return JWT token.

Request Body:

{
   "email": "test@example.com",
  "password": "test123"
}
Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsIn..."
}
3. Get Current User Info (Secure with JWT)
URL: GET /api/user/me
Headers:

Authorization: Bearer <JWT_TOKEN>
Response:
{
  "id": 1,
  "username": "testuser",
  "email": "test@example.com"
}
