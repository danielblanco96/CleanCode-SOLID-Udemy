package ocp.badexample;

public class Programmer {

    private String fullName;
    private Integer salary;

    public Programmer(String fullName, Integer salary) {
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
