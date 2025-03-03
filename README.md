# Limepay Application - Directors-Movies API

## Overview
This project fetches and processes movie data from an external API and provides two REST endpoints:

- `/api/movies?page=X` → Returns movies from the given page.
- `/api/directors?threshold=X` → Returns directors who have directed more than X movies.

## Technologies Used
- Java 17 + Spring Boot
- REST API (`RestTemplate`)
- JSON Processing (`Jackson`)
- Caching (`@Cacheable`)
- DTO Implementation (Data Transfer Objects)



## API Endpoints

### **Fetch Movie Data**
```
http://localhost:9090/api/movies?page=1
```
####  Response:
```json
{
  "page": 1,
  "perPage": 10,
  "total": 26,
  "totalPages": 3,
  "movies": [
    {
      "title": "Midnight in Paris",
      "year": "2011",
      "rated": "PG-13",
      "released": "10 Jun 2011",
      "runtime": "94 min",
      "genre": "Comedy, Fantasy, Romance",
      "director": "Woody Allen",
      "writer": "Woody Allen",
      "actors": "Owen Wilson, Rachel McAdams, Kathy Bates"
    },
    {
      "title": "The Hateful Eight",
      "year": "2015",
      "rated": "R",
      "released": "30 Dec 2015",
      "runtime": "168 min",
      "genre": "Crime, Drama, Mystery",
      "director": "Quentin Tarantino",
      "writer": "Quentin Tarantino",
      "actors": "Samuel L. Jackson, Kurt Russell, Jennifer Jason Leigh"
    },
    
    "AND MORE..."
  ]
}
```

### **Fetch Directors with More than "threshold" Movies**
```
http://localhost:9090/api/directors?threshold=4
```
####  Response:
```json
{
  "directors": [
    "Martin Scorsese",
    "Woody Allen"
  ]
}
```

## Additional Features Implemented
- **Implemented DTOs (Data Transfer Objects)** for cleaner response structure.
- **Added Caching (`@Cacheable`)** to improve performance and reduce redundant API calls.
- **Handled API pagination dynamically** to fetch all available movie data.
- **Sorted director names alphabetically** in the results.
- **Improved maintainability** by separating concerns between controller, service, and DTOs.

---

