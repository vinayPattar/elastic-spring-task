# Spring Boot + Elasticsearch Course Search App
A modern, searchable Course Catalog built with Spring Boot & Elasticsearch for full-text search, filtering, pagination, autocomplete suggestions, and fuzzy search.

🚀 Features
🔍 Full-text search on title and description

🎯 Filters for category, type, age range, price, and date

🔄 Pagination & Sorting

✨ Autocomplete Suggestions

🐛 Fuzzy Search (typo-tolerant)

# Launch Elasticsearch
📂 Docker Compose Setup

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

# Run Elasticsearch
docker-compose up -d

# Verify Elasticsearch
curl http://localhost:9200

# Populate Sample Data
src/main/resources/sample-courses.json

Auto-indexed on startup to course index.
Verify with:
curl http://localhost:9200/course/_count

# API Endpoints (with Examples)
Base URL
http://localhost:8080/api/search

# Search Courses
sql
Copy
Edit
GET /api/search
Param	Purpose
q	Full-text keyword
minAge	Min age filter
maxAge	Max age filter
category	Category filter
type	ONE_TIME, COURSE, CLUB
minPrice	Min price
maxPrice	Max price
startDate	Date after filter
sort	upcoming, priceAsc, priceDesc
page	Page number (default 0)
size	Page size (default 10)
