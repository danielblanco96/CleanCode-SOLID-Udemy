package ocp.badexample;

import java.util.List;

public class Company {

    private List<Programmer> programmers;
    private ProgrammerMemoryPersistence persistence;

    public Company() {
        persistence = new ProgrammerMemoryPersistence();
        programmers = persistence.findAll();
    }

    public void addProgrammer(String fullName, Integer salary) {
        persistence.save(new Programmer(fullName, salary));
    }
}
