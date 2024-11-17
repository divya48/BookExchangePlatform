# BookExchangePlatform

# Backend
A Spring Boot-based backend application for a digital book exchange platform. This application allows users to register, list books for exchange, search for books by various criteria, and securely interact with other users for book exchanges. 

## Features

- **User Authentication**: Register, login, password recovery, and JWT-based session management.
- **Book Listing**: Users can list books they want to exchange or lend, including details like title, author, genre, and condition.
- **Book Search**: Users can search for books by title, author, genre, condition and availability.
- **Scalability**: Microservices architecture designed to support future expansion, including recommendation features.

## Tech Stack

- **Backend**: Java, Spring Boot, Spring Data JPA
- **Database**: MySQL
- **Authentication**: JWT
- **Build Tool**: Maven

## Setup Instructions

### Prerequisites

- Java 11
- Maven
- MySQL database
- Git

### Installation

1. **Clone the repository**:
   ```bash
   git clone 
   cd book-exchange-platform
   run "mvn clean install"
   start the application

### Frontend Application
The Book Exchange Platform frontend is a React-based single-page application (SPA) designed to provide an intuitive and user-friendly experience for book enthusiasts who want to exchange books with others. Users can register, browse available books, list their own books, and connect with other users interested in book exchanges.

## Features

- **User Authentication**: Register, login, password recovery, and JWT-based session management.
- **Book Listing**: Users can list books they want to exchange or lend, including details like title, author, genre, and condition.
- **Book Search**: Users can search for books by title, author, genre, condition and availability.
- **Scalability**: Microservices architecture designed to support future expansion, including recommendation features.

### Tech stack
React for the user interface
React Router for navigation
Redux for state management
Fetch for making API requests
JWT for user authentication and session management


### Installation

1. **Clone the repository**:
   ```bash
   git clone 
   cd book-exchange-platform
   npm install
   npm start
   The application should be running on http://localhost:3000
