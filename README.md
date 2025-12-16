# PatientFlow OS – Java OOP Project

**Course:** Object Oriented Programming  
**Semester:** Fall 2025  
**Authors:** Muhammad Dantiye Nasir & Sipho Chakuamba Zulu

---

## 1. Project Overview

PatientFlow OS is a **console-based Java application** that simulates how a dental clinic or medical service can manage incoming leads and convert them into patients.

The system focuses on the early stages of a real clinic workflow:
- capturing potential patients (leads)
- scoring and classifying them
- converting qualified leads into patients

The goal of the project is not complexity, but **clean Object-Oriented design**, clear separation of responsibilities, and readable, maintainable code.

---

## 2. Inspiration

The idea for PatientFlow OS comes from a real workflow concept we previously explored using **Google Sheets and Apps Script** to manage clinic leads and automate status updates.

For this Object Oriented Programming course, we rebuilt the **same domain idea** in Java to focus purely on:
- class design
- interfaces
- services
- and object interaction

This allowed us to separate business logic from automation tools and demonstrate proper OOP principles.

---

## 3. What the System Can Do

When you run the program, you can:

1. Create a new lead
2. List all leads
3. Show HOT leads
4. Show WARM leads
5. Show COLD leads
6. View a daily summary (lead counts by status)
7. List leads by city
8. Convert a lead into a patient
9. List all patients
10. Find a patient by ID
0. Exit the program

This menu-driven approach keeps the system simple and easy to test.

---

## 4. Architecture Overview

The project is structured into clearly separated packages.

### `app`
- Contains `PatientFlowApp`
- Handles user input and console output
- Does NOT contain business logic

### `model`
- Represents real-world entities:
    - `Lead`
    - `Patient`
    - `ContactInfo`
- Uses enums like `LeadStatus` to control valid states

### `repository`
- Handles data storage
- Uses interfaces to define behavior:
    - `CrudRepository<T, ID>`
    - `LeadRepository`
    - `PatientRepository`
- Uses in-memory implementations for simplicity

### `service`
- Contains business logic
- Examples:
    - creating and scoring leads
    - converting a lead into a patient
    - retrieving patients by ID

### `scoring`
- Implements the **Strategy Pattern**
- `LeadScoringStrategy` defines scoring behavior
- `DefaultLeadScoringStrategy` applies simple, readable rules

---

## 5. Example Program Flow

A typical usage flow looks like this:

1. A new lead is created through the console
2. The lead is scored and assigned a status (HOT / WARM / COLD)
3. Leads can be filtered or summarized
4. A qualified lead is converted into a patient
5. Patient records can be viewed later

This mirrors a real clinic's pipeline and follow up with potential patients.

---

## 6. OOP Concepts Demonstrated

This project demonstrates several key OOP concepts:

- **Encapsulation**  
  Data is stored inside well-defined classes with controlled access.

- **Abstraction**  
  Repositories and strategies are defined using interfaces.

- **Polymorphism**  
  The scoring system can be replaced with a different strategy without changing other code.

- **Separation of Concerns**  
  UI, business logic, storage, and scoring are kept in separate layers.

---

## 7. How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/siphoczulu/patientflow-os-oop.git
   ```
2.	Open the project in IntelliJ IDEA
3.	Mark src/main/java as Sources Root
4.	Run:
   ```bash
   com.patientflow.app.PatientFlowApp
   ```


---

## 8. Future Improvements

If the project were continued further, possible improvements include:
- Adding a graphical user interface (JavaFX or Swing)
- Persisting data to files or a database
- Adding appointment scheduling
- Writing unit tests with JUnit
- Exposing the logic through an API for a mobile application
- Implementing more advanced lead scoring algorithms
- Adding user authentication and roles (e.g., admin, staff)
- Internationalization for multiple languages
- Logging and error handling improvements
- Implementing notifications (email/SMS) for lead follow-ups 
- 

---
## 9. Possible Technical Direction

While this project was built as a Java console application for the purpose of demonstrating Object Oriented Programming principles, the overall workflow proved to be a good candidate for a more permanent implementation.

Initially, similar lead-management workflows were explored using tools such as Google Sheets and Apps Script for automation. However, as this Java project evolved, it became clear that implementing the core workflow in Java provides:

- better structure and maintainability
- clearer separation of business logic and storage
- easier extension into a GUI or backend service

As a future step, the Java implementation could become the **main system**, with user interfaces (desktop or mobile) built on top of it, rather than relying on spreadsheet-based logic.

---
## 10. Final Notes

PatientFlow OS is intentionally simple, readable, and structured to clearly demonstrate object-oriented programming principles in a realistic domain.

---
Thank you for reviewing our project!