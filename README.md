# Gandha-Siri – Sandalwood Tree Management App

## Overview

Gandha-Siri is an Android-based application developed to help sandalwood farmers digitally manage, monitor, and protect sandalwood plantations. The application focuses on solving problems such as illegal smuggling, theft, poor record management, and lack of awareness regarding legal harvesting procedures.

The application provides a lightweight and user-friendly platform where users can register sandalwood trees, monitor growth, capture GPS locations, manage plantation records, and improve plantation security through alert systems.

---

# Features

- Dashboard for plantation overview
- Tree Registration with GPS tracking
- Tree photo management
- Growth monitoring and maturity estimation
- RecyclerView-based tree management
- Security Center with Panic Button alerts
- Legal Guide for harvesting procedures
- Local database storage using Room Database
- Lightweight Android application optimized for low-end systems

---

# Technologies Used

- Android Studio
- Java / Kotlin
- XML Layouts
- Android SDK
- Material Design Components
- RecyclerView
- Room Database
- SQLite
- Google Play Services Location API
- GPS Services
- Notification Services
- Gradle Build System
- Git & GitHub

---

# Project Structure

```text
Gandha-Siri/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   ├── res/
│   │   │   ├── AndroidManifest.xml
│   │
│   ├── build.gradle.kts
│
├── gradle/
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

---

# Modules

## Dashboard
Displays registered trees, mature trees, and security status.

## Register Tree
Allows users to register sandalwood trees with:
- Tree photo
- GPS location
- Girth details
- Tree age

## My Trees
Displays all registered sandalwood tree records using RecyclerView.

## Security Center
Provides:
- Panic Button
- Emergency alerts
- Security checklist

## Legal Guide
Provides information regarding:
- Tree registration
- Harvest permissions
- Transit permits
- Legal harvesting procedures

---

# Database Implementation

The project uses Room Database for local data storage and management.

Stored information includes:
- Tree ID
- GPS coordinates
- Girth measurement
- Age
- Health status
- Maturity information

CRUD operations are implemented for efficient tree management.

---

# How to Run the Project

## Requirements

- Android Studio
- Android SDK
- Minimum SDK: 24
- Java / Kotlin support
- Physical Android device or emulator

---

## Steps

1. Open Android Studio.
2. Click **Open Project**.
3. Select the Gandha-Siri project folder.
4. Allow Gradle Sync to complete.
5. Connect Android device using USB debugging.
6. Click **Run ▶** to install the application.

---

# Future Enhancements

- Firebase cloud synchronization
- AI-based disease prediction
- Cloud backup support
- QR code–based tree identification
- Real-time notification services
- Advanced analytics dashboard

---

# Learning Outcomes

- Android application development
- XML UI designing
- Room Database integration
- GPS tracking implementation
- RecyclerView handling
- Android debugging and optimization
- Lightweight application development
- Real-world agricultural technology solutions

<img width="200" height="700" alt="image" src="https://github.com/user-attachments/assets/fe374f08-60b7-48f8-b458-1c9f7dc6e7fe" />
<img width="200" height="700" alt="image" src="https://github.com/user-attachments/assets/1f5a627b-99ce-44b4-ab1d-e6e53406ff91" />
<img width="200" height="700" alt="image" src="https://github.com/user-attachments/assets/dc1ef9da-d68b-4196-a9ee-fcdd544abfa0" />
<img width="200" height="700" alt="image" src="https://github.com/user-attachments/assets/561be9d8-b0ec-4893-bffa-9deab23470a7" />
<img width="200" height="700" alt="image" src="https://github.com/user-attachments/assets/46732597-110e-4d75-8ea3-8dda8d174ad3" />




---

# Conclusion

Gandha-Siri demonstrates how Android technology can be used to solve real-world agricultural and natural resource management problems. The application provides a secure, scalable, and lightweight solution for sandalwood plantation monitoring and management while improving digital adoption among farmers.
