🚕 Ride Booking Application — Uber Clone

A production-grade Ride Booking Backend built with Java and Spring Boot, designed to simulate real-world ride-hailing systems.

This project focuses on Low-Level System Design, clean service boundaries, and real-world engineering decisions — not just CRUD APIs.

---

🎯 Why This Project?

This project is built to replicate real backend challenges faced in ride-hailing platforms.

It demonstrates:
- Scalable backend design
- Real-world ride lifecycle handling
- Design pattern implementation in production scenarios
- Clean and maintainable service architecture

This is not a tutorial project — it is an engineering-focused implementation.

---
## 🏗️ System Design

### 📌 UML Diagram
[Download UML Diagram](https://github.com/user-attachments/files/26262560/Uber_UML_Diagram_6bee8f589b.pdf)

### 🔄 System Flow
<img width="100%" alt="System Flow" src="https://github.com/user-attachments/assets/85b17c8e-f950-48d4-9efc-26ed3b29ddbc" />

🏗️ System Architecture

User
├── Rider
├── Driver
└── Admin

RideRequest → Ride → Payment → Wallet
                    ↓
                 Rating

---

🚀 Features

- Complete ride lifecycle: request → accept → start → end
- OTP-based ride verification for secure ride start
- Driver matching using Strategy Pattern
- Geo-spatial queries using PostGIS
- Wallet and payment handling with transactional safety
- JWT Authentication & Role-based access
- Async email notifications
- Rating system
- Swagger API documentation

---

🔐 OTP Verification Flow

Driver accepts ride → OTP generated → sent to rider  
Driver starts ride → OTP validated  

- Match → Ride starts  
- Mismatch → Ride blocked  

Ensures real-world safety and prevents fraud.

---

🧩 Design Patterns Used

- Strategy Pattern → Driver Matching & Fare Calculation  
- Builder Pattern → Ride creation  
- Factory Pattern → Payment handling  
- Singleton (Spring IoC) → Dependency management  

---

🧠 Interview Talking Points

- Separation of RideRequest and Ride ensures clean lifecycle modeling  
- OTP validation enforces real-world constraints  
- Strategy Pattern enables extensibility  
- Transaction management ensures financial consistency  
- PostGIS enables efficient geo-spatial queries  

---

📦 Entity Model

User, RideRequest, Ride, Payment, Wallet, Rating

---

🔄 Ride Status Flow

REQUESTED → ACCEPTED → ONGOING → ENDED  
                 ↓  
            CANCELLED  

---

🛠️ Tech Stack

- Java 21  
- Spring Boot  
- Spring Security + JWT  
- PostgreSQL + PostGIS  
- Hibernate Spatial  
- Spring Data JPA  
- Swagger  
- Maven  

---

🚀 Getting Started

DEV Mode:
mvn spring-boot:run -Dspring.profiles.active=dev

PROD Mode:
CREATE EXTENSION postgis;


--------

🔗 API Highlights

- POST /rider/requestRide  
- POST /driver/acceptRide  
- POST /driver/startRide  
- POST /driver/endRide  

---

🗺️ Future Enhancements

- Real-time tracking (WebSockets)
- Surge pricing improvements
- Multi-city scaling
- Ride scheduling

---

🤝 Connect

Your Name  
LinkedIn: https://www.linkedin.com/in/bhawesh-verma-49b524218 
GitHub: https://github.com/BhaweshVerma123  

⭐ If you found this interesting, feel free to connect or give a star!

