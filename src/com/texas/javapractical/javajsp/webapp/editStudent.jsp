<%@ page import="com.texas.javapractical.javajsp.student.StudentDAO,com.texas.javapractical.javajsp.student.StudentTable" %>
<%
    String idStr = request.getParameter("id");
    StudentTable s = null;
    if(idStr != null) {
        int id = Integer.parseInt(idStr);
        s = new StudentDAO().selectStudentById(id);
    }

    if("POST".equalsIgnoreCase(request.getMethod())) {
        String roll = request.getParameter("roll_no");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String program = request.getParameter("program");
        int id = Integer.parseInt(request.getParameter("id")); // hidden input
        StudentTable updated = new StudentTable(id, roll, name, email, program);
        new StudentDAO().updateStudent(updated);
        response.sendRedirect("list.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Student</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f2f7f9;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .form-container {
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            width: 380px;
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: 600;
            color: #555;
        }
        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 10px 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }
        input[type="text"]:focus,
        input[type="email"]:focus {
            border-color: #007bff;
            outline: none;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #007bff;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }
        .back-link:hover {
            text-decoration: underline;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
            font-weight: 600;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Edit Student</h2>
    <% if(s != null) { %>
    <form method="post">
        <input type="hidden" name="id" value="<%= s.getId() %>"/>
        <label for="roll_no">Roll No:</label>
        <input type="text" name="roll_no" id="roll_no" value="<%= s.getRollNo() %>" required/>

        <label for="name">Name:</label>
        <input type="text" name="name" id="name" value="<%= s.getName() %>" required/>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" value="<%= s.getEmail() %>" required/>

        <label for="program">Program:</label>
        <input type="text" name="program" id="program" value="<%= s.getProgram() %>" required/>

        <input type="submit" value="Update Student"/>
    </form>
    <% } else { %>
    <p class="error">Student not found!</p>
    <% } %>
    <a class="back-link" href="list.jsp">← Back to Student List</a>
</div>
</body>
</html>
