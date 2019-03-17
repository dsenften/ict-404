package academy.inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("FieldCanBeLocal")
class EngineerTest {

    private final String firstName = "Daniel" ;
    private final String lastName = "Senften" ;

    private Engineer engineer;

    @BeforeEach
    void setUp() {
        engineer = new Engineer(firstName, lastName);
    }

    @Test
    void getProjects() {
        engineer.addProject("University of Berne");
        engineer.addProject("Java Development");

        List<String> projects = Arrays.asList(
                "University of Berne",
                "Java Development"
        );
        assertEquals(projects, engineer.getProjects());
    }
}
