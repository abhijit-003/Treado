# Treado Application

This is a **Spring Boot-based trading application** with a comprehensive set of features for stock market trading. The application integrates various technologies such as Spring Security, JWT, MySQL, React, Tailwind CSS, Redux, and APIs like Gemini and CoinGecko to deliver a seamless trading experience.

## Features

- **Stock Viewing:** View the latest stock prices from various companies.
- **Buy Stock:** Buy stocks directly from the platform.
- **Sell Stock:** Sell stocks and manage your portfolio.
- **View Wallet:** Check the balance of your wallet and its transaction history.
- **Send to Another Wallet:** Transfer funds or stocks to another user's wallet.
- **Gemini Chatbot:** A chatbot integrated with the Gemini API to assist with stock market queries and provide insights.

## Technologies Used

### Backend:
- **Spring Boot**: A powerful framework for building backend services.
- **Spring Security**: Used for securing the application with authentication and authorization.
- **JWT (JSON Web Tokens)**: Used for secure authentication and session management.
- **Java MailSender**: To send emails for user notifications and updates.
- **MySQL**: A relational database for storing user data, transactions, and stock details.

### Frontend:
- **React**: A JavaScript library for building the user interface.
- **Tailwind CSS**: A utility-first CSS framework for designing modern and responsive UIs.
- **Redux**: A state management library for handling the application's state in React.

### APIs:
- **Gemini API**: For stock market data and real-time stock information.
- **CoinGecko API**: To get cryptocurrency data and support trading of digital currencies.

## Installation

### Prerequisites

1. **Java 11+**
2. **MySQL** installed and running.
3. **Node.js** (for frontend React application).

### Steps

#### 1. **Clone the Repository:**

```bash
git clone https://github.com/abhijit-003/Treado.git
cd Treado
```

#### 2. **Backend Setup:**

- Navigate to the backend folder (`backend/`).
- Configure your `application.properties` to match your MySQL database credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tradingapp
spring.datasource.username=root
spring.datasource.password=root
```

- Run the backend server:

```bash
./mvnw spring-boot:run
```

This will start the backend application on `http://localhost:8080`.

#### 3. **Frontend Setup:**

- Navigate to the frontend folder (`frontend/`).
- Install the required dependencies:

```bash
npm install
```

- Run the frontend application:

```bash
npm start
```

This will start the frontend React application on `http://localhost:3000`.

#### 4. **Database Setup:**

- Ensure MySQL is running.
- Create the `tradingapp` database and set up the required tables for stock, user, transactions, etc.

#### 5. **JWT Authentication:**

- When you start the application, create a user account and login to generate a JWT token.
- Use this token for accessing secured endpoints (e.g., buy, sell, wallet).

## Usage

- **Login/Signup**: Create a new user account or login to an existing one to access the trading features.
- **Stock Viewing**: Use the 'View Stocks' section to check the prices of various stocks.
- **Buy and Sell**: Use the 'Buy Stock' and 'Sell Stock' features to manage your trading actions.
- **Wallet Management**: Check your wallet balance, view transaction history, and send funds to other users' wallets.
- **Gemini Chatbot**: Use the chatbot for stock market assistance powered by Gemini API.

## API Documentation

The application uses the following endpoints:

- `POST /api/auth/login`: User login (returns JWT token).
- `GET /api/stocks`: Fetch the list of available stocks.
- `POST /api/stocks/buy`: Buy stock (requires JWT token).
- `POST /api/stocks/sell`: Sell stock (requires JWT token).
- `GET /api/wallet`: View wallet balance and transactions (requires JWT token).
- `POST /api/wallet/send`: Transfer funds to another wallet (requires JWT token).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- **Gemini API**: Used for providing stock market data.
- **CoinGecko API**: Used for cryptocurrency market data.
- **Tailwind CSS**: For creating a responsive and clean UI.
- **React**: For building a dynamic and efficient frontend.
