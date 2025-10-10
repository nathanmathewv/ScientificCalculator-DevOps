# Scientific Calculator - DevOps Project

A scientific calculator built with Java 17, Maven, Docker, and Jenkins CI/CD pipeline. Features comprehensive testing, code coverage, and automated deployment with Ansible.

## Features

### Basic Operations
- Addition, Subtraction, Multiplication, Division
- Decimal point support
- Sign change (positive/negative)
- Backspace and clear functions

### Scientific Functions
- **Square Root (√)**: Calculate square root of a number
- **Power (x^y)**: Raise a number to any power
- **Factorial (n!)**: Calculate factorial of an integer
- **Natural Logarithm (ln)**: Calculate natural logarithm (base e)

## Technology Stack

- **Language**: Java 17
- **Build Tool**: Maven 3.9
- **Testing**: JUnit 5.9.3
- **Code Coverage**: JaCoCo 0.8.10
- **Containerization**: Docker
- **CI/CD**: Jenkins
- **Deployment**: Ansible
- **Version Control**: Git/GitHub

## Prerequisites

- Java 17 or higher
- Maven 3.9 or higher
- Docker
- Jenkins (for CI/CD)
- Ansible (for deployment)

## Project Structure

```
ScientificCalculator-DevOps/
├── src/
│   ├── main/java/com/calculator/
│   │   ├── Calculator.java      # Core calculator logic
│   │   └── CalculatorGUI.java   # Swing GUI
│   └── test/java/com/calculator/
│       └── CalculatorTest.java  # JUnit 5 tests (40+ test cases)
├── ansible/
│   ├── inventory.ini            # Ansible inventory
│   └── deploy.yml               # Deployment playbook
├── target/                      # Build artifacts
├── pom.xml                      # Maven configuration
├── Dockerfile                   # Container definition
├── Jenkinsfile                  # CI/CD pipeline
├── docker-compose.yml           # Docker compose config
├── JENKINS_SETUP.md            # Jenkins setup guide
└── README.md                    # This file
```

## Local Development

### Build the Project

```bash
# Compile
mvn clean compile

# Run tests
mvn test

# Generate code coverage report
mvn clean test jacoco:report
# Report: target/site/jacoco/index.html

# Package as JAR
mvn clean package
```

### Run the Calculator

```bash
# Option 1: Run with Maven
mvn exec:java -Dexec.mainClass="com.calculator.CalculatorGUI"

# Option 2: Run the JAR file
java -jar target/scientific-calculator-1.0.0.jar

# Option 3: Run compiled classes
mvn compile
java -cp target/classes com.calculator.CalculatorGUI
```

## Docker

### Build Docker Image

```bash
docker build -t scientific-calculator-java .
```

### Run Container

```bash
docker run -d -p 8080:8080 scientific-calculator-java
```

### Using Docker Compose

```bash
docker-compose up -d
```

## CI/CD Pipeline

The Jenkins pipeline automates the following stages:

1. **Checkout**: Pull code from GitHub
2. **Build & Test**: Compile and run tests with Maven
3. **Build Docker Image**: Create containerized application
4. **Login to DockerHub**: Authenticate with Docker registry
5. **Push Image**: Upload image to DockerHub
6. **Deploy**: Deploy using Ansible playbook

### Pipeline Configuration

- **Jenkins Credentials Required**: 
  - `dockerhub-creds`: DockerHub username and password
  
- **Email Notifications**: 
  - Success/Failure emails sent to configured address

See [JENKINS_SETUP.md](JENKINS_SETUP.md) for detailed setup instructions.

## Testing

### Test Coverage

The project includes 40+ comprehensive JUnit 5 tests:

- **Square Root Tests**: Positive numbers, zero, negatives, large values
- **Factorial Tests**: Integers, zero, negatives, edge cases
- **Natural Logarithm Tests**: Positive numbers, decimals, error cases
- **Power Function Tests**: Positive/negative exponents, fractions, zero
- **Basic Operations**: Arithmetic operations and division by zero

### Run Tests

```bash
# Run all tests
mvn test

# Generate coverage report
mvn clean test jacoco:report

# View test report
mvn surefire-report:report
```

## Deployment

### Ansible Deployment

The project uses Ansible for automated deployment:

```bash
# Deploy to configured servers
ansible-playbook -i ansible/inventory.ini ansible/deploy.yml
```

The deployment playbook:
- Stops existing containers
- Pulls latest Docker image
- Starts new container with proper configuration
- Verifies deployment health

## Maven Commands

| Command | Description |
|---------|-------------|
| `mvn clean` | Clean build artifacts |
| `mvn compile` | Compile source code |
| `mvn test` | Run unit tests |
| `mvn package` | Create JAR file |
| `mvn clean install` | Full build and install |
| `mvn jacoco:report` | Generate code coverage report |
| `mvn surefire-report:report` | Generate test report |

## License

This project is for educational purposes as part of CSE 816 SPE coursework.

## Repository

- **GitHub**: [nathanmathewv/ScientificCalculator-DevOps](https://github.com/nathanmathewv/ScientificCalculator-DevOps)
- **Branch**: main