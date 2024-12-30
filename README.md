# Personal Expense Tracker

**Personal Expense Tracker** is a Java-based web application designed to help users manage their monthly budgets and track their expenses effectively. \
The application features a user-friendly interface for adding expenses, setting budget limits, and monitoring spending habits, making financial management simple and efficient.

---

## Features

1. **Add Expenses:**
   - Users can record details such as the expense name, amount, date, category, and description.

2. **Set Monthly Budget:**
   - Users can define budget limits for specific months to keep track of their spending goals.

3. **Budget Monitoring:**
   - The application calculates the total expenses for a month and displays the status (Under Budget or Over Budget).

4. **Responsive Design:**
   - A clean and simple UI for easy navigation and interaction.

5. **Database Integration:**
   - Stores expense and budget data securely using MySQL.

---

## Technologies Used

### **Frontend:**
- HTML, CSS for user interface design.

### **Backend:**
- Java Servlets for server-side logic.

### **Database:**
- MySQL to store expense and budget data.

### **Server:**
- Apache Tomcat for hosting the application.

---

## Project Structure

```
Personal_Expense_Tracker/
|-- src/
|   |-- servlets/          # Java Servlets for backend logic
|-- web/
|   |-- AddExpense.html    # Page to add expenses
|   |-- BudgetStatus.html  # Page to view budget status
|   |-- styles.css         # Styling for the application
|-- sql/
|   |-- create_tables.sql  # SQL script to set up database tables
|-- README.md              # Project documentation
```

---

## Installation and Setup

### Step 1: Clone the Repository

### Step 2: Import Project into IDE

- Open your preferred Java IDE (e.g., Eclipse, IntelliJ IDEA).
- Import the project as a Maven or Java project.

### Step 3: Configure the Database

1. Install and start MySQL.


### Step 4: Configure the Server

1. Install Apache Tomcat.
2. Add the project to the Tomcat server configuration in your IDE.

### Step 5: Run the Application

1. Start the Tomcat server.
---

## How to Use

### 1. Add Expense:
- Navigate to the **Add Expense** page.
- Fill in the details of your expense and submit.

### 2. Set Monthly Budget:
- Go to the **Set Budget** page.
- Enter the month/year and budget limit, then save.

### 3. View Budget Status:
- Check the **Budget Status** page to see your total expenses and budget status.

---

## Screenshots

### **1. Add Expense Page:**
![Add Expense Page]  ![AddExpense](https://github.com/user-attachments/assets/c4293495-197b-409b-be1b-138dc8671cbd)


### **2. Budget Status Page:**
![Budget Status Page] ![SetBudget](https://github.com/user-attachments/assets/4458fd8e-74b6-4463-add0-096f00a5a93b) ![OutPut](https://github.com/user-attachments/assets/828cba58-0866-4e63-9e38-513f6bbbc1ac)



---

## Future Enhancements

- **Expense Categories:** Add pre-defined categories for better tracking.
- **Reports:** Generate monthly or yearly expense reports.
- **Login System:** Enable user authentication for personalized data.
- **Mobile Optimization:** Improve responsiveness for mobile devices.

---

## Contributing

Contributions are welcome! If you have suggestions for improvement or find bugs, please fork the repository and submit a pull request.

---

Thank you for checking out the **Personal Expense Tracker** project! We hope this tool helps you manage your finances effectively.
