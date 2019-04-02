package simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Ein einfacher Jäger-Beute-Simulator, basierend auf einem
 * Feld mit Füchsen und Hasen.
 */
@SuppressWarnings("WeakerAccess")
public class Simulator {

    // Konstanten für Konfigurationsinformationen über die Simulation.
    // Die Standardbreite für ein Field.
    private static final int STANDARD_WIDTH = 120;

    // Die Standardtiefe für ein Field.
    private static final int STANDARD_HEIGHT = 80;

    // Die Wahrscheinlichkeit für die Geburt eines Fuchses an
    // einer beliebigen Position im Field.
    private static final double FOX_BIRTH_LIKELIHOOD = 0.02;

    // Die Wahrscheinlichkeit für die Geburt eines Hasen an
    // einer beliebigen Position im Field.
    private static final double RABBIT_BIRTH_LIKELIHOOD = 0.08;

    // Listen der Tiere im Field. Getrennte Listen vereinfachen das Iterieren.
    private List<Animal> animals;

    // Der aktuelle Zustand des Feldes
    private Field field;

    // Der aktuelle Schritt der Simulation
    private int actualStep;

    // Eine grafische Ansicht der Simulation
    private SimulationView view;

    /**
     * Erzeuge ein Simulationsfeld mit einer Standardgrösse.
     */
    public Simulator() {
        this(STANDARD_HEIGHT, STANDARD_WIDTH);
    }

    /**
     * Erzeuge ein Simulationsfeld mit der gegebenen Grösse.
     *
     * @param height die Tiefe des Feldes (muss grösser als null sein).
     * @param width  die Breite des Feldes (muss grösser als null sein).
     */
    public Simulator(int height, int width) {
        if (width <= 0 || height <= 0) {
            System.out.println("Abmessungen müssen grösser als '0' sein.");
            System.out.println("Benutze Standardwerte.");
            height = STANDARD_HEIGHT;
            width = STANDARD_WIDTH;
        }

        animals = new ArrayList<>();
        field = new Field(height, width);

        view = new SimulationView(height, width);
        view.setColor(Fox.class, Color.blue);
        view.setColor(Rabbit.class, Color.orange);

        reset();
    }

    /**
     * Starte die Simulation vom aktuellen Zustand aus für einen längeren
     * Zeitraum (4000 Schritte).
     */
    public void simulate() {
        simulate(4000);
    }

    /**
     * Führe vom aktuellen Zustand aus die angegebene Anzahl an
     * Simulationsschritten durch. Brich vorzeitig ab, wenn die
     * Simulation nicht mehr aktiv ist.
     *
     * @param steps Anzahl der auszuführenden Schritte.
     */
    public void simulate(int steps) {
        for (int step = 1; step <= steps && view.isActiv(field); step++) {
            simulateStep();
        }
    }

    /**
     * Führe einen einzelnen Simulationsschritt aus:
     * Durchlaufe alle Feldpositionen und aktualisiere den
     * Zustand jedes Fuchses und Hasen.
     */
    public void simulateStep() {
        actualStep++;

        List<Animal> animals = new ArrayList<>();

        // Alle Tiere agieren lassen.
        for (Iterator<Animal> iter = this.animals.iterator(); iter.hasNext(); ) {
            Animal animal = iter.next();
            animal.act(animals);
            if (!animal.isAlive()) {
                iter.remove();
            }
        }

        this.animals.addAll(animals);

        view.showStatus(actualStep, field);
    }

    /**
     * Setze die Simulation an den Anfang zur�ck.
     */
    public void reset() {
        actualStep = 0;
        animals.clear();
        populate();

        view.showStatus(actualStep, field);
    }

    /**
     * Bevölkere das Field mit Füchsen und Hasen.
     */
    private void populate() {
        Random rand = Randomizer.getRandomizer();
        field.clear();
        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                if (rand.nextDouble() <= FOX_BIRTH_LIKELIHOOD) {
                    Position position = new Position(row, column);
                    Fox fox = new Fox(true, field, position);
                    animals.add(fox);
                } else if (rand.nextDouble() <= RABBIT_BIRTH_LIKELIHOOD) {
                    Position position = new Position(row, column);
                    Rabbit rabbit = new Rabbit(true, field, position);
                    animals.add(rabbit);
                }
            }
        }
    }

    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.simulate();
    }
}
