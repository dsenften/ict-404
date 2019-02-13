package course;

/**
 * Diese Klasse definiert Studenten in einem Uni-Verwaltungssystem.
 * Sie beschreibt die Details von Studenten, die für unseren Kontext
 * relevant sind.
 */
public class Student
{
    // der volle Name des Studierenden
    private String fullName;

    // seine Matrikelnummer
    private String studentNumber;

    // die Anzahl der bereits erworbenen Scheine
    private int exams;

    /**
     * Erzeuge einen neuen Studenten mit Name und Matrikelnummer.
     */
    public Student(String fullName, String studentNumber)
    {
        this.fullName = fullName;
        this.studentNumber = studentNumber;
        this.exams = 0;
    }

    /**
     * Liefere den vollen Namen dieses Studenten.
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * Trage einen neuen Namen ein.
     */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * Liefere die Matrikelnummer dieses Studenten.
     */
    public String getStudentNumber()
    {
        return studentNumber;
    }

    /**
     * Erhöhe die Anzahl der erworbenen Scheine.
     */
    public void incrementExams(int exams)
    {
        this.exams += exams;
    }

    /**
     * Liefere die Anzahl der bereits erworbenen Scheine.
     */
    public int getExams()
    {
        return exams;
    }

    /**
     * Liefere den Login-Namen. Der Login-Name ist eine Kombination der
     * ersten vier Buchstaben des Namens mit den ersten drei Ziffern der
     * Matrikelnummer.
     */
    public String getLoginName()
    {
        return fullName.substring(0,4) + studentNumber.substring(0,3);
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", exams=" + exams +
                '}';
    }

}
