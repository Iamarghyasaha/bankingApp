
ARGHYA SAHA
12:04 (5 hours ago)
to me

# Banking App with Spring Boot

This is a simple banking application built using Spring Boot framework. The application allows users to create accounts, perform transactions such as deposit and withdrawal, and fetch account details.

## Features

- **Account Creation**: Users can create new accounts with a unique account number.
- **Fetch Account**: fetch any account with account id and get it's details.
- **Fetch All Accounts**: fetch all accounts and get it's details.
- **Deposit**: Account holders can deposit money into their accounts.
- **Withdrawal**: Account holders can withdraw money from their accounts, provided they have sufficient balance.
- **Delete an Account**:  delete any account with account id.
- **Transaction History**: Users can view their transaction history for each account.

## Technologies Used

- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: For data persistence and managing database interactions.
- **MySql Database**: In-memory database for storing account and transaction data.
- **Lombok**: To reduce boilerplate code in Java classes.

## Getting Started

### Prerequisites

- Java 8 or higher installed on your machine.
- Maven installed to build and run the application.

### Installation

1. Clone the repository: git clone https://github.com/your-username/banking-app.git

2. Navigate to the project directory:
   
3. Build the project using Maven:

4. Set up the MySql DataBase: We are using ddl-auto=update check the applitation properties
   
5. Run the application:

   
6. Access the application in your web browser at [http://localhost:8080](http://localhost:8080).

## API Endpoints

The following API endpoints are available:

- **POST /api/accounts**: Create a new account.
- **GET /api/accounts/{accountNumber}**: Get single account details by account number.
- **GET /api/accounts**: Get All account details in the DataBase.
- **PUT /api/accounts/{accountNumber}/deposit**: Deposit money into an account.
- **PUT /api/accounts/{accountNumber}/withdraw**: Withdraw money from an account.
- **DELETE /api/accounts/{accountNumber}**: delete an account.
- **GET /api/accounts/{accountNumber}/transactions**: Get transaction history for an account with timestamp.

For more details and to try out the APIs, you can access the Swagger UI at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Configuration

You can configure the application properties in the `application.properties` file located in the `src/main/resources` directory.

## Database

The application uses an MySql database by default. You can change the database configuration in the `application.properties` file if needed.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a pull request.

## License

This project is not licensed you can do whatever you want! hehe:) acctually I've tried to write readme file first time.
