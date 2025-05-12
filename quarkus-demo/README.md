# 📘 Quarkus ORM Backend

A modular Java backend project built with [Quarkus](https://quarkus.io/) and Maven, designed for high-performance and reactive applications.

---

## 🚀 Features

- ✅ Built with **Quarkus 3.21.4**
- 🧩 Modular Maven structure (`quarkus-orm`, `Book` module)
- 🔄 RESTful APIs for managing `Book` entities
- 🔐 Role-based access control (using `@RolesAllowed`)
- ⚙️ Panache ORM for simplified persistence
- 🔁 Reactive programming using **Mutiny Uni**
- 🧪 Unit testing with JUnit

---

## 🛠 Tech Stack

- Java 17
- Quarkus
- Hibernate ORM with Panache
- RESTEasy Reactive
- Maven
- Jakarta REST + Security

---

## 📦 Project Structure
```bash
#quarkus-be/
├── Book/ # Domain module for Book entity
├── src/ # Application source
│ └── main/java # Java source files
│ └── resources # application.properties, configs
├── pom.xml # Project configuration
└── README.md # Project documentation

```
---

## 🧪 Running the App

### Prerequisites

- Java 17+
- Maven 3.8+

### Commands

```bash
# Navigate to backend
cd quarkus-be

# Run in dev mode
./mvnw quarkus:dev

```
### 📬 API Overview

```bash

    POST /books - Create a new book

    PUT /books/{id} - Update an existing book

    GET /books/{id} - Get book by ID

    GET /books - List all books

```
 ## License

##### This project is licensed under the MIT License.
