package figures;

import java.awt.*;

/**
 * Ein Quadrat, das manipuliert werden kann und sich selbst auf einer Canvas
 * zeichnet.
 */

public class Square {

    // Grösse des Quadrates
    private int size;

    // Position des Quadrates
    private int xPosition;
    private int yPosition;

    // Farbe des Quadrates
    private Color color;

    // Ist das Square sichtbar?
    private boolean isVisible;

    /**
     * Erzeuge ein neues Square mit einer Standardfarbe an einer
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
     * Mache dieses Square sichtbar. Wenn es bereits sichtbar ist, tue nichts.
     */
     public void setVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Mache dieses Square unsichtbar. Wenn es bereits unsichtbar ist, tue
     * nichts.
     */
    public void setInvisible() {
        delete();
        isVisible = false;
    }

    /**
     * Bewege dieses Square einige Bildschirmpunkte nach rechts.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Bewege dieses Square einige Bildschirmpunkte nach links.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Bewege dieses Square einige Bildschirmpunkte nach oben.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Bewege dieses Square einige Bildschirmpunkte nach unten.
     */
    public void moveDown() {
        moveVertical(20);
    }

    /**
     * Bewege dieses Square horizontal um 'entfernung' Bildschirmpunkte.
     */
    public void moveHorizontal(int distance) {
        delete();
        xPosition += distance;
        draw();
    }

    /**
     * Bewege dieses Square vertikal um 'entfernung' Bildschirmpunkte.
     */
    public void moveVertical(int distance) {
        delete();
        yPosition += distance;
        draw();
    }

    /**
     * Ändere die Größe dieses Quadrates in 'neueGroesse'. 'neueGroesse' muss
     * groesser gleich Null sein.
     */
    public void setSize(int size) {
        delete();
        this.size = size;
        draw();
    }

    /**
     * Ändere die Farbe dieses Quadrates in 'neueFarbe'.
     */
    public void setColor(Color color) {
        this.color = color;
        draw();
    }

    /**
     * Zeichne dieses Square mit seinen aktuellen Werten auf den Bildschirm.
     */
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Rectangle(xPosition, yPosition,
                    size, size));
            canvas.pause(10);
        }
    }

    /**
     * Lösche dieses Square vom Bildschirm.
     */
    private void delete() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.remove(this);
        }
    }
}
