package course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * User: Daniel Senften <daniel@senften.org>
 * Date: 2019-02-12, 21:56
 */
class CourseTest {

    private Course course;
    private Student peter, paul;

    @BeforeEach
    void setUp() {
        course = new Course(12);
        peter = new Student("Müller, Peter", "4789-123-45");
        paul = new Student("Gilbert, Paul", "8967-348-12");
    }

    @Test
    void addStudent() {
        course.addStudent(peter);
        Student expected = course.getStudents().get(0);
        assertEquals(expected, peter);
    }

    @Test
    void numberOfStudents() {
        assertEquals(0, course.getNumberOfStudents());

        course.addStudent(peter);
        assertEquals(1, course.getNumberOfStudents());
    }

    @Test
    void setRoom() {
        String expected = "Boston";
        course.setRoom(expected);
        assertEquals(expected, course.getRoom());
    }

    @Test
    void setDateTime() {
        String expected = "12.02.2019, 13:00";
        course.setDateTime(expected);
        assertEquals(expected, course.getDateTime());
    }

    @Test
    void setLecturer() {
        String expected = "Müller, Peter";
        course.setLecturer(expected);
        assertEquals(expected, course.getLecturer());
    }

    @Test
    void listeAusgeben() {
        String expected =
                "Kurs: <unbekannt>\n" +
                        "Dozent: <unbekannt>   Raum: <unbekannt>\n" +
                        "Teilnehmerliste:\n" +
                        "Student{fullName='Müller, Peter', studentNumber='4789-123-45', exams=0}\n" +
                        "Teilnehmeranzahl: 1";

        course.addStudent(peter);
        assertEquals(expected, course.getCourse());
    }
}
