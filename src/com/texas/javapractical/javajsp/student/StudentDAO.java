package com.texas.javapractical.javajsp.student;
import java.sql.*;
import java.util.*;

public class StudentDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/java";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "nirgun"; // set your password

    private static final String INSERT_SQL = "INSERT INTO studentTable (roll_no, name, email, program) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM studentTable";
    private static final String SELECT_BY_ID = "SELECT * FROM studentTable WHERE id=?";
    private static final String UPDATE_SQL = "UPDATE studentTable SET roll_no=?, name=?, email=?, program=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM studentTable WHERE id=?";

    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public StudentTable selectStudentById(int id) {
        StudentTable student = null;
        try {
            Connection con = getConnection(); // your connection method
            String sql = "SELECT * FROM studentTable WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                student = new StudentTable(
                        rs.getInt("id"),
                        rs.getString("roll_no"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("program")
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public void insertStudent(StudentTable student) {
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getProgram());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<StudentTable> selectAllStudents() {
        List<StudentTable> list = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new StudentTable(
                        rs.getInt("id"),
                        rs.getString("roll_no"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("program")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public StudentTable selectStudent(int id) {
        StudentTable s = null;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new StudentTable(
                        rs.getInt("id"),
                        rs.getString("roll_no"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("program")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return s;
    }

    public boolean updateStudent(StudentTable student) {
        boolean rowUpdated = false;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getProgram());
            ps.setInt(5, student.getId());
            rowUpdated = ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return rowUpdated;
    }

    public boolean deleteStudent(int id) {
        boolean rowDeleted = false;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return rowDeleted;
    }
}
