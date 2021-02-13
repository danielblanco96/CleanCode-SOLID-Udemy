package dip.refactored;

import dip.refactored.factory.EmployeeePersistenceFactory;
import dip.refactored.persistence.EmployeePersistence;

public class Company {

    private EmployeePersistence persistence;

    public Company(EmployeeePersistenceFactory persistenceFactory) {
        persistence = persistenceFactory.getEmployeePersistance();
    }

    public void addEmployee(Employee e) {
        persistence.save(e);
    }
}
