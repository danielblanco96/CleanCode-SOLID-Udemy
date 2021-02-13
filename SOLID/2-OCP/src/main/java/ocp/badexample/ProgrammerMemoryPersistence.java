package ocp.badexample;

import java.util.ArrayList;
import java.util.List;

public class ProgrammerMemoryPersistence {

    private List<Programmer> programmers;

    public ProgrammerMemoryPersistence() {
        programmers = new ArrayList<Programmer>();
    }

    public List<Programmer> findAll() {
        return programmers;
    }

    public void save(Programmer programmer) {
        programmers.add(programmer);
    }
}
