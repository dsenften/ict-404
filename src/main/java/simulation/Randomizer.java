package simulation;

import java.util.Random;

/**
 * Steuerung der zufallsbasierten Elemente der Simulation.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Randomizer {

    // Vorgabe für den SEED-Wert, der die Erzeugung der Zufallszahlen steuert
    private static final int SEED = 1111;

    // Ein gemeinsam genutztes Random-Objekt
    private static final Random rand = new Random(SEED);

    /**
     * Konstruktor für Objekte der Klasse Randomizer
     */
    public Randomizer() {
    }

    /**
     * Liefert einen Zufallsgenerator.
     *
     * @return Ein Random-Objekt.
     */
    public static Random getRandomizer() {
        return rand;
    }

    /**
     * Setzt die Randomizer zurueck.
     */
    public static void reset() {
        rand.setSeed(SEED);
    }
}
