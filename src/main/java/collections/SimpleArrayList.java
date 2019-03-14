package collections;

import inheritance.Employee;

import java.util.ArrayList;

public class SimpleArrayList {

    public static void main(String[] args) {

        ArrayList list = new ArrayList();

        Employee emp = new Employee("Daniel", "Senften");

        list.add(1);
        list.add("Daniel");
        list.add(emp);


    }
}
