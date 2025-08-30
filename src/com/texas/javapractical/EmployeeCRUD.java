package com.texas.javapractical;

import java.sql.*;
import java.util.Scanner;

public class EmployeeCRUD {
    // Database connection details
    private static final String URL = "jdbc:postgresql://localhost:5432/java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "nirgun";

    private static Connection conn;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Load driver (optional for newer JDBC versions)
            Class.forName("org.postgresql.Driver");

            // Connect
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL successfully!");

            int choice;
            do {
                System.out.println("\n--- Employee Department CRUD Menu ---");
                System.out.println("1. Insert Department");
                System.out.println("2. Insert Employee");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. View All Employees with Department");
                System.out.println("6. Fetch Employees by Department");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> insertDepartment();
                    case 2 -> insertEmployee();
                    case 3 -> updateEmployee();
                    case 4 -> deleteEmployee();
                    case 5 -> viewEmployees();
                    case 6 -> fetchByDepartment();
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertDepartment() throws SQLException {
        System.out.print("Enter dept_id: ");
        String deptId = sc.nextLine();
        System.out.print("Enter dept_name: ");
        String deptName = sc.nextLine();

        String sql = "INSERT INTO Department (dept_id, dept_name) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, deptId);
        ps.setString(2, deptName);
        ps.executeUpdate();
        System.out.println("Department inserted successfully.");
    }

    private static void insertEmployee() throws SQLException {
        System.out.print("Enter emp_id: ");
        String empId = sc.nextLine();
        System.out.print("Enter emp_name: ");
        String empName = sc.nextLine();
        System.out.print("Enter dept_id (must exist in Department): ");
        String deptId = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO Employee (emp_id, emp_name, dept_id, salary) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, empId);
        ps.setString(2, empName);
        ps.setString(3, deptId);
        ps.setDouble(4, salary);
        ps.executeUpdate();
        System.out.println("Employee inserted successfully.");
    }

    private static void updateEmployee() throws SQLException {
        System.out.print("Enter emp_id to update: ");
        String empId = sc.nextLine();
        System.out.print("Enter new name: ");
        String newName = sc.nextLine();
        System.out.print("Enter new salary: ");
        double newSalary = sc.nextDouble();

        String sql = "UPDATE Employee SET emp_name = ?, salary = ? WHERE emp_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, newName);
        ps.setDouble(2, newSalary);
        ps.setString(3, empId);

        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Employee updated successfully.");
        else System.out.println("Employee not found.");
    }

    private static void deleteEmployee() throws SQLException {
        System.out.print("Enter emp_id to delete: ");
        String empId = sc.nextLine();

        String sql = "DELETE FROM Employee WHERE emp_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, empId);

        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Employee deleted successfully.");
        else System.out.println("Employee not found.");
    }

    private static void viewEmployees() throws SQLException {
        String sql = "SELECT e.emp_id, e.emp_name, e.salary, d.dept_name " +
                "FROM Employee e JOIN Department d ON e.dept_id = d.dept_id";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\nEmployees with Departments:");
        while (rs.next()) {
            System.out.printf("ID: %s | Name: %s | Salary: %.2f | Department: %s%n",
                    rs.getString("emp_id"),
                    rs.getString("emp_name"),
                    rs.getDouble("salary"),
                    rs.getString("dept_name"));
        }
    }

    private static void fetchByDepartment() throws SQLException {
        System.out.print("Enter dept_id: ");
        String deptId = sc.nextLine();

        String sql = "SELECT e.emp_id, e.emp_name, e.salary, d.dept_name " +
                "FROM Employee e JOIN Department d ON e.dept_id = d.dept_id " +
                "WHERE d.dept_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, deptId);

        ResultSet rs = ps.executeQuery();
        System.out.println("\nEmployees in Department " + deptId + ":");
        while (rs.next()) {
            System.out.printf("ID: %s | Name: %s | Salary: %.2f | Department: %s%n",
                    rs.getString("emp_id"),
                    rs.getString("emp_name"),
                    rs.getDouble("salary"),
                    rs.getString("dept_name"));
        }
    }
}
