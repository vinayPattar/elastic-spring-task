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
http://localhost:8080/course
```

### 🔎 Search Courses
```
GET /course/search?params
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
GET /course/search?q={keyword}&minAge=5&maxPrice=50&page=0&size=5&sort=priceAsc
```

**Example:**
```bash
curl "http://localhost:8080/course/search?q=sci&minAge=5&maxPrice=50&page=0&size=5&sort=priceAsc"
```

Response:
```json
["Young Scientists Club"]
```

---

### 🐛 Fuzzy Search (Typo-Friendly)
```
GET course/search?q=sci&minAge=5&maxPrice=50&page=0&size=5&sort=priceAsc
```

**Example:**
```bash
curl "http://localhost:8080/course/search?q=scence&minAge=5&maxPrice=50&page=0&size=5&sort=priceAsc"
```

Response:
```json
[
  {
    "id": 3,
    "title": "Young Scientists Club",
    "description": "Weekly club sessions with interactive science experiments and discussions.",
    "category": "Science",
    "type": "CLUB",
    "gradeRange": "3rd-5th",
    "minAge": 8,
    "maxAge": 11,
    "price": 1200.0,
    "nextSessionDate": "2025-07-20T14:00:00Z"
  }
]
```

---

## 🏗️ Project Structure
```
├── util            # Logic to load the resource data at the starting
├── controller      # REST Controllers
├── model           # Elasticsearch Document Models
├── repository      # Spring Data Elasticsearch Repositories
├── service         # Business Logic to handle REST controllers and Search controls
├── Dto             # Structured response to the search query
└── resources
    └── sample-courses.json
```

---

## ✅ Manual Check Steps
```bash
# 1️⃣ Start Elasticsearch
docker-compose up -d

# 3️⃣ Confirm index
curl http://localhost:9200/course/_count

# 4️⃣ Test endpoints
curl http://localhost:8080/course/search?q=scence&minAge=5&maxPrice=1500&category=&sort=priceAsc&page=0&size=10
curl http://localhost:8080/course/search?q=scence&minAge=5&maxPrice=1500&category=&sort=priceAsc&page=1&size=5
curl http://localhost:8080/course/add-course
curl http://localhost:8080/course/update-course
curl http://localhost:8080/course/delete-course
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

