package figures;

import java.awt.*;
import java.util.Objects;

/**
 * Ein Quadrat, das manipuliert werden kann und sich selbst auf einer Canvas
 * zeichnet.
 */

@SuppressWarnings("WeakerAccess")
public class Square extends Figure {

    // Grösse des Quadrates
    private int size;


    /**
     * Erzeuge ein neues Quadrat mit einer Standardfarbe an einer
     * Standardposition.
     */
    public Square() {
        size = 60;
        xPosition = 310;
        yPosition = 120;
        color = Color.RED;
        isVisible = false;
    }

    /**
     * Ändere die Grösse dieses Quadrates in 'size'. 'size' muss
     * groesser gleich '0' sein.
     */
    public void setSize(int size) {
        this.size = size;
        draw();
    }

    /**
     * Zeichne dieses Square mit seinen aktuellen Werten auf den Bildschirm.
     */
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Rectangle(xPosition, yPosition,
                    size, size));
            canvas.pause(10);
        }
    }

}
