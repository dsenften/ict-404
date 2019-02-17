package simulation;

import java.util.HashMap;

/**
 * Diese Klasse sammelt und liefert statistische Daten über den
 * Zustand eines Feldes. Auf sehr flexible Weise: Es wird ein
 * Zähler angelegt und gepflegt für jede Objektklasse, die im
 * Field gefunden wird.
 */
@SuppressWarnings("WeakerAccess")
public class FieldStatistics {
    
    // Die Zähler für die jeweiligen Akteurstypen (Fuchs, Hase, etc.)
    // in der Simulation.
    private HashMap<Class, Counter> counter;

    // Sind die Zählerstände momentan aktuell?
    private boolean isCounterCurrent;

    /**
     * Erzeuge ein FieldStatistics-Objekt.
     */
    public FieldStatistics() {
        // Wir legen eine Sammlung für die Zähler an, die wir für
        // die gefundenen Tierarten erzeugen.
        counter = new HashMap<>();
        isCounterCurrent = true;
    }

    /**
     * Liefere Informationen über die Bewohner im Field.
     *
     * @return Eine Beschreibung, welche Tiere das
     * Field bevölkern.
     */
    public String getInfo(Field field) {
        StringBuilder buffer = new StringBuilder();
        if (!isCounterCurrent) {
            determineCounter(field);
        }
        for (Class key : counter.keySet()) {
            Counter info = counter.get(key);
            buffer.append(info.getName());
            buffer.append(": ");
            buffer.append(info.getCounter());
            buffer.append(' ');
        }
        return buffer.toString();
    }

    /**
     * Verwerfe alle bisher gesammelten Daten; setze alle Zähler
     * auf null zurück.
     */
    public void reset() {
        isCounterCurrent = false;
        for (Class key : counter.keySet()) {
            Counter counter = this.counter.get(key);
            counter.reset();
        }
    }

    /**
     * Erh�he den Zähler für eine Tierklasse.
     *
     * @param animalClass Klasse der Tierart, für die erhöht werden soll.
     */
    public void increaseCounter(Class animalClass) {
        Counter counter = this.counter.get(animalClass);
        if (counter == null) {
            // Wir haben noch keinen Z�hler f�r
            // diese Spezies - also neu anlegen
            counter = new Counter(animalClass.getName());
            this.counter.put(animalClass, counter);
        }
        counter.increase();
    }

    /**
     * Signalisiere, dass eine Tierzählung beendet ist.
     */
    public void countCompleted() {
        isCounterCurrent = true;
    }

    /**
     * Stelle fest, ob die Simulation noch aktiv ist, also
     * ob sie weiterhin laufen sollte.
     *
     * @return true wenn noch mehr als eine Spezies lebt.
     */
    public boolean isActive(Field field) {
        // Wieviele Zähler sind nicht null.
        int counters = 0;
        if (!isCounterCurrent) {
            determineCounter(field);
        }
        for (Class key : counter.keySet()) {
            Counter info = counter.get(key);
            if (info.getCounter() > 0) {
                counters++;
            }
        }
        return counters > 1;
    }

    /**
     * Erzeuge Zähler für die Anzahl der Füchse und Hasen.
     * Diese werden nicht ständig aktuell gehalten, während
     * Füchse und Hasen in das Field gesetzt werden, sondern
     * jeweils bei der Abfrage der Zählerstände berechnet.
     *
     * @param field das Field, für das die Statistik erstellt
     *             werden soll.
     */
    private void determineCounter(Field field) {
        reset();
        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                Object animal = field.getObjectAt(row, column);
                if (animal != null) {
                    increaseCounter(animal.getClass());
                }
            }
        }
        isCounterCurrent = true;
    }
}
