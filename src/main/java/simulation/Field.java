package simulation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Ein rechteckiges Gitter von Feldpositionen.
 * Jede Position kann ein einzelnes Tier aufnehmen.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Field {

    private static final Random rand = Randomizer.getRandomizer();

    // Die Höhe und die Breite des Feldes
    private int hight, width;

    // Speicher für die Tiere
    private Object[][] field;

    /**
     * Erzeuge ein Feld mit den angegebenen Dimensionen.
     *
     * @param hight die Höhe des Feldes.
     * @param width die Breite des Feldes.
     */
    public Field(int hight, int width) {
        this.hight = hight;
        this.width = width;
        field = new Object[hight][width];
    }

    /**
     * Räume das Field und initialisiere es mit 'null' Werten.
     */
    public void clear() {
        for (int row = 0; row < hight; row++) {
            for (int column = 0; column < width; column++) {
                field[row][column] = null;
            }
        }
    }

    /**
     * Räume die gegebene Position.
     *
     * @param position die zu leerende Position
     */
    public void clear(Position position) {
        field[position.getRow()][position.getColumn()] = null;
    }

    /**
     * Platziere das gegebene Tier an der angegebenen Position.
     * Wenn an der Position bereits ein Tier eingetragen ist,
     * geht es verloren.
     *
     * @param animal das Tier das platziert werden soll.
     * @param row    die Zeilenkoordinate der Position.
     * @param column die Spaltenkoordinate der Position.
     */
    public void place(Object animal, int row, int column) {
        place(animal, new Position(row, column));
    }

    /**
     * Platziere das gegebene Tier an der angegebenen Position.
     * Wenn an der Position bereits ein Tier eingetragen ist,
     * geht es verloren.
     *
     * @param animal   das Tier, das platziert werden soll.
     * @param position die Position, an der das Animal platziert werden soll.
     */
    public void place(Object animal, Position position) {
        field[position.getRow()][position.getColumn()] = animal;
    }

    /**
     * Liefere das Tier an der angegebenen Position, falls vorhanden.
     *
     * @param position die gewünschte Position.
     * @return das Tier an der angegebenen Position oder null, wenn
     * dort kein Tier eingetragen ist.
     */
    public Object getObjectAt(Position position) {
        return getObjectAt(position.getRow(), position.getColumn());
    }

    /**
     * Liefere das Tier an der angegebenen Position, falls vorhanden.
     *
     * @param row    die gewünschte Zeile.
     * @param column die gewünschte Spalte.
     * @return das Animal an der angegebenen Position oder null, wenn
     * dort kein Animal eingetragen ist.
     */
    public Object getObjectAt(int row, int column) {
        return field[row][column];
    }

    /**
     * Wähle zufällig eine der Positionen, die an die gegebene Position
     * angrenzen, oder die gegebene Position selbst.
     * Die gelieferte Position liegt innerhalb der gültigen Grenzen
     * dieses Feldes.
     *
     * @param position die Position, von der ein Nachbar zu wählen ist.
     * @return eine gültige Position innerhalb dieses Feldes. Das kann
     * auch die gegebene Position selbst sein.
     */
    public Position randomPosition(Position position) {
        List<Position> neighbour = adjacentLocations(position);
        return neighbour.get(0);
    }

    /**
     * Liefert eine gemischte Liste von freien Nachbarposition.
     *
     * @param position die Position, für die Nachbarpositionen
     *                 zu liefern ist.
     * @return eine Liste freier Nachbarpositionen.
     */
    public List<Position> getFreeAdjacentLocations(Position position) {
        List<Position> free = new LinkedList<>();
        List<Position> neighbour = adjacentLocations(position);
        for (Position next : neighbour) {
            if (getObjectAt(next) == null) {
                free.add(next);
            }
        }
        return free;
    }

    /**
     * Versuche, eine freie Nachbarposition zur gegebenen Position zu
     * finden. Wenn es keine gibt, liefere null.
     * Die gelieferte Position liegt innerhalb der Feldgrenzen.
     *
     * @param position die Position, für die eine Nachbarposition
     *                 zu liefern ist.
     * @return eine gültige Position innerhalb der Feldgrenzen.
     */
    public Position freeAdjacentLocation(Position position) {
        // Die verfügbaren freien Nachbarpositionen
        List<Position> free = getFreeAdjacentLocations(position);
        if (free.size() > 0) {
            return free.get(0);
        } else {
            return null;
        }
    }

    /**
     * Liefert eine gemischte Liste von Nachbarpositionen zu der gegebenen
     * Position. Diese Liste enthält nicht die gegebene Position selbst.
     * Alle Positionen liegen innerhalb des Feldes.
     *
     * @param position die Position, für die Nachbarpositionen zu liefern sind.
     * @return eine Liste der Nachbarpositionen zur gegebenen Position.
     */
    public List<Position> adjacentLocations(Position position) {

        List<Position> positions = new LinkedList<>();

        int row = position.getRow();
        int column = position.getColumn();

        for (int rowDifference = -1; rowDifference <= 1; rowDifference++) {
            int nextRow = row + rowDifference;
            if (nextRow >= 0 && nextRow < hight) {
                for (int columnDifference = -1;
                     columnDifference <= 1;
                     columnDifference++) {
                    int nextColumn = column + columnDifference;

                    // Ungültige Positionen und Ausgangsposition ausschliessen.
                    if (nextColumn >= 0 &&
                            nextColumn < width &&
                            (rowDifference != 0 || columnDifference != 0)) {
                        positions.add(new Position(nextRow, nextColumn));
                    }
                }
            }
        }

        // Mische die Liste. Verschiedene andere Methoden verlassen sich
        // darauf, dass die Liste ungeordnet ist.
        Collections.shuffle(positions, rand);

        return positions;
    }

    /**
     * Liefere die Höhe dieses Feldes.
     */
    public int getHeight() {
        return hight;
    }

    /**
     * Liefere die Breite dieses Feldes.
     */
    public int getWidth() {
        return width;
    }
}
