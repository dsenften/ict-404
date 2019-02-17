package simulation;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Eine grafische Ansicht des Simulationsfeldes.
 * Die Ansicht zeigt für jede Position ein gefärbtes Rechteck,
 * das den jeweiligen Inhalt repräsentiert, und hat eine
 * vorgebene Hintergrundfarbe.
 * <p>
 * Die Farben für die verschiedenen Tierarten können mit
 * der Methode setColor definiert werden.
 */
@SuppressWarnings("WeakerAccess")
public class Simulationsansicht extends JFrame {

    // Die Farbe für leere Positionen
    private static final Color EMPTY_COLOR = Color.white;

    // Die Farbe für Objekte ohne definierte Farbe
    private static final Color UNDEFINED_COLOR = Color.gray;

    private final String STEP_PREFIX = "Schritt: ";
    private final String POPULATION_PREFIX = "Population: ";

    private JLabel stepLabel, population;
    private FieldView fieldView;

    // Eine Map für die Farben der Simulationsteilnehmer
    private Map<Class, Color> colors;

    // Ein Statistik-Objekt zur Berechnung und Speicherung
    // von Simulationsdaten
    private FieldStatistics stats;

    /**
     * Erzeuge eine Ansicht mit der gegebenen Breite und H�he.
     *
     * @param height Die Höhe der Simulation.
     * @param width  Die Breite der Simulation.
     */
    public Simulationsansicht(int height, int width) {

        stats = new FieldStatistics();
        colors = new LinkedHashMap<>();

        setTitle("Simulation von Füchsen und Hasen");
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);

        setLocation(100, 50);

        fieldView = new FieldView(height, width);

        Container inhalt = getContentPane();
        inhalt.add(stepLabel, BorderLayout.NORTH);
        inhalt.add(fieldView, BorderLayout.CENTER);
        inhalt.add(population, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    /**
     * Definiere eine Farbe für die gegebene Tierklasse.
     *
     * @param animalClass Das Klassenobjekt der Tierklasse.
     * @param color       Die zu benutzende Farbe für die Tierklasse.
     */
    public void setColor(Class animalClass, Color color) {
        colors.put(animalClass, color);
    }

    /**
     * @return die definierte Farbe für die gegebene Tierklasse.
     */
    private Color getColor(Class animalClass) {
        Color color = colors.get(animalClass);
        if (color == null) {
            // für die gegebene Klasse ist keine Farbe definiert
            return UNDEFINED_COLOR;
        } else {
            return color;
        }
    }

    /**
     * Zeige den aktuellen Zustand des Feldes.
     *
     * @param step welcher Iterationsschritt ist dies.
     * @param field   das Field, das angezeigt werden soll.
     */
    public void showStatus(int step, Field field) {
        if (!isVisible())
            setVisible(true);

        stepLabel.setText(STEP_PREFIX + step);
        stats.reset();

        fieldView.prepareView();

        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                Object animal = field.getObjectAt(row, column);
                if (animal != null) {
                    stats.increaseCounter(animal.getClass());
                    fieldView.drawField(column, row, getColor(animal.getClass()));
                } else {
                    fieldView.drawField(column, row, EMPTY_COLOR);
                }
            }
        }
        stats.countCompleted();

        population.setText(POPULATION_PREFIX + stats.getInfo(field));
        fieldView.repaint();
    }

    /**
     * Entscheide, ob die Simulation weiterlaufen soll.
     *
     * @return true wenn noch mehr als eine Spezies lebendig ist.
     */
    public boolean istAktiv(Field field) {
        return stats.isActive(field);
    }

    /**
     * Liefere eine grafische Ansicht eines rechteckigen Feldes.
     * Dies ist eine geschachtelte Klasse (eine Klasse, die
     * innerhalb einer anderen Klasse definiert ist), die eine
     * eigene grafische Komponente f�r die Benutzungsschnittstelle
     * definiert. Diese Komponente zeigt das Field an.
     * Dies ist fortgeschrittene GUI-Technik - Sie k�nnen sie
     * f�r Ihr Projekt ignorieren, wenn Sie wollen.
     */
    private class FieldView extends JPanel {
        private static final long serialVersionUID = 20060330L;
        private final int FACTOR = 6;

        private int fieldWidth, fieldHeight;
        private int xFactor, yFactor;

        Dimension size;
        private Graphics g;
        private Image image;

        /**
         * Erzeuge eine neue Komponente zur FieldView.
         */
        public FieldView(int height, int width) {
            fieldHeight = height;
            fieldWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Der GUI-Verwaltung mitteilen, wie gro� wir sein wollen.
         * Der Name der Methode ist durch die GUI-Verwaltung festgelegt.
         */
        public Dimension getPreferredSize() {
            return new Dimension(fieldWidth * FACTOR,
                    fieldHeight * FACTOR);
        }

        /**
         * Bereite eine neue Zeichenrunde vor. Da die Komponente
         * in der Gr��e ge�ndert werden kann, muss der Ma�stab neu
         * berechnet werden.
         */
        public void prepareView() {
            if (!size.equals(getSize())) {  // Gr��e wurde ge�ndert...
                size = getSize();
                image = fieldView.createImage(size.width, size.height);
                g = image.getGraphics();

                xFactor = size.width / fieldWidth;
                if (xFactor < 1) {
                    xFactor = FACTOR;
                }
                yFactor = size.height / fieldHeight;
                if (yFactor < 1) {
                    yFactor = FACTOR;
                }
            }
        }

        /**
         * Zeichne an der gegebenen Position ein Rechteck mit
         * der gegebenen Farbe.
         */
        public void drawField(int x, int y, Color farbe) {
            g.setColor(farbe);
            g.fillRect(x * xFactor, y * yFactor, xFactor - 1, yFactor - 1);
        }

        /**
         * Die Komponente für die FieldView muss erneut angezeigt
         * werden. Kopiere das interne Image in die Anzeige.
         * Der Name der Methode ist durch die GUI-Verwaltung festgelegt.
         */
        public void paintComponent(Graphics g) {
            if (image != null) {
                Dimension actualSize = getSize();
                if (size.equals(actualSize)) {
                    g.drawImage(image, 0, 0, null);
                } else {
                    // Grösse des aktuellen Images anpassen.
                    g.drawImage(image, 0, 0, actualSize.width,
                            actualSize.height, null);
                }
            }
        }
    }
}
