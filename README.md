# 🚗 FIPE Table API

> A Spring Boot CLI application that queries the FIPE vehicle price table, allowing users to search cars, motorcycles, and trucks by brand, model, and year — returning average market prices for all available years at once.

<p align="left">
  <img src="https://img.shields.io/badge/Java-26-red?style=for-the-badge" alt="Java 26" />
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen?style=for-the-badge" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Jackson-Databind-orange?style=for-the-badge" alt="Jackson" />
  <img src="https://img.shields.io/badge/API-FIPE-blue?style=for-the-badge" alt="FIPE API" />
</p>

---

## 📌 About the Project

This project was built as part of an Alura Java challenge.

Instead of just viewing prices year by year like the official FIPE website, this application lists the average market price for **all available years** of a chosen model at once.

The main goal is to practice Spring Boot fundamentals, REST API consumption, JSON deserialization with Jackson, and clean layered architecture in a real project.

---

## 🚀 Features

- 🚗 Search by vehicle type: cars, motorcycles, or trucks
- 🏷️ List all available brands for the chosen type
- 🔍 Filter models by name fragment (case-insensitive)
- 📅 Display average FIPE price for **all available years** of a model
- ⚙️ Clean layered architecture with separated responsibilities
- 🔄 Single reusable HTTP client for all API calls

---

## 🏗️ Architecture

```text
Main (CommandLineRunner — Application Entry Point)
│
├── Service Layer
│   └── FipeService          → Deserialization logic
│
├── Util Layer
│   └── ApiConsumer          → HTTP requests
│
└── Model Layer
    ├── Brand                → Brand data
    ├── Model                → Model and year data
    ├── ModelList            → Wrapper for models endpoint
    └── Vehicle              → Full vehicle pricing data
```

---

## 📦 Project Structure

```text
src/
└── main/
    └── java/
        └── com/project/tabelafip/
            ├── model/
            │   ├── Brand.java
            │   ├── Model.java
            │   ├── ModelList.java
            │   └── Vehicle.java
            ├── service/
            │   └── FipeService.java
            ├── util/
            │   └── ApiConsumer.java
            ├── principal/
            │   └── Main.java
            └── TabelafipApplication.java
```

---

## 🔎 How It Works

The application follows this flow:

```text
1. User selects vehicle type (car / motorcycle / truck)
2. App fetches and lists all brands
3. User selects a brand by code
4. App fetches and lists all models
5. User types a name fragment to filter models
6. User selects a model by code
7. App fetches all available years
8. App fetches and displays FIPE price for every year
```

---

## 💡 OOP Concepts Applied

### Records
All model classes are Java Records — immutable data carriers ideal for API response mapping.

```java
public record Brand(
    @JsonAlias("codigo") String code,
    @JsonAlias("nome") String name
) {}
```

### Generics
`FipeService` uses generic methods to deserialize any type without duplication:

```java
public <T> T parseJson(String json, Class<T> type) { ... }
public <T> List<T> parseJsonList(String json, Class<T> type) { ... }
```

### Dependency Injection
Spring manages all dependencies via `@Autowired`, `@Service`, and `@Component`.

---

## ▶️ How to Run

Clone the repository:

```bash
git clone https://github.com/Vinizeira/Fipe-table-api
cd Fipe-table-api
```

Run with Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Or run `TabelafipApplication.java` directly from IntelliJ IDEA.

---

## 🌐 API Reference

This project consumes the public FIPE API v1:

| Endpoint | Description |
|----------|-------------|
| `/carros/marcas` | List car brands |
| `/motos/marcas` | List motorcycle brands |
| `/caminhoes/marcas` | List truck brands |
| `/{type}/marcas/{brandId}/modelos` | List models by brand |
| `/{type}/marcas/{brandId}/modelos/{modelId}/anos` | List available years |
| `/{type}/marcas/{brandId}/modelos/{modelId}/anos/{yearId}` | Get price for year |

Base URL: `https://parallelum.com.br/fipe/api/v1`

---

## 🛠️ Technologies

- Java 26
- Spring Boot 4.0.6
- Jackson Databind
- Maven Wrapper
- FIPE Public API v1
- IntelliJ IDEA
- Git / GitHub

---

## 🗺️ Development Roadmap

### Level 1 — MVP
- [x] Project setup with Spring Boot
- [x] API consumer with Java HttpClient
- [x] JSON deserialization with Jackson
- [x] Brand listing by vehicle type
- [x] Model listing by brand
- [x] Model filter by name fragment
- [x] Price listing for all available years

### Future Expansion
- [ ] Export results to CSV or JSON file
- [ ] Add input validation and error messages
- [ ] Add logging
- [ ] Web interface with Spring Web

---

## 📈 Learning Goals

This project is used to practice:

- Spring Boot fundamentals
- REST API consumption
- JSON deserialization with Jackson
- Layered architecture
- Java Records
- Generics
- Dependency injection
- Clean code principles

---

## 🧠 Development Approach

```text
Learn concept → Apply in project → Refactor → Improve architecture
```

This project was built step by step, applying each concept as it was learned.

---

## 👨‍💻 Author

**Vinicius Pereira**

Java Backend Developer
