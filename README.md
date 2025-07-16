# 📚 Spring Boot + Elasticsearch Course Search App

A modern, searchable **Course Catalog** built with **Spring Boot** & **Elasticsearch** for full-text search, filtering, pagination, autocomplete suggestions, and fuzzy search.

---

## 🚀 Features
- 🔍 Full-text search on `title` and `description`
- 🎯 Filters for `category`, `type`, `age range`, `price`, and `date`
- 🔄 Pagination & Sorting
- ✨ Autocomplete Suggestions
- 🐛 Fuzzy Search (typo-tolerant)

---

## 🐳 1️⃣ Launch Elasticsearch

### 📂 Docker Compose Setup
```yaml
version: '3.1'
services:
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.17.15
    container_name: elasticsearch
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
    ports:
      - "9200:9200"
    networks:
      - elastic
networks:
  elastic:
    driver: bridge
```

### 🚀 Run Elasticsearch
```bash
docker-compose up -d
```

### ✅ Verify Elasticsearch
```bash
curl http://localhost:9200
```

---

## ⚙️ 2️⃣ Build & Run Spring Boot App
```bash
./mvnw clean package
java -jar target/elastic-spring-task-0.0.1-SNAPSHOT.jar
```

or for dev:
```bash
./mvnw spring-boot:run
```

---

## 📥 3️⃣ Populate Sample Data
Data is under:
```
src/main/resources/sample-courses.json
```

➡️ **Auto-indexed on startup** to `course` index.  
Verify with:
```bash
curl http://localhost:9200/course/_count
```

---

## 🔍 4️⃣ API Endpoints (with Examples)

### Base URL
```http
http://localhost:8080/api/search
```

### 🔎 Search Courses
```
GET /api/search
```

| Param    | Purpose           |
|----------|-------------------|
| q        | Full-text keyword  |
| minAge   | Min age filter     |
| maxAge   | Max age filter     |
| category | Category filter    |
| type     | ONE_TIME, COURSE, CLUB |
| minPrice | Min price          |
| maxPrice | Max price          |
| startDate| Date after filter   |
| sort     | upcoming, priceAsc, priceDesc |
| page     | Page number (default 0) |
| size     | Page size (default 10) |

**Example:**
```bash
curl "http://localhost:8080/api/search?q=science&minAge=5&maxPrice=50&page=0&size=5&sort=priceAsc"
```

---

### ✨ Autocomplete Suggestions
```
GET /api/search/suggest?q={partialTitle}
```

**Example:**
```bash
curl "http://localhost:8080/api/search/suggest?q=phy"
```

Response:
```json
["Physics for Kids", "Physics Basics"]
```

---

### 🐛 Fuzzy Search (Typo-Friendly)
```
GET /api/search/fuzzy?q={keywordWithTypos}
```

**Example:**
```bash
curl "http://localhost:8080/api/search/fuzzy?q=dinors"
```

Response:
```json
[
  {
    "id": 12,
    "title": "Dinosaurs 101",
    "category": "Science",
    "price": 49.99,
    "nextSessionDate": "2025-06-01T10:00:00Z"
  }
]
```

---

## 🏗️ Project Structure
```
├── config          # Elasticsearch Configuration
├── controller      # REST Controllers
├── document        # Elasticsearch Document Models
├── repository      # Spring Data Elasticsearch Repositories
├── service         # Business Logic
└── resources
    └── sample-courses.json
```

---

## ✅ Manual Check Steps
```bash
# 1️⃣ Start Elasticsearch
docker-compose up -d

# 2️⃣ Run the app
./mvnw spring-boot:run

# 3️⃣ Confirm index
curl http://localhost:9200/course/_count

# 4️⃣ Test endpoints
curl http://localhost:8080/api/search?q=science
curl http://localhost:8080/api/search/suggest?q=phy
curl http://localhost:8080/api/search/fuzzy?q=dinors
```

---

## 🏁 Summary
| Feature       | Status   |
|---------------|----------|
| Full-text     | ✅ Done   |
| Filters       | ✅ Done   |
| Pagination    | ✅ Done   |
| Sorting       | ✅ Done   |
| Autocomplete  | ✅ Done   |
| Fuzzy Search  | ✅ Done   |

---

### 🏷️ Badges
![Java](https://img.shields.io/badge/Java-17+-brightgreen?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring-boot)
![Elasticsearch](https://img.shields.io/badge/Elasticsearch-7.x-blue?style=for-the-badge&logo=elasticsearch)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)
