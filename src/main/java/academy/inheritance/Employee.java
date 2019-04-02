package academy.inheritance;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuppressWarnings("WeakerAccess")
public class Employee extends Person {

    private static int numberOfEmployees;
    protected static int empployeeID;

    private List<Competency> skills = new ArrayList<>();

    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
        empployeeID = ++numberOfEmployees;
    }

    public void addSkills(Competency competency) {
        skills.add(competency);
    }

    public List<Competency> getSkills() {
        return skills;
    }

    protected enum Competency {
        Bash, JavaScript, Pascal, Python
    }

    protected int getNumberOfEmployees() {
        return numberOfEmployees;
    }
}
