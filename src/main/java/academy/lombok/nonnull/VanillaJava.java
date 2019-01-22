package academy.lombok.nonnull;

import academy.inheritance.Person;

public class VanillaJava {
    public VanillaJava(Person person) {
        if (person == null) {
            throw new NullPointerException
                    ("Die Person ist nicht definiert.");
        }
        // Some more code
    }
}
