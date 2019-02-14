package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Ein Circle, der manipuliert werden kann und sich selbst auf einer Canvas
 * zeichnet.
 */

public class Circle {

    // Durchmesser des Kreises
    private int diameter;

    // Position des Kreises auf der Canvas
    private int xPosition;
    private int yPosition;

    // Farbe des Kreises
    private Color color;

    // Ist der Kreis sichtbar=
    private boolean isVisible;

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
     * Mache diesen Circle sichtbar. Wenn es bereits sichtbar ist, tue nichts.
     */
    public void setVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Mache diesen Circle unsichtbar. Wenn es bereits unsichtbar ist, tue
     * nichts.
     */
    public void setInvisible() {
        delete();
        isVisible = false;
    }

    /**
     * Bewege diesen Circle einige Bildschirmpunkte nach rechts.
     */
    public void turnRight() {
        moveHorizontal(20);
    }

    /**
     * Bewege diesen Circle einige Bildschirmpunkte nach links.
     */
    public void turnLeft() {
        moveHorizontal(-20);
    }

    /**
     * Bewege diesen Circle einige Bildschirmpunkte nach oben.
     */
    public void turnUp() {
        moveVertical(-20);
    }

    /**
     * Bewege diesen Circle einige Bildschirmpunkte nach unten.
     */
    public void turnDown() {
        moveVertical(20);
    }

    /**
     * Bewege diesen Circle horizontal um 'entfernung' Bildschirmpunkte.
     */
    public void moveHorizontal(int distance) {
        delete();
        xPosition += distance;
        draw();
    }

    /**
     * Bewege diesen Circle vertikal um 'entfernung' Bildschirmpunkte.
     */
    public void moveVertical(int distance) {
        delete();
        yPosition += distance;
        draw();
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
     * Ändere die Farbe dieses Kreises in 'neueFarbe'. Gültige Angaben sind
     * "rot", "gelb", "blau", "gruen", "lila" und "schwarz".
     */
    public void setColor(Color color) {
        this.color = color;
        draw();
    }

    /**
     * Zeichne diesen Circle mit seinen aktuellen Werten auf den Bildschirm.
     */
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Ellipse2D.Double(xPosition,
                    yPosition, diameter, diameter));
            canvas.pause(10);
        }
    }

    /**
     * Lösche diesen Circle vom Bildschirm.
     */
    private void delete() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.remove(this);
        }
    }

}
