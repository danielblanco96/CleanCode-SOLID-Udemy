package dip.refactored.factory;

import dip.refactored.persistence.EmployeeMemoryPersistence;
import dip.refactored.persistence.EmployeePersistence;

public class EmployeeMemoryPersistenceFactory implements EmployeeePersistenceFactory {

    @Override
    public EmployeePersistence getEmployeePersistance() {
        return new EmployeeMemoryPersistence();
    }

}
