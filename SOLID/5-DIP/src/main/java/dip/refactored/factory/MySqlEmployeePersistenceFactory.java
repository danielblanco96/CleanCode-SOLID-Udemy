package dip.refactored.factory;

import dip.refactored.persistence.EmployeePersistence;
import dip.refactored.persistence.MySqlEmployeePersistence;

public class MySqlEmployeePersistenceFactory implements EmployeeePersistenceFactory {

    @Override
    public EmployeePersistence getEmployeePersistance() {
        return new MySqlEmployeePersistence(System.getProperty("url"),
                System.getProperty("user"), System.getProperty("password"));
    }

}
