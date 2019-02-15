package inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * User: Daniel Senften <daniel@senften.org>
 * Date: 2019-02-15, 11:45
 */
class ManagerTest {

    private Manager manager;

    @BeforeEach
    void setUp() {
        manager = new Manager("Daniel", "Senften",
                "Enginering");
    }

    @Test
    void testToString() {
        String expected = "Manager(super=Employee(firstName=Daniel, lastName=Senften), department=Enginering)";
        assertEquals(expected, manager.toString());
    }

    @Test
    void testDepartment() {
        String expected = "Enginering";
        assertEquals(expected, manager.getDepartment());
    }

    @Test
    void testInheritance() {
        Employee employee = manager;
        String expected = "Manager(super=Employee(firstName=Daniel, lastName=Senften), department=Enginering)";
        assertEquals(expected, manager.toString());
    }
}
