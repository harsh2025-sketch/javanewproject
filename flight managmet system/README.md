# Flight Reservation System

![Flight logo](./logo.svg)

[![Java](https://img.shields.io/badge/Java-21%20LTS-orange.svg)](https://adoptium.net/temurin/releases/?version=21)
[![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-10-blue.svg)](https://jakarta.ee/specifications/platform/10/)
[![Maven](https://img.shields.io/badge/Maven-3.11%2B-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A modern Flight Reservation System built with Java 21, Jakarta EE 10, featuring flight booking, passenger management, and airline operations.

## âœ¨ Features

- ðŸ” **Flight Search & Booking** - Search flights with real-time seat availability
- ðŸ‘¥ **Passenger Management** - XML-based passenger data storage
- ðŸŽ« **Ticket Management** - Electronic tickets with unique booking IDs
- ðŸ“… **Schedule Management** - Real-time flight updates and notifications
- ðŸ’° **Fare Calculation** - Automatic fare calculation by class
- ðŸ’³ **Payment Integration** - Secure payment gateway support
- ðŸ“Š **Booking History** - Complete booking history tracking
- ðŸ” **User Roles** - Customer and Admin role management
- ðŸ“§ **Notifications** - Email/SMS for bookings and alerts
- ðŸ“ˆ **Reports & Analytics** - Revenue and booking analytics

## ðŸš€ Quick Start

### Prerequisites

- **Java 21 (LTS)** - [Download](https://adoptium.net/temurin/releases/?version=21)
- **Maven 3.9+** - [Download](https://maven.apache.org/download.cgi)
- **Tomcat 10.1+** - [Download](https://tomcat.apache.org/download-10.cgi)

### Build & Run

```bash
# Clone the repository
git clone <repository-url>
cd flight-reservation-system

# Build the project
mvn clean install

# Deploy to Tomcat
copy target\flight-reservation-system.war <TOMCAT_HOME>\webapps\

# Start Tomcat and access
# http://localhost:8080/flight-reservation-system
```

ðŸ“– **Detailed Setup**: See [docs/SETUP.md](docs/SETUP.md) for complete installation instructions.

## ðŸ› ï¸ Technology Stack

- **Backend**: Java 21 (LTS), Jakarta Servlets 6.0, Jakarta JSP 3.1
- **Data Storage**: XML with DOM4J 2.1.4
- **Build Tool**: Maven 3.11+
- **Email**: Jakarta Mail API 2.0
- **JSON**: Gson 2.10
- **Logging**: SLF4J 2.0
- **Testing**: JUnit 5 (Jupiter)

## ðŸ“ Project Structure

```
flight-reservation-system/
â”œâ”€â”€ src/
â”‚   â”œâ”€â”€ main/
â”‚   â”‚   â”œâ”€â”€ java/
â”‚   â”‚   â”‚   â””â”€â”€ com/nimbus/frs/
â”‚   â”‚   â”‚       â”œâ”€â”€ model/          # Domain entities
â”‚   â”‚   â”‚       â””â”€â”€ util/           # Utilities & XML handlers
â”‚   â”‚   â””â”€â”€ webapp/
â”‚   â”‚       â””â”€â”€ WEB-INF/
â”‚   â”‚           â””â”€â”€ web.xml         # Web deployment descriptor
â”‚   â””â”€â”€ test/                       # Unit tests
â”œâ”€â”€ docs/                           # Documentation
â”œâ”€â”€ pom.xml                         # Maven configuration
â””â”€â”€ README.md                       # This file
```

## Setup Instructions

### Prerequisites
- **Java JDK 21 (LTS)** - [Download Temurin 21](https://adoptium.net/temurin/releases/?version=21) or [Oracle JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- **Apache Maven 3.9+** - [Download](https://maven.apache.org/download.cgi)
- **Jakarta EE 10 Compatible Server**:
  - Apache Tomcat 10.1+ (Recommended) - [Download](https://tomcat.apache.org/download-10.cgi)
  - Jetty 12.x or later
  - WildFly 27+ or later
  - GlassFish 7.x or later
- **IDE** (Eclipse 2023-06+, IntelliJ IDEA 2023.2+, or VS Code with Java extensions)

> âš ï¸ **Important**: This project requires Jakarta EE 10 support. Older servers (Tomcat 9.x, Java EE 8 servers) will NOT work.

## ðŸ“š Documentation

- **[Setup Guide](docs/SETUP.md)** - Complete installation and configuration
- **[Java 21 Upgrade](docs/UPGRADE.md)** - Details about Java 21 migration
- **[Jakarta EE Migration](docs/JAKARTA_MIGRATION.md)** - javax â†’ jakarta migration guide
- **[API Documentation](docs/API.md)** - REST endpoints and usage

## ðŸ” Default Credentials

### Customer Account
```
Username: customer@nimbus.com
Password: customer123
```

### Admin Account
```
Username: admin@nimbus.com
Password: admin123
```

## ðŸ§ª Testing

```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report
```

## ðŸ¤ Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## ðŸ“ License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## ðŸ‘¥ Authors

- Flight Reservation System Team

## ðŸ™ Acknowledgments

- Built with Java 21 and Jakarta EE 10
- Inspired by modern airline reservation systems

## ðŸ“® Support

For issues and questions:
- Open an [Issue](../../issues)
- Check [Documentation](docs/)

---

**Version**: 2.0  
**Last Updated**: November 2025  
**Status**: âœ… Production Ready

