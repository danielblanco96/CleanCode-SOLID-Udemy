package ocp.badexample;

import java.util.List;

public class Company {

    private ProgrammerMemoryPersistence persistence;

    public Company() {
        persistence = new ProgrammerMemoryPersistence();
    }

    public List<Programmer> getEmployees() {
        return persistence.findAll();
    }

    public void addProgrammer(String fullName, Integer salary) {
        persistence.save(new Programmer(fullName, salary));
    }
}
