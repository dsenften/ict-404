package simulation;

/**
 * Objekte dieser Klasse repräsentieren
 * Positionen in einem rechteckigen Field.
 */
@SuppressWarnings("WeakerAccess")
public class Position {

    private int row;
    private int column;

    /**
     * Repräsentiere eine Zeile und eine Spalte.
     *
     * @param row  die Zeile.
     * @param column die Spalte.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Prüfung auf Datengleichheit.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position position = (Position) obj;
            return row == position.getRow()
                    && column == position.getColumn();
        } else {
            return false;
        }
    }

    /**
     * Liefere einen String in der Form 'Zeile,Spalte'
     *
     * @return eine Stringdarstellung dieser Position.
     */
    public String toString() {
        return row + "," + column;
    }

    /**
     * Benutze die 16 höherwertigen Bits für den den Zeilenwert
     * und die 16 niederwertigen Bits für den Spaltenwert.
     * Ausser für sehr grosse Felder sollte dies einen eindeutigen
     * Hashwert für jedes Zeile-Spalte-Paar geben.
     *
     * @return einen Hash-Code für diese Position.
     */
    public int hashCode() {
        return (row << 16) + column;
    }

    /**
     * @return Die Zeile.
     */
    public int getRow() {
        return row;
    }

    /**
     * @return Die Spalte.
     */
    public int getColumn() {
        return column;
    }
}
