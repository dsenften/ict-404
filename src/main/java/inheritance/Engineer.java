package inheritance;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
@SuppressWarnings("WeakerAccess")
public class Engineer extends Employee {

    List<String> skills;

    {
        /*
         * The list returned when you call Arrays.asList is a thin
         * wrapper over the array, not a copy. The list returned is
         * fixed size: attempting to call add will throw an
         * UnsupportedOperationException exception. Therefore we
         * have to convert it to a 'real' ArrayList first :-)
         */
        skills = new ArrayList<>(
                Arrays.asList("Java", "C", "C++"));
    }

    public Engineer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }
}
