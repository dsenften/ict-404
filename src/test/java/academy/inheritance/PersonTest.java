package academy.inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    private final String firstName = "Daniel" ;
    private final String lastName = "Senften" ;

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person(firstName, lastName);
    }

    @Test
    void getFirstName() {
        assertEquals(firstName, person.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals(lastName, person.getLastName());
    }
}
