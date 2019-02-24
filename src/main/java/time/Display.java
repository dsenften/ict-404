package time;

/**
 * Die Klasse Display implementiert die Anzeige einer Digitaluhr.
 * Die Anzeige zeigt Stunden und Minuten. Der Anzeigebereich reicht von
 * 00:00 (Mitternacht) bis 23:59 (eine Minute vor Mitternacht).
 * <p>
 * Eine Display sollte minütlich "Taktsignale" (über die Operation
 * "clockSignal") erhalten, damit sie die Anzeige aktualisieren
 * kann. Dies geschieht, wie man es bei einer Watch erwartet: Die
 * Stunden erhöhen sich, wenn das Minutenlimit einer Stunde erreicht
 * ist.
 */
@SuppressWarnings("ALL")
public class Display {
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simuliert die tatsächliche Anzeige

    /**
     * Konstruktor für ein Exemplar von Display.
     * Mit diesem Konstruktor wird die Anzeige auf 00:00 initialisiert.
     */
    public Display() {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Konstruktor für ein Exemplar von Display.
     * Mit diesem Konstruktor wird die Anzeige auf den Wert
     * initialisiert, der durch 'stunde' und 'minute'
     * definiert ist.
     */
    public Display(int hour, int minute) {
        this.hours = new NumberDisplay(24);
        this.minutes = new NumberDisplay(60);
        setWatch(hour, minute);
    }

    /**
     * Diese Operation sollte einmal pro Minute aufgerufen werden -
     * sie sorgt dafür, dass die Anzeige um eine Minute
     * weiter gestellt wird.
     */
    public void clockSignal() {
        minutes.incrementValue();
        if (minutes.getValue() == 0) {  // Limit wurde erreicht!
            hours.incrementValue();
        }
        updateDisplay();
    }

    /**
     * Setze die Uhrzeit dieser Anzeige auf die gegebene 'stunde' und
     * 'minute'.
     */
    public void setWatch(int hour, int minute) {
        this.hours.setValue(hour);
        this.minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Liefere die aktuelle Uhrzeit dieser Display im Format SS:MM.
     */
    public String getWatch() {
        return displayString;
    }

    /**
     * Aktualisiere die interne Zeichenkette, die die Zeitanzeige h�lt.
     */
    private void updateDisplayx() {
        displayString = hours.getDisplayValue() + ":"
                + minutes.getDisplayValue();
    }

    private void updateDisplay() {
        String suffix = " am";
        int hour = hours.getValue();
        if (hour >= 12) {
            hour -= 12;
            suffix = " pm";
        }
        if (hour == 0) hour = 12;
        displayString = ((hour < 10) ? "0" + hour : hour) + ":"
                + minutes.getDisplayValue() + suffix;
    }

    public static void main(String[] args) {

        Display display = new Display(1, 59);

        while (true) {
            display.updateDisplay();
            display.clockSignal();
        }
    }
}
