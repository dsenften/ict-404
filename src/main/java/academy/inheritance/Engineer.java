package academy.inheritance;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuppressWarnings("WeakerAccess")
public class Engineer extends Employee {

    private List<String> projects = new ArrayList<>();

    public Engineer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void addProject(String project) {
        projects.add(project);
    }

    public List<String> getProjects() {
        return projects;
    }

}
