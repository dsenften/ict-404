package time;

/**
 * Die Klasse NumberDisplay repräsentiert Darstellungen von
 * digitalen Werten, die von null bis zu einem vorgegebenen Limit
 * reichen können. Das Limit wird definiert, wenn eine NumberDisplay
 * erzeugt wird. Die darstellbaren Werte reichen von null bis Limit-1.
 * Wenn beispielsweise eine NumberDisplay für die Sekunden einer
 * digitalen Watch verwendet werden soll, würde man ihr Limit auf 60
 * setzen, damit die dargestellten Werte von 0 bis 59 reichen.
 * Wenn der Wert einer NumberDisplay erhöht wird, wird bei Erreichen
 * des Limits der Wert automatisch auf null zur�ckgesetzt.
 */
public class NumberDisplay {
    private int limit;
    private int value;

    /**
     * Konstruktor für Exemplare der Klasse NumberDisplay.
     * Setzt das Limit, bei dem die Anzeige zurückgesetzt wird.
     */
    public NumberDisplay(int limit) {
        this.limit = limit;
        value = 0;
    }

    /**
     * Liefere den aktuellen Wert als int.
     */
    public int getValue() {
        return value;
    }

    /**
     * Liefere den Anzeigewert, also den Wert dieser Anzeige als
     * einen String mit zwei Ziffern. Wenn der Wert der Anzeige
     * kleiner als zehn ist, wird die Anzeige mit einer führenden
     * null eingerückt.
     */
    public String getDisplayValue() {
        if (value < 10) {
            return "0" + value;
        } else {
            return "" + value;
        }
    }

    public String getBinaryDisplayValue() {
        int len = Integer.SIZE - Integer.numberOfLeadingZeros(limit);
        byte[] buf = new byte[len];
        formatUnsignedInt(value, buf, len);
        return new String(buf);
    }

    private void formatUnsignedInt(int val, byte[] buf, int len) {
        int charPos = len;
        int radix = 1 << 1;
        int mask = radix - 1;
        do {
            buf[--charPos] = (byte) ascii[val & mask];
            val >>>= 1;
        } while (charPos > 0);
    }

    /**
     * Setze den Wert der Anzeige auf den angegebenen 'value'.
     * Wenn der angegebene Wert unter null oder über dem Limit liegt,
     * tue nichts.
     */
    public void setValue(int value) {
        if ((value >= 0) && (value < limit)) {
            this.value = value;
        }
    }

    /**
     * Erhöhe den Wert um eins. Wenn das Limit erreicht ist, setze
     * den Wert wieder auf null.
     */
    public void incrementValue() {
        value = (value + 1) % limit;
    }

    /**
     * All possible chars for representing a number as a String
     */
    private static final char[] ascii = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'a' , 'b' ,
        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };
}
