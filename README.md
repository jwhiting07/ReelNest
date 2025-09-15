# ReelNest

ReelNest is a full-stack project management platform designed for filmmakers and creative teams. It allows users to create and manage projects, organize sections like writing, storyboarding, VFX, and more, and store project assets efficiently.

---

## 🛠 Tech Stack

- **Backend:** Spring Boot 3, Java 24
- **Database:** PostgreSQL 15 + Flyway for migrations
- **Authentication:** Spring Security
- **Cloud Storage:** AWS S3
- **Build Tool:** Maven
- **Other:** HikariCP connection pooling

---

## ⚡ Features

- User authentication and role-based access (`USER`, `ADMIN`)
- Project creation and management
- Section-based organization:
  - Writing
  - Storyboarding
  - Set Diagrams
  - Test Renders
  - Foley
  - Soundtrack
  - VFX
  - Color Grading
  - Legal
- AWS S3 integration for storing project assets
- Database versioning via Flyway

---

## 🚀 Getting Started

### Prerequisites

- Java 24
- Maven
- PostgreSQL 15
- AWS account (for S3 integration)

---

### Setup

1. **Clone the repo:**
```bash
git clone https://github.com/yourusername/ReelNest.git
cd ReelNest
```

2. Create PostgreSQL database:
```bash
CREATE DATABASE reelnestdb;
CREATE USER reelnest WITH PASSWORD 'reelnestpass';
GRANT ALL PRIVILEGES ON DATABASE reelnestdb TO reelnest;
```

3. Run Flyway migrations:
```bash
cd backend
mvn flyway:migrate
```

4. Update AWS credentials (for development, store locally in ~/.aws/credentials):
```bash
aws_access_key_id = YOUR_ACCESS_KEY
aws_secret_access_key = YOUR_SECRET_KEY
region = us-east-1
```

5. Run the backend:
```bash
mvn spring-boot:run
```

The backend runs on http://localhost:8080

### Optional: Frontend
If a frontend exists, follow its setup instructions in the frontend/folder (e.g., npm install and npm start).

## 🧩 API Endpoints (Example)
* POST /users/register -- Register new users
* POST /users/login -- Login
* GET /projects -- List projects
* POST /projects -- Create project
* GET /projects/{id} -- Get project details

Full API documentation coming soon. You can integrate Swagger/OpenAPI for automatic documentation.

## ⚠️ Notes
- Security: Currently uses generated development passwords. Update application.yml for production.
- Flyway: Manages database schema. Add new migrations in backend/src/main/resources/db/migration/
