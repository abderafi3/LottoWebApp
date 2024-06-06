# Lotto Webapp Simulator

You can find the live deployment of this Webapp at this [link](https://lotto.up.railway.app/).

The Lotto Webapp Simulator is a web application built with Java Spring Boot and PostgreSQL. 
It allows users to choose lotto numbers, submit tickets, and check the results.
The application performs draws twice a week and notifies users via email if their ticket matches certain criteria.

## Features

- User-friendly UI for selecting and submitting lotto numbers.
- Stores submitted tickets in a PostgreSQL database.
- Performs draws twice a week (Wednesday and Saturday).
- Compares submitted numbers against drawn numbers.
- Notifies users via email if they win.
- Allows users to check ticket results using a unique ticket number.
- Populates the database with dummy data for testing purposes.

## Technologies Used

- Java Spring Boot
- PostgreSQL
- Thymeleaf (for templating)
- Bootstrap (for responsive design)
- Docker (for containerization)
- Docker Compose (for orchestrating containers)

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker
- Docker Compose

### Setting Up the Project

1. **Clone the repository:**

   ```bash
   git clone git@github.com:abderafi3/LottoWebApp.git
   cd LottoWebApp
2. **Build the project:**
   ```bash
    ./mvnw clean package
   
4. **Run Docker Compose:**
   ```bash
   docker-compose up --build

## Accessing the Application

Once the application is running, you can access it in your web browser at: http://localhost:8080

## Usage

### Select Lotto Numbers:
Go to the "Play Lotto" page and select 6 numbers.
Enter your email (optional) and submit the ticket.

### Check Ticket Results:
Go to the "Check Results" page and enter your ticket number to see if you have won.
### View Draw Information:
On the homepage, view the last draw numbers and the date of the next draw.

