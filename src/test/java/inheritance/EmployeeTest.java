package inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("Daniel", "Senften");
    }

    @Test
    void testFirstName() {
        assertEquals("Daniel", employee.getFirstName());
    }

    @Test
    void testLastName() {
        assertEquals("Senften", employee.getLastName());
    }

    @Test
    void testToString() {
        String expected = "Employee(firstName=Daniel, lastName=Senften)";
        assertEquals(expected, employee.toString());
    }

}
