package dip.refactored.persistence;

import java.util.ArrayList;
import java.util.List;

import dip.refactored.Employee;

public class MySqlEmployeePersistence implements EmployeePersistence {

    public MySqlEmployeePersistence(String url, String user, String password) {

    }

    @Override
    public List<Employee> findAll() {
        // Database Query
        return new ArrayList<Employee>();
    }

    @Override
    public void save(Employee employee) {
        // Database Save

    }
}
