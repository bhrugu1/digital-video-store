# 🎬 StreamVault - Digital Video Store

A full-stack web application for renting and purchasing movies and TV shows, built with **React** frontend and **Spring Boot** backend.

## 🏗️ **Project Structure**

```
DigitalVideoStore/
├── frontend/                    # React.js frontend application
│   ├── src/
│   │   ├── components/         # Reusable components (Header, Footer, HeroSlider)
│   │   ├── pages/              # Page components (Home, Listings, Details, Login, Register)
│   │   └── styles/             # CSS styling files
│   ├── public/
│   └── package.json
├── backend/                     # Spring Boot REST API
│   ├── src/main/java/
│   │   └── com/bhrugu/api/restapi/
│   │       ├── controller/     # REST API endpoints
│   │       ├── service/        # Business logic
│   │       ├── repository/     # Data access layer
│   │       ├── model/          # Entity classes
│   │       └── config/         # Configuration classes
│   └── pom.xml
└── start-app.bat               # Script to start both servers
```

## 🚀 **Quick Start**

### **Option 1: Use Start Script (Recommended)**
```bash
# From the project root directory
.\start-app.bat
```

### **Option 2: Manual Start**
```bash
# Terminal 1 - Start Spring Boot API
cd backend
.\mvnw.cmd spring-boot:run

# Terminal 2 - Start React Frontend (wait for API to start first)
cd frontend
npm start
```

## 🌐 **Application URLs**

- **Frontend (React)**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Database Console**: http://localhost:8080/h2-console

## 📡 **API Endpoints**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/media` | Get all movies and TV shows |
| GET | `/api/media/{id}` | Get specific media by ID |
| GET | `/api/movies` | Get all movies only |
| GET | `/api/tvshows` | Get all TV shows only |
| GET | `/api/media/search?title=keyword` | Search media by title |
| POST | `/api/media` | Create new media |
| PUT | `/api/media/{id}` | Update existing media |
| DELETE | `/api/media/{id}` | Delete media |

## 💾 **Database**

- **Development**: H2 in-memory database
- **Access**: http://localhost:8080/h2-console
  - **JDBC URL**: `jdbc:h2:mem:testdb`
  - **Username**: `sa`
  - **Password**: (leave empty)

## 🎯 **Features**

### **Frontend (React)**
- 🏠 **Homepage** with hero slider and featured content
- 📋 **Listings page** with filtering (All/Movies/TV Shows)
- 📖 **Details page** for individual movies/shows
- 🔐 **Authentication pages** (Login/Register)
- 📱 **Responsive design** for all devices
- 🎨 **Modern dark theme** with gradients

### **Backend (Spring Boot)**
- 🔌 **REST API** with full CRUD operations
- 🗃️ **JPA/Hibernate** for database operations
- 🌐 **CORS configuration** for frontend integration
- 📊 **H2 database** with sample data initialization
- 🔍 **Search functionality** by title and type

## 🛠️ **Technologies Used**

### **Frontend**
- React 18.2.0
- React Router 6.22.3
- React Slick (for carousel)
- Modern CSS with Flexbox/Grid

### **Backend**
- Spring Boot 3.5.4
- Spring Data JPA
- H2 Database
- Maven

## 📁 **Key Components**

### **React Components**
- `App.js` - Main application with routing
- `Header.js` - Navigation component
- `Home.js` - Landing page with featured content
- `Listings.js` - Grid view of all media
- `Details.js` - Individual movie/show details
- `Login.js` / `Register.js` - Authentication forms

### **Spring Boot Classes**
- `MediaController.java` - REST API endpoints
- `MediaService.java` - Business logic
- `MediaRepository.java` - Data access
- `Media.java` - Entity model
- `DataInitializer.java` - Sample data setup

## 🎬 **Sample Data**

The application comes pre-loaded with 8+ movies and TV shows including:
- **Movies**: Inception, The Matrix, The Dark Knight, Black Panther, Avatar: The Way of Water
- **TV Shows**: Breaking Bad, Game of Thrones, The Office

## 📋 **Development Notes**

- Frontend runs on port **3000**
- Backend API runs on port **8080**
- Database resets on each restart (in-memory)
- CORS is configured for local development
- All images use external URLs from TMDB/Amazon

## 🔧 **Customization**

To add more media items, edit the `DataInitializer.java` file in the backend or use the POST API endpoint.

---

**Built with ❤️ using React and Spring Boot**
