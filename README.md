# 🏦 Bank Management System (Spring Boot)

A complete **Bank Account Management System** built with **Spring Boot**, featuring account creation, deposits, withdrawals, transaction tracking, and customer management.  
The project demonstrates **RESTful API design**, **DTO mapping**, **JPA entity relationships**, **validation**, **exception handling**, and **best practices** for backend development.

---

## 📌 Features

- **Customer Management**
  - Create, read, update, delete customers
  - Search by email
- **Bank Account Management**
  - Create accounts
  - Deposit & withdraw money
  - Search accounts by type or balance range
  - Transfer money between accounts
- **Transaction Management**
  - Track deposits, withdrawals, and transfers
  - Delete old transactions
  - Get transactions by account
- **Advanced Filtering**
  - Custom finder methods
  - Complex queries with `@Query`
- **DTO & Mapping**
  - Entity ↔ DTO conversion using **ModelMapper** mapping
  - Avoid circular JSON references
- **Validation**
  - `@Valid`, `@NotNull`, `@Email`, etc.
- **Exception Handling**
  - Global exception handler (`@ControllerAdvice`)
  - Custom error responses with `ResponseEntity`
- **Database**
  - Supports **MySQL**

---

## 🗂 Project Structure
```
bankmanagementsystem/
├── src/main/java/com/sprints/bankmanagementsystem
│   ├── BankApplication.java
│   ├── controller/
│   │   ├── CustomerController.java
│   │   ├── BankAccountController.java
│   │   └── TransactionController.java
│   ├── dto/
│   │   ├── CustomerDTO.java
│   │   ├── BankAccountDTO.java
│   │   └── TransactionDTO.java
│   ├── entity/
│   │   ├── Customer.java
│   │   ├── BankAccount.java
│   │   └── Transaction.java
│   ├── repository/
│   │   ├── CustomerRepository.java
│   │   ├── BankAccountRepository.java
│   │   └── TransactionRepository.java
│   ├── service/
│   │   ├── CustomerService.java
│   │   ├── BankAccountService.java
│   │   └── TransactionService.java
│   └── mapper/
│   │   ├── CustomerMapper.java
│   │   ├── BankAccountMapper.java
│   │   └── TransactionMapper.java
│   ├── exception/ 
│   │   ├── CustomErrorResponse.java
│   │   ├── ConflictException.java
│   │   ├── DataNotFoundException.java
│   ├── configration/ 
│   │   ├── ControllerAdvice.java
│   │   ├── MapperConfig.java
├── src/main/resources/
│   ├── application.properties
├── pom.xml
```
---

## 🛠 Technologies Used

- **Java 17+**
- **Spring Boot** (Web, Data JPA, Validation)
- **MySQL**
- **ModelMapper**
- **Maven**
- **Postman**(Testing APIs)

---

## 🛠️ How to Set Up

### 1️⃣ Clone the repository
```bash
git clone https://github.com/Eng-AmanyMohamed/Bank-Management-System.git
cd Bank-Management-System
```
### 2️⃣ Configure the database
- Edit application.properties (for MySQL):
```
spring.datasource.url=jdbc:mysql://localhost:3306/bank_system
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 3️⃣ Build & Run
```
mvn spring-boot:run
```
---
## 📍 API Endpoints
**Customer**
```
POST /customers	               #Create a customer
GET	 /customers	               #List all customers
GET	 /customers/{id}	         #Get customer by ID
PUT	 /customers/{id}	         #Update customer
DELETE	/customers/{id}	       #Delete customer
GET	 /customers/email/{email}	 #Find by email
```

**Bank Account**
```
POST	/accounts	                  #Create account
GET	 /accounts/{id}	              #Get account by ID
GET	 /accounts	                  #List accounts
GET	 /accounts?minBalance=1000	  #Filter by min balance
POST	/accounts/deposit	          #Deposit money
POST	/accounts/withdraw	        #Withdraw money
POST	/accounts/transfer	        #Transfer between accounts
```
**Transaction**
```
GET	/transactions/account/{accountId} 	#Get transactions by account
DELETE	/transactions/{id}	            #Delete transaction
```
to see all end points and Dto Schemas run the application and use 
```
http://localhost:8080/swagger-ui/index.html
```
---
## Team Members
This project was a collaborative effort with an outstanding team:
- Amany Mohamed
- Mohamed Mostafa
- Youssef Ahmed
- Touka Mohamed
- Leena Hesham
- Esraa Tarek
