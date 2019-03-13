package figures;

import java.awt.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Figure {

    // Position der Figur auf der Zeichenfläche
    protected int xPosition;
    protected int yPosition;

    // Farbe der Figur
    protected Color color;

    // Ist die Figur sichtbar?
    protected boolean isVisible;

    /**
     * Zeichne dieses Triangle mit seinen aktuellen Werten auf den Bildschirm.
     */
    protected abstract void draw();

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
     * Bewege diese Person einige Bildschirmpunkte nach oben.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Bewege diese Person einige Bildschirmpunkte nach unten.
     */
    public void moveDown() {
        moveVertical(20);
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
     * Bewege dieses Triangle horizontal um 'entfernung' Bildschirmpunkte.
     */
    public void moveHorizontal(int distance) {
        xPosition += distance;
        draw();
    }

    /**
     * Bewege dieses Triangle vertikal um 'distance' Bildschirmpunkte.
     */
    public void moveVertical(int distance) {
        yPosition += distance;
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
     * Mache diese Figur sichtbar. Wenn es bereits sichtbar ist, tue nichts.
     */
    public void setVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Mache diese Figur unsichtbar. Wenn es bereits unsichtbar ist, tue
     * nichts.
     */
    public void setInvisible() {
        delete();
        isVisible = false;
    }

    /**
     * Lösche diese Figur vom Bildschirm.
     */
    protected void delete() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.remove(this);
        }
    }

}
