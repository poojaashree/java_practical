package com.texas.javapractical.employee;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    double calculateSalary() {
        return salary;
    }
}

class FullTimeEmployee extends Employee {
    double basicPay, allowance;

    FullTimeEmployee(int id, String name, double basicPay, double allowance) {
        super(id, name);
        this.basicPay = basicPay;
        this.allowance = allowance;
    }

    @Override
    double calculateSalary() {
        salary = basicPay + allowance;
        return salary;
    }
}

class PartTimeEmployee extends Employee {
    int hoursWorked;
    double hourlyRate;
    PartTimeEmployee(int id, String name, int hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    @Override
    double calculateSalary() {
        salary = hoursWorked * hourlyRate;
        return salary;
    }
}