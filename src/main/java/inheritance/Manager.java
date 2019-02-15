package inheritance;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper=true)
public class Manager extends Employee {
    private final String department;

    public Manager(String firstName, String lastName,
                   String department) {
        super(firstName, lastName);
        this.department = department;
    }
}
