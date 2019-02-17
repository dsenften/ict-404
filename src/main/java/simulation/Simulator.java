package simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Ein einfacher Jäger-Beute-Simulator, basierend auf einem
 * Field mit Füchsen und Hasen.
 */
@SuppressWarnings("WeakerAccess")
public class Simulator {

    // Konstanten für Konfigurationsinformationen über die Simulation.
    // Die Standardbreite für ein Field.
    private static final int STANDARD_BREITE = 120;

    // Die Standardtiefe für ein Field.
    private static final int STANDARD_TIEFE = 80;

    // Die Wahrscheinlichkeit für die Geburt eines Fuchses an
    // einer beliebigen Position im Field.
    private static final double FUCHSGEBURT_WAHRSCHEINLICH = 0.02;

    // Die Wahrscheinlichkeit für die Geburt eines Hasen an
    // einer beliebigen Position im Field.
    private static final double HASENGEBURT_WAHRSCHEINLICH = 0.08;

    // Listen der Tiere im Field. Getrennte Listen vereinfachen das Iterieren.
    private List<Animal> tiere;

    // Der aktuelle Zustand des Feldes
    private Field field;

    // Der aktuelle Schritt der Simulation
    private int schritt;

    // Eine grafische Ansicht der Simulation
    private Simulationsansicht ansicht;

    /**
     * Erzeuge ein Simulationsfeld mit einer Standardgrösse.
     */
    public Simulator() {
        this(STANDARD_TIEFE, STANDARD_BREITE);
    }

    /**
     * Erzeuge ein Simulationsfeld mit der gegebenen Grösse.
     *
     * @param tiefe  die Tiefe des Feldes (muss grösser als null sein).
     * @param breite die Breite des Feldes (muss grösser als null sein).
     */
    public Simulator(int tiefe, int breite) {
        if (breite <= 0 || tiefe <= 0) {
            System.out.println("Abmessungen müssen grösser als null sein.");
            System.out.println("Benutze Standardwerte.");
            tiefe = STANDARD_TIEFE;
            breite = STANDARD_BREITE;
        }

        tiere = new ArrayList<Animal>();
        field = new Field(tiefe, breite);

        // Eine Ansicht der Zustände aller Positionen im Field erzeugen.
        ansicht = new Simulationsansicht(tiefe, breite);
        ansicht.setColor(Fox.class, Color.blue);
        ansicht.setColor(Rabbit.class, Color.orange);

        // Einen gültigen Startzustand einnehmen.
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
     * Simulationsschritten durch.
     * Brich vorzeitig ab, wenn die Simulation nicht mehr aktiv ist.
     *
     * @param schritte Anzahl der auszuführenden Schritte.
     */
    public void simulate(int schritte) {
        for (int schritt = 1; schritt <= schritte && ansicht.istAktiv(field); schritt++) {
            simuliereEinenSchritt();
        }
    }

    /**
     * Führe einen einzelnen Simulationsschritt aus:
     * Durchlaufe alle Feldpositionen und aktualisiere den
     * Zustand jedes Fuchses und Hasen.
     */
    public void simuliereEinenSchritt() {
        schritt++;

        // Platz für neugeborenes Animal anlegen.
        List<Animal> neueTiere = new ArrayList<>();

        // Alle Tiere agieren lassen.
        for (Iterator<Animal> iter = tiere.iterator(); iter.hasNext(); ) {
            Animal animal = iter.next();
            animal.act(neueTiere);
            if (!animal.isAlive()) {
                iter.remove();
            }
        }

        // Neugeborene Füchse und Hasen in die Hauptliste einfügen.
        tiere.addAll(neueTiere);

        ansicht.showStatus(schritt, field);
    }

    /**
     * Setze die Simulation an den Anfang zur�ck.
     */
    public void reset() {
        schritt = 0;
        tiere.clear();
        bevoelkere();

        // Zeige den Startzustand in der Ansicht.
        ansicht.showStatus(schritt, field);
    }

    /**
     * Bevölkere das Field mit Füchsen und Hasen.
     */
    private void bevoelkere() {
        Random rand = Randomizer.getRandomizer();
        field.clear();
        for (int zeile = 0; zeile < field.getHeight(); zeile++) {
            for (int spalte = 0; spalte < field.getWidth(); spalte++) {
                if (rand.nextDouble() <= FUCHSGEBURT_WAHRSCHEINLICH) {
                    Position position = new Position(zeile, spalte);
                    Fox fox = new Fox(true, field, position);
                    tiere.add(fox);
                } else if (rand.nextDouble() <= HASENGEBURT_WAHRSCHEINLICH) {
                    Position position = new Position(zeile, spalte);
                    Rabbit rabbit = new Rabbit(true, field, position);
                    tiere.add(rabbit);
                }
                // ansonsten die Position leer lassen
            }
        }
    }

    public static void main(String[] args) {
          Simulator sim = new Simulator();
          sim.simulate();
      }
}
