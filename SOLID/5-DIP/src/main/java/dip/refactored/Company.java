package dip.refactored;

import java.util.List;

import dip.refactored.factory.EmployeeePersistenceFactory;
import dip.refactored.persistence.EmployeePersistence;

public class Company {

    private List<Employee> employees;
    private EmployeePersistence persistence;

    public Company(EmployeeePersistenceFactory persistenceFactory) {
        persistence = persistenceFactory.getEmployeePersistance();
        employees = persistence.findAll();
    }

    public void addEmployee(Employee e) {
        persistence.save(e);
    }
}
