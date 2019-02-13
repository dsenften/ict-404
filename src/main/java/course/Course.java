package course;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Course definiert Teilnehmerlisten von Laborkursen.
 * Ein Exemplar der Klasse Course hält Informationen über Zeit, Raum
 * und alle Teilnehmer sowie über den Namen des Dozenten.
 */
public class Course {
    private String lecturer;
    private String room;
    private String dateTime;
    private ArrayList<Student> students;
    private int maxNumberOfStudents;

    /**
     * Erzeuge einen Course mit einer Teilnehmerbegrenzung. Alle
     * anderen Details werden mit Standardwerten vorbelegt.
     */
    public Course(int maxNumberOfStudents) {
        lecturer = "<unbekannt>";
        room = "<unbekannt>";
        dateTime = "<unbekannt>";
        students = new ArrayList<>();
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    /**
     * Trage einen Studenten in diesen Kurs ein.
     */
    public void addStudent(Student student) {
        if (students.size() == maxNumberOfStudents) {
            System.out.println("Der Kurs ist voll, keine Eintragung mehr möglich.");
        } else {
            students.add(student);
        }
    }

    /**
     * Gibt die Liste der Studenten zurück.
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Liefere die Anzahl der in diesen Kurs momentan eingetragenen Studenten.
     */
    public int getNumberOfStudents() {
        return students.size();
    }

    /**
     * Setze den Raum für diesen Kurs.
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * Liefert den Raum dieses Kurses.
     */
    public String getRoom() {
        return this.room;
    }

    /**
     * Setze Wochentag und Anfangszeit für diesen Kurs. Der Parameter
     * sollte das Format "01.01.2019, 13:00" haben.
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Liefert den aktuellen Wert des Kursdatums
     */
    public String getDateTime() {
        return this.dateTime;
    }

    /**
     * Setze den Namen des Dozenten für diesen Course.
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * Liefert den Dozenten dieses Kurses.
     */
    public String getLecturer() {
        return this.lecturer;
    }

    /**
     * Gib eine Liste der Teilnehmer zusammen mit den weiteren Details
     * dieses Kurses auf die Konsole aus.
     */
    public String getCourse() {
        StringBuilder result = new StringBuilder();

        result.append("Kurs: ").append(dateTime).append("\n")
                .append("Dozent: ").append(lecturer).append("   Raum: ").append(room)
                .append("\n")
                .append("Teilnehmerliste:\n");

        for (Student student : students) {
            result.append(student);
        }

        result.append("\nTeilnehmeranzahl: ").append(getNumberOfStudents());

        return result.toString();
    }
}
