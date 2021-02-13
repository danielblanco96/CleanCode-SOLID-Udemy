package dip.refactored;

import dip.refactored.factory.MySqlEmployeePersistenceFactory;

public class Main {

    public static void main(String[] args) {
        Company company = new Company(new MySqlEmployeePersistenceFactory());
    }

}
