package org.metro.model.DTO;

public class Employee extends Person {
    private Role personnel; // Vị trí (vai trò của nhân viên)

    public Employee(Employee employee) {
        super(employee);
        this.personnel = employee.personnel;
    }
}