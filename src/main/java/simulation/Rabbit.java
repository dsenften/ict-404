package simulation;

import java.util.List;

/**
 * Ein einfaches Modell eines Hasen.
 * Ein Hase altert, bewegt sich, gebärt Nachwuchs und stirbt.
 */

public class Rabbit extends Animal {

    // Das Alter, in dem ein Rabbit gebärfähig wird.
    private static final int BREEDING_AGE = 5;

    // Das Höchstalter eines Hasen.
    private static final int MAX_AGE = 40;

    // Die Wahrscheinlichkeit, mit der ein Hase Nachwuchs gebärt.
    private static final double BREEDING_PROBABILITY = 0.12;

    // Die maximale Grösse eines Wurfes (Anzahl der Jungen)
    private static final int MAX_LITTER_SIZE = 4;

    // Individuelle Eigenschaften eines Hasen (Instanzfelder).

    /**
     * Erzeuge einen neuen Hasen. Ein neuer Hase kann das Alter 0
     * (neugeboren) oder ein zufälliges Alter haben.
     *
     * @param randomAge soll der Hase ein zufälliges Alter haben?
     * @param field     das aktuelle belegte Feld
     * @param position  die Position im Feld
     */
    public Rabbit(boolean randomAge, Field field, Position position) {
        super(field, position);
        age = 0;
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }

    /**
     * Das ist was ein Hase die meiste Zeit tut - er läuft herum.
     * Manchmal gebärt er Nachwuchs und irgendwann stirbt er
     * an Altersschwäche.
     *
     * @param animals eine Liste zum Zurückliefern der neugeborenen Hasen.
     */
    public void act(List<Animal> animals) {
        increaseAge();
        if (isAlive()) {
            giveBirth(animals);

            // nur in das nächste Field setzen, wenn eine Position frei ist
            Position location = getField().freeAdjacentLocation(getPosition());
            if (location != null) {
                setLocation(location);
            } else {
                die();
            }
        }
    }

    /**
     * Erhöhe das Alter.
     * Dies kann zum Tod des Hasen f�hren.
     */
    private void increaseAge() {
        age++;
        if (age > MAX_AGE) {
            die();
        }
    }

    /**
     * Prüfe, ob dieser Hase in diesem Schritt gebären kann.
     * Neugeborene kommen in freie Nachbarpositionen.
     *
     * @param animals eine Liste zum Zurückliefern der neugeborenen Hasen.
     */
    private void giveBirth(List<Animal> animals) {
        Field field = getField();
        List<Position> free = field.getFreeAdjacentLocations(getPosition());
        int birth = pregnant();
        for (int b = 0; b < birth && free.size() > 0; b++) {
            Position pos = free.remove(0);
            Rabbit rabbit = new Rabbit(false, field, pos);
            animals.add(rabbit);
        }
    }

    /**
     * Erzeuge eine Zahl für die Wurfgrösse, wenn der Hase
     * gebären kann.
     *
     * @return Wurfgrösse (kann '0' sein).
     */
    private int pregnant() {
        int birthSize = 0;
        if (canGiveBirth() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            birthSize = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return birthSize;
    }

    /**
     * Ein Rabbit kann gebären, wenn er das gebärfähige
     * Alter erreicht hat.
     */
    private boolean canGiveBirth() {
        return age >= BREEDING_AGE;
    }
}
