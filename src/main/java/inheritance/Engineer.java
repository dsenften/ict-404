package inheritance;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
public class Engineer extends Employee {

    List<String> skills = new ArrayList<>();

    {
        skills = Arrays.asList("Java", "C", "C++");
    }

    public Engineer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }
}
