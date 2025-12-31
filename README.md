
# BillDesk

BillDesk – Billing System : 

📌 Overview BillDesk is a Spring Boot–based billing and management system that handles products, customers, invoices, payments, and reports in a structured and secure way.

🛠 Tech Stack  : Java, Spring Boot Spring Data JPA, Hibernate Spring Security (Role based - BCrypt) PostgreSQL / MySQL

👥 Roles Admin: Manages products, invoices, payments, and reports Customer: Views invoices and payment history

📦 Core Modules :  Product Management Invoice & Invoice Items Payment Processing , Report Generation (daily/monthly/annual)

🗄 Database Highlights : Normalized schema One-to-many relationships (Admin → Products, Customer → Invoices) Snapshot pricing in invoice items for historical accuracy

🔐 Security : Passwords stored using BCrypt hashing Role-based access control

🎯 Key Features ✅ Clean layered architecture ✅ DTO & Mapper pattern ✅ Transactional payment handling ✅ Scalable design
