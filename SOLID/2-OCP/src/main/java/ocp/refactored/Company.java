package ocp.refactored;

import java.util.List;

public class Company {

    private List<Employee> employees;
    private EmployeePersistence persistence;

    public Company() {
        persistence = new EmployeeMemoryPersistence();
        employees = persistence.findAll();
    }

    public void addProgrammer(String fullName, Integer salary) {
        persistence.save(new Programmer(fullName, salary));
    }
}
