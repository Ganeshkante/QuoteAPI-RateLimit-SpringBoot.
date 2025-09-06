# Quote API with Rate Limiting (Spring Boot)

## 📌 Project Overview
This is a simple **RESTful API** built with **Spring Boot** that returns random inspirational quotes.  
To prevent abuse, the API includes **IP-based rate limiting**: each client IP can make only **5 requests per minute**.

---

## 🚀 Features
- ✅ REST API endpoint: `GET /api/quote`
- ✅ Returns a random inspirational quote in JSON
- ✅ IP-based rate limiting (5 requests/minute)
- ✅ Returns **HTTP 429** with retry time if limit exceeded
- ✅ Thread-safe implementation using `ConcurrentHashMap`
- ✅ Logs each request (IP + status)
- ✅ Swagger/OpenAPI documentation included

---

## 🛠️ Tech Stack
- Java 21
- Spring Boot
- Maven
- Swagger (OpenAPI)
- In-memory rate limiting (`ConcurrentHashMap`)

---

## ⚙️ Setup Instructions

1. Clone the repository:
   ```sh
   git clone https://github.com/Ganeshkante/QuoteAPI-RateLimit-SpringBoot.git
   cd QuoteAPI-RateLimit-SpringBoot
