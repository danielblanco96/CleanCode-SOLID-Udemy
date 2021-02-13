package ocp.refactored;

import java.util.List;

public class Company {

    private List<Employee> employees;
    private EmployeePersistence persistence;

    public Company() {
        persistence = new EmployeeMemoryPersistence();
        employees = persistence.findAll();
    }

    public void addEmployee(Employee e) {
        persistence.save(e);
    }

}
