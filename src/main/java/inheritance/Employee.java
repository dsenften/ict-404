package inheritance;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Employee {
    private final String firstName;
    private final String lastName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
