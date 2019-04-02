package academy.inheritance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("Daniel", "Senften");
    }

    @Test
    void testSkills() {
        employee.addSkills(Employee.Competency.Bash);
        employee.addSkills(Employee.Competency.Python);

        List<Employee.Competency> skills = Arrays.asList(
                Employee.Competency.Bash, Employee.Competency.Python
        );
        assertEquals(skills, employee.getSkills());
    }

    @Test
    void testGetEmployeeID() {
        int expected = employee.getNumberOfEmployees();
        assertEquals(expected, Employee.empployeeID);
    }
    @Test
    void testToString() {
        String expected = "Person(firstName=Daniel, lastName=Senften)" ;
        assertEquals(expected, employee.toString());
    }

}
