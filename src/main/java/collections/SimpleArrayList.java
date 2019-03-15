package collections;


import academy.inheritance.Employee;

import java.util.ArrayList;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "unchecked"})
public class SimpleArrayList {

    public static void main(String[] args) {

        ArrayList list = new ArrayList();

        list.add(1);
        list.add("Daniel");
        list.add(new Employee("Daniel", "Senften"));
    }
}
