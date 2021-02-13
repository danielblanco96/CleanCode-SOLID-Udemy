package dip.refactored.persistence;

import java.util.List;

import dip.refactored.Employee;

public interface EmployeePersistence {

    public List<Employee> findAll();

    public void save(Employee employee);
}
