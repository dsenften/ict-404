package course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: Daniel Senften <daniel@senften.org>
 * Date: 2019-02-12, 21:44
 */
class StudentTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Senften, Daniel", "1234-34-6");
    }

    @Test
    void getFullName() {
        String expected = "Senften, Daniel";
        assertEquals(expected, student.getFullName());
    }

    @Test
    void getStudentNumber() {
        String expected = "1234-34-6";
        assertEquals(expected, student.getStudentNumber());
    }

    @Test
    void incrementExams() {
        int expected = 2;
        student.incrementExams(2);
        assertEquals(expected, student.getExams());        
    }

    @Test
    void getExams() {
        int expected = 0;
        assertEquals(expected, student.getExams());
    }

    @Test
    void getLoginName() {
        String expected = "Senf123";
        assertEquals(expected, student.getLoginName());
    }

    @Test
    void getString() {
        String expected = "Student{fullName='Senften, Daniel', studentNumber='1234-34-6', exams=0}";
        assertEquals(expected, student.toString());
    }
}
