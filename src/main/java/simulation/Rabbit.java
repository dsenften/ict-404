package simulation;

import java.util.List;
import java.util.Random;

/**
 * Ein einfaches Modell eines Hasen.
 * Ein Hase altert, bewegt sich, gebärt Nachwuchs und stirbt.
 */
@SuppressWarnings("WeakerAccess")
public class Rabbit extends Animal {

    // Das Alter, in dem ein Rabbit gebärfähig wird.
    private static final int BIRTH_AGE = 5;

    // Das Höchstalter eines Hasen.
    private static final int MAX_AGE = 40;

    // Die Wahrscheinlichkeit, mit der ein Hase Nachwuchs gebärt.
    private static final double BIRTH_PROBABILITY = 0.12;

    // Die maximale Grösse eines Wurfes (Anzahl der Jungen)
    private static final int MAX_BIRTH_SIZE = 4;

    // Ein gemeinsamer Zufallsgenerator, der die Geburten steuert.
    private static final Random rand = Randomizer.getRandomizer();

    // Individuelle Eigenschaften eines Hasen (Instanzfelder).

    // Das Alter dieses Hasen.
    private int age;

    /**
     * Erzeuge einen neuen Hasen. Ein neuer Hase kann das Alter 0
     * (neugeboren) oder ein zufälliges Alter haben.
     *
     * @param randomAge soll der Rabbit ein zufälliges Alter haben?
     * @param field      das aktuelle belegte Field
     * @param position  die Position im Field
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
            Position newPosition = getField().freePosition(getPosition());
            if (newPosition != null) {
                setPosition(newPosition);
            } else {
                // Überpopulation
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
        // Neugeborene kommen in freie Nachbarpositionen.
        // Freie Nachbarpositionen abfragen.
        Field field = getField();
        List<Position> free = field.freePositions(getPosition());
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
     * @return Wurfgrösse (kann null sein).
     */
    private int pregnant() {
        int birthSize = 0;
        if (canGiveBirth() && rand.nextDouble() <= BIRTH_PROBABILITY) {
            birthSize = rand.nextInt(MAX_BIRTH_SIZE) + 1;
        }
        return birthSize;
    }

    /**
     * Ein Rabbit kann gebären, wenn er das gebärfähige
     * Alter erreicht hat.
     */
    private boolean canGiveBirth() {
        return age >= BIRTH_AGE;
    }
}
