# SMART PARKING LOT MANAGEMENT SYSTEM

## Overview
This project, developed as part of the flipped course evaluation , implements a robust, object-oriented solution for automating vehicle entry, spot assignment, and fee calculation in a multi-level parking facility. The system is built using Java, heavily leveraging core OOP principles like Inheritance and Polymorphism to handle different vehicle types and parking rates.


## Features

Automated Entry: Issues a unique ParkingTicket upon vehicle entry with an entry timestamp.


Spot Allocation: Assigns vehicles to appropriate spots (Motorcycle, Compact, Large) based on vehicle type, demonstrating a logical workflow.



Dynamic Fee Calculation: Calculates parking fees based on total duration and the specific hourly rate defined by the vehicle type, utilizing polymorphism.


Spot Management: Updates spot availability in real-time, satisfying CRUD operation requirements.


Error Handling: Implements custom exceptions to manage scenarios like "Lot Full" or "Invalid Ticket ID".


Technologies and Tools Used
Language: Java (JDK 17+)

Build Tool: Maven or Gradle (recommended for dependency management)


Testing: JUnit 5 (for validation tests )


Version Control: Git & GitHub 

Steps to Install & Run the Project
Clone the Repository:


git clone:  https://github.com/jeelraithatha8-png/Parking_Mangement_Syestem/tree/main


cd smart-parking-lot-system

Compile the Code (Using Maven):


# Ensure you are in the project root directory
mvn clean install
Run the Main Application:


# Execute the compiled JAR or run the main class directly
java -cp target/classes main.Main
Instructions for Testing
The core business logic is tested using JUnit.

Run Unit Tests:

mvn test
Verify Workflow: Check the console output when running main.Main.java. The successful execution should demonstrate:

A car entering and receiving a ticket.

A motorcycle entering and receiving a ticket.

Both vehicles exiting, displaying the calculated total fee.

An expected InvalidTicketException when attempting to reuse an exited ticket.

## Outputs:
<img width="616" height="658" alt="image" src="https://github.com/user-attachments/assets/86283fae-3e82-4269-b029-fe0045596b73" />
