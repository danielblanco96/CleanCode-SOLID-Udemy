package dip.badexample;

import java.util.List;

public class Company {

    private EmployeePersistence persistence;

    public Company() {
        persistence = new EmployeeMemoryPersistence();
    }

    public List<Employee> getEmployees() {
        return persistence.findAll();
    }

    public void addEmployee(Employee e) {
        persistence.save(e);
    }
}
