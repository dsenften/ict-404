package simulation;

import java.util.List;
import java.util.Random;

/**
 * Ein simples Modell eines Fuchses.
 * Füchse altern, bewegen sich, fressen Hasen und sterben.
 */
@SuppressWarnings("WeakerAccess")
public class Fox extends Animal {

    // Das Alter, in dem ein Fuchs gebärfähig wird.
    private static final int BIRTH_AGE = 15;

    // Das Höchstalter eines Fuchses.
    private static final int MAX_AGE = 150;

    // Die Wahrscheinlichkeit, mit der ein Fox Nachwuchs gebärt.
    private static final double BIRTH_PROBABILITY = 0.08;

    // Die maximale Grösse eines Wurfes (Anzahl der Jungen).
    private static final int MAX_BIRTH_SIZE = 2;

    // Der Nährwert eines einzelnen Hasen. Letztendlich ist
    // dies die Anzahl der Schritte, die ein Fuchs bis zur
    // nächsten Mahlzeit laufen kann.
    private static final int NUTRITIONAL_VALUE = 9;

    // Ein gemeinsamer Zufallsgenerator, der die Geburten steuert.
    private static final Random rand = Randomizer.getRandomizer();

    // Individuelle Eigenschaften (Instanzfelder).

    // Das Alter dieses Fuchses.
    private int age;

    // Der Futter-Level, der durch das Fressen von Hasen erhöht wird.
    private int foodLevel;

    /**
     * Erzeuge einen Fuchs. Ein Fuchs wird entweder neugeboren
     * (Alter 0 Jahre und nicht hungrig) oder kann mit einem zufälligen Alter
     * und zufälligem Hungergefühl erzeugt werden.
     *
     * @param randomAge falls true, hat der neue Fuchs ein
     *                  zufälliges Alter und einen zufälligen Futter-Level.
     * @param field      das aktuelle belegte Field
     * @param position  die Position im Field
     */
    public Fox(boolean randomAge, Field field, Position position) {
        super(field, position);
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(NUTRITIONAL_VALUE);
        } else {
            age = 0;
            foodLevel = NUTRITIONAL_VALUE;
        }
    }

    /**
     * Das ist was ein Fox die meiste Zeit tut: er jagt Hasen.
     * Dabei kann er Nachwuchs gebären, vor Hunger sterben oder
     * an Altersschwäche.
     *
     * @param animals eine Liste zum Zurückliefern neugeborener Füchse.
     */
    public void act(List<Animal> animals) {
        increaseAge();
        increaseHunger();
        if (isAlive()) {
            giveBirth(animals);
            // In die Richtung bewegen, in der Futter gefunden wurde.
            Position newPosition = findFood();
            if (newPosition == null) {
                // kein Futter - zufällig bewegen
                newPosition = getField().freePosition(getPosition());
            }
            // Ist Bewegung m�glich?
            if (newPosition != null) {
                setPosition(newPosition);
            } else {
                // Überpopulation
                die();
            }
        }
    }

    /**
     * Erhöhe das Alter dieses Fuchses. Dies kann zu seinem
     * Tod führen.
     */
    private void increaseAge() {
        age++;
        if (age > MAX_AGE) {
            die();
        }
    }

    /**
     * Vergrössere den Hunger dieses Fuchses. Dies kann zu seinem
     * Tode führen.
     */
    private void increaseHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            die();
        }
    }

    /**
     * Suche nach Nahrung (Hasen) in den Nachbarpositionen.
     * Es wird nur der erste lebendige Hase gefressen.
     *
     * @return die Position mit Nahrung, oder null, wenn keine vorhanden.
     */
    private Position findFood() {
        Field field = getField();
        List<Position> nachbarPositionen =
                field.neighbourPosition(getPosition());
        for (Position pos : nachbarPositionen) {
            Object tier = field.getObjectAt(pos);
            if (tier instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) tier;
                if (rabbit.isAlive()) {
                    rabbit.die();
                    foodLevel = NUTRITIONAL_VALUE;
                    return pos;
                }
            }
        }
        return null;
    }

    /**
     * Prüfe, ob dieser Fuchs in diesem Schritt gebären kann.
     * Neugeborene kommen in freie Nachbarpositionen.
     *
     * @param animals eine Liste zum Zurückliefern neugeborener Füchse.
     */
    private void giveBirth(List<Animal> animals) {
        // Neugeborene kommen in freie Nachbarpositionen.
        // Freie Nachbarpositionen abfragen.
        Field field = getField();
        List<Position> frei = field.freePositions(getPosition());
        int geburten = pregnant();
        for (int b = 0; b < geburten && frei.size() > 0; b++) {
            Position pos = frei.remove(0);
            Fox fox = new Fox(false, field, pos);
            animals.add(fox);
        }
    }

    /**
     * Erzeuge eine Zahl für die Wurfgrösse, wenn der Fuchs
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
     * Ein Fox kann gebären, wenn er das gebärfähige
     * Alter erreicht hat.
     */
    private boolean canGiveBirth() {
        return age >= BIRTH_AGE;
    }

}
