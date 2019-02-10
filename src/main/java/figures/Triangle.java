package figures;

import java.awt.*;

/**
 * Ein Triangle, das manipuliert werden kann und sich selbst auf einer Canvas
 * zeichnet.
 */

public class Triangle {

    // Höhe des Dreieckes
    private int height;

    // Breite des Dreieckes
    private int width;

    // Position des Dreieckes auf der Canvas
    private int xPosition;
    private int yPosition;

    // Farbe des Dreieckes
    private Color color;

    // Ist das Dreieck sichtbar?
    private boolean isVisible;

    /**
     * Erzeuge ein Triangle mit einer Standardfarbe an einer Standardposition.
     */
     Triangle() {
        height = 60;
        width = 70;
        xPosition = 210;
        yPosition = 140;
        color = Color.GREEN;
        isVisible = false;
    }

    /**
     * Mache dieses Triangle sichtbar. Wenn es bereits sichtbar ist, tue nichts.
     */
     void setVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Mache dieses Triangle unsichtbar. Wenn es bereits unsichtbar ist, tue
     * nichts.
     */
    public void setInvisible() {
        delete();
        isVisible = false;
    }

    /**
     * Bewege dieses Triangle einige Bildschirmpunkte nach rechts.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Bewege dieses Triangle einige Bildschirmpunkte nach links.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Bewege dieses Triangle einige Bildschirmpunkte nach oben.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Bewege dieses Triangle einige Bildschirmpunkte nach unten.
     */
    public void moveDown() {
        moveVertical(20);
    }

    /**
     * Bewege dieses Triangle horizontal um 'entfernung' Bildschirmpunkte.
     */
    private void moveHorizontal(int distance) {
        delete();
        xPosition += distance;
        draw();
    }

    /**
     * Bewege dieses Triangle vertikal um 'entfernung' Bildschirmpunkte.
     */
    private void moveVertical(int distance) {
        delete();
        yPosition += distance;
        draw();
    }

    /**
     * Bewege dieses Triangle langsam horizontal um 'entfernung'
     * Bildschirmpunkte.
     */
    public void moveSlowly(int distance) {
        int delta;

        if (distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for (int i = 0; i < distance; i++) {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Ändere die Höhe in 'neueHoehe' und die Breite in 'neueBreite'. Beide
     * Angaben müssen größer gleich Null sein.
     */
    public void changeSize(int height, int width) {
        delete();
        this.height = height;
        this.width = width;
        draw();
    }

    /**
     * Ändere die Farbe dieses Dreiecks in 'neueFarbe'. Gültige Angaben sind
     * "rot", "gelb", "blau", "gruen", "lila" und "schwarz".
     */
    public void setColor(Color color) {
        this.color = color;
        draw();
    }

    /**
     * Zeichne dieses Triangle mit seinen aktuellen Werten auf den Bildschirm.
     */
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = {xPosition, xPosition + (width / 2),
                    xPosition - (width / 2)};
            int[] ypoints = {yPosition, yPosition + height, yPosition + height};
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.pause(10);
        }
    }

    /**
     * Lösche dieses Triangle vom Bildschirm.
     */
    private void delete() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.remove(this);
        }
    }
}
