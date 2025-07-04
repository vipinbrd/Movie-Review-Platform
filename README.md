# 🎬 Movie Review Platform

A full-stack movie review platform that allows users to register, browse movies, submit reviews, and rate them. Built using **Spring Boot 3**, **JWT-based Security**, and **MySQL** with role-based access.

---

## 🔗 Quick Links

- 🔄 **Postman Collection:** [Postman Collection](https://documenter.getpostman.com/view/23490471/2sB34bLizD)

- 🧪 **Swagger Docs:** http://localhost:8080/swagger-ui/index.html


## 🚀 Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security (JWT)**
- **Spring Data JPA + Hibernate**
- **MySQL** or **PostgreSQL**
- **Lombok**
- **OpenAPI / Swagger**
- **Postman** (API testing)

---

## 🧱 Features

- ✅ User registration & secure JWT login  
- ✅ Role-based authorization: `USER` and `ADMIN`  
- ✅ List, view, add, and delete movies  
- ✅ Users can review and rate movies  
- ✅ Admin can delete any review  
- ✅ DTO-based request/response  
- ✅ Global exception handling  
- ✅ Swagger UI + Postman collection

---

## 🧩 Entity Relationship Diagram

![ER Diagram](https://sewasarthiprodbbucket.s3.ap-south-1.amazonaws.com/ERD.png)


🧠 Entity Relationship & Design Justification
 I designed the database schema using clear relationships between the core entities:

User ↔ Review: One user can write many reviews. @OneToMany(mappedBy = "user") was used with cascade = ALL and orphanRemoval = true to ensure user-associated reviews are managed automatically (deleted when the user is removed).

Movie ↔ Review: A movie can have many reviews. Same cascade and orphan rules are used for clean deletion.

CascadeType.ALL: Used so that when a user or movie is deleted, all associated reviews are also deleted, preventing orphaned records.

OrphanRemoval = true: Ensures that if a review is removed from a movie or user, it is also deleted from the database.

---

## 🔐 Roles

- `USER`: Can register, login, view movies, submit reviews  
- `ADMIN`: All `USER` rights + can delete movies, reviews, manage content  

---

## 🧪 API Endpoints Overview

| Action              | Endpoint                | Method | Auth Role |
|---------------------|-------------------------|--------|-----------|
| Register User       | `/auth/register`        | POST   | Public    |
| Login User          | `/auth/login`           | POST   | Public    |
| Get All Movies      | `/movies`               | GET    | Public    |
| Get Movie by ID     | `/movies/{id}`          | GET    | Public    |
| Delete Movie By ID  | `/movies/{id}`         | DELETE | ADMIN     |
| Add Movie           | `/movies`               | POST   | ADMIN     |
| Submit Review       | `/reviews/{movieId}`    | POST   | USER      |
| Get Movie Reviews   | `/reviews/{movieId}`    | GET    | Public    |
| Delete Review       | `/reviews/{id}`         | DELETE | ADMIN     |

More details in the [Postman Collection](https://documenter.getpostman.com/view/23490471/2sB34bLizD)

---

## ⚙️ Getting Started

### 🧩 Prerequisites

- Java 17
- Maven
- MySQL

### 📦 Setup

```bash
# Clone the project
https://github.com/vipinbrd/Movie-Review-Platform.git
cd movie-review-platform

# Setup database credentials in src/main/resources/application.properties

# Build and run
mvn clean install
mvn spring-boot:run
📄 Swagger UI
Once the app is running:

👉 http://localhost:8080/swagger-ui/index.html

📤 Deployment
You can generate the .jar file using:
mvn clean package

Then run it using:
java -jar target/movie_review_platform-1.0.0.jar
