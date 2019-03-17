package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Ein Circle, der manipuliert werden kann und sich selbst auf einer Canvas
 * zeichnet.
 */

public class Circle extends Figure {

    // Durchmesser des Kreises
    private int diameter;

    /**
     * Erzeuge einen neuen Circle an einer Standardposition mit einer
     * Standardfarbe.
     */
    public Circle() {
        diameter = 68;
        xPosition = 230;
        yPosition = 90;
        color = Color.BLUE;
    }

    /**
     * Ändere den Durchmesser dieses Kreises in 'neuerDurchmesser' (Angabe in
     * Bildschirmpunkten). 'neuerDurchmesser' muss größer gleich Null sein.
     */
    public void setSize(int diameter) {
        delete();
        this.diameter = diameter;
        draw();
    }


    /**
     * Zeichne diesen Circle mit seinen aktuellen Werten auf den Bildschirm.
     */
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Ellipse2D.Double(xPosition,
                    yPosition, diameter, diameter));
            canvas.pause(10);
        }
    }

}
