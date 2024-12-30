<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 50px;
        }
        .profile-container {
            margin: auto;
            padding: 20px;
            width: 50%;
            box-shadow: 0px 0px 10px #ccc;
            border-radius: 10px;
        }
        .actions button {
            margin: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #007BFF;
            color: white;
        }
        .actions button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <h1>Welcome to Your Profile For Personal_Expense_Tracker</h1>
        <p><strong>Name:</strong> ${name}</p>
        <p><strong>Email:</strong> ${email}</p>
        <p><strong>Mobile:</strong> ${mobile}</p>
        <p><strong>Gender:</strong> ${gender}</p>
        <p><strong>Age:</strong> ${age}</p>
        
        <h3>Actions</h3>
        <div class="actions">
            <button onclick="location.href='AddExpense.html'">Add Expense</button>
            <button onclick="location.href='SetBudget.html'">Set Budget</button>
        </div>
    </div>
</body>
</html>
