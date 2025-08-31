<%@ page import="com.texas.javapractical.javajsp.student.StudentDAO,com.texas.javapractical.javajsp.student.StudentTable" %>
<%@ page import="java.util.*,  " %>
<%
    StudentDAO dao = new StudentDAO();
    List<StudentTable> list = dao.selectAllStudents();
%>
<html>
<head><title>All Students</title></head>
<body>
<h2>Student Records</h2>
<table border="1">
    <tr><th>ID</th><th>Roll No</th><th>Name</th><th>Email</th><th>Program</th><th>Actions</th></tr>
    <%
        for(StudentTable s : list){
    %>
    <tr>
        <td><%=s.getId()%></td>
        <td><%=s.getRollNo()%></td>
        <td><%=s.getName()%></td>
        <td><%=s.getEmail()%></td>
        <td><%=s.getProgram()%></td>
        <td>
            <a href="editStudent.jsp?id=<%=s.getId()%>">Edit</a> |
            <a href="deleteStudent.jsp?id=<%=s.getId()%>">Delete</a>
        </td>
    </tr>
    <% } %>
</table>
<a href="addStudent.jsp">Add New Student</a>
</body>
</html>
%>
<%
    dao = new StudentDAO();
    list = dao.selectAllStudents();
%>
<html>
<head><title>All Students</title></head>
<body>
<h2>Student Records</h2>
<table border="1">
    <tr><th>ID</th><th>Roll No</th><th>Name</th><th>Email</th><th>Program</th><th>Actions</th></tr>
    <%
        for(StudentTable s : list){
    %>
    <tr>
        <td><%=s.getId()%></td>
        <td><%=s.getRollNo()%></td>
        <td><%=s.getName()%></td>
        <td><%=s.getEmail()%></td>
        <td><%=s.getProgram()%></td>
        <td>
            <a href="editStudent.jsp?id=<%=s.getId()%>">Edit</a> |
            <a href="deleteStudent.jsp?id=<%=s.getId()%>">Delete</a>
        </td>
    </tr>
    <% } %>
</table>
<a href="addStudent.jsp">Add New Student</a>
</body>
</html>
