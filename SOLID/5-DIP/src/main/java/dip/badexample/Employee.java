package dip.badexample;

public class Employee {
    private String fullName;
    private Integer salary;

    public Employee(String fullName, Integer salary) {
        this.fullName = fullName;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getSalary() {
        return salary;
    }
}
