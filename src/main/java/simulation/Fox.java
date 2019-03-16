package simulation;

import java.util.List;

/**
 * Ein simples Modell eines Fuchses.
 * Füchse altern, bewegen sich, fressen Hasen und sterben.
 */

public class Fox extends Animal {

    // Das Alter, in dem ein Fuchs gebärfähig wird.
    private static final int BREEDING_AGE = 15;

    // Das Höchstalter eines Fuchses.
    private static final int MAX_AGE = 150;

    // Die Wahrscheinlichkeit, mit der ein Fox Nachwuchs gebärt.
    private static final double BREEDING_PROBABILITY = 0.08;

    // Die maximale Grösse eines Wurfes (Anzahl der Jungen).
    private static final int MAX_LITTER_SIZE = 2;

    // Der Nährwert eines einzelnen Hasen. Letztendlich ist
    // dies die Anzahl der Schritte, die ein Fuchs bis zur
    // nächsten Mahlzeit laufen kann.
    private static final int RABBIT_FOOD_VALUE = 9;

    // Individuelle Eigenschaften (Instanzfelder).

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
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        } else {
            age = 0;
            foodLevel = RABBIT_FOOD_VALUE;
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
            Position location = findFood();
            if (location == null) {
                // kein Futter - zufällig bewegen
                location = getField().freeAdjacentLocation(getPosition());
            }
            // Ist Bewegung m�glich?
            if (location != null) {
                setLocation(location);
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
        List<Position> adjacent =
                field.adjacentLocations(getPosition());
        for (Position position : adjacent) {
            Object animal = field.getObjectAt(position);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.die();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return position;
                }
            }
        }
        return null;
    }

    /**
     * Prüfe, ob dieser Fuchs in diesem Schritt gebären kann.
     * Neugeborene kommen in freie Nachbarpositionen.
     *
     * @param foxes eine Liste zum Zurückliefern neugeborener Füchse.
     */
    private void giveBirth(List<Animal> foxes) {
        // Neugeborene kommen in freie Nachbarpositionen.
        // Freie Nachbarpositionen abfragen.
        Field field = getField();
        List<Position> free = field.getFreeAdjacentLocations(getPosition());
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Position pos = free.remove(0);
            Fox young = new Fox(false, field, pos);
            foxes.add(young);
        }
    }

    /**
     * Erzeuge eine Zahl für die Wurfgrösse, wenn der Fuchs
     * gebären kann.
     *
     * @return Wurfgrösse (kann '0' sein).
     */
    private int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * Ein Fox kann gebären, wenn er das gebärfähige
     * Alter erreicht hat.
     */
    private boolean canBreed() {
        return age >= BREEDING_AGE;
    }

}
