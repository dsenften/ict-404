package figures;

import java.awt.*;

/**
 * Ein Dreieck, das manipuliert werden kann und sich selbst auf einer Canvas
 * zeichnet.
 */

@SuppressWarnings("WeakerAccess")
public class Triangle extends Figure {

    // Höhe des Dreieckes
    private int height;

    // Breite des Dreieckes
    private int width;

    /**
     * Erzeuge ein Dreieck mit einer Standardfarbe an einer Standardposition.
     */
    public Triangle() {
        height = 60;
        width = 70;
        xPosition = 210;
        yPosition = 140;
        color = Color.GREEN;
        isVisible = false;
    }

    /**
     * Ändere die Höhe in 'height' und die Breite in 'width'. Beide
     * Angaben müssen grösser gleich '0' sein.
     */
    public void setSize(int height, int width) {
        this.height = height;
        this.width = width;
        draw();
    }

    /**
     * Zeichne dieses Dreieck mit seinen aktuellen Werten auf den Bildschirm.
     */
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = {xPosition, xPosition + (width / 2),
                    xPosition - (width / 2)};
            int[] ypoints = {yPosition, yPosition + height, yPosition + height};
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.pause(10);
        }
    }

}
