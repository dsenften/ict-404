package inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EngineerTest {

    private Engineer engineer;

    @BeforeEach
    void setUp() {
        engineer = new Engineer("Daniel", "Senften");
    }

    @Test
    void getSkills() {
        String expected = "Python";
        engineer.addSkill(expected);

        List<String> skills = engineer.getSkills();
        assertEquals(expected, skills.get(skills.size() - 1));
    }
}
