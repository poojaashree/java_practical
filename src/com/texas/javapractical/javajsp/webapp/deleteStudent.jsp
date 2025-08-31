<%@ page import="com.texas.javapractical.javajsp.student.StudentDAO" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    new StudentDAO().deleteStudent(id);
    response.sendRedirect("list.jsp");
%>
