package figures;

import java.awt.*;

/**
 * Eine Persion, die manipuliert werden kann und sich selbst auf einer Canvas
 * zeichnet.
 *
 * @author Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */

public class Person {

    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private Color color;
    private boolean isVisible;

    /**
     * Erzeuge eine Person mit einer Standardfarbe an einer Standardposition.
     */
    public Person() {
        height = 60;
        width = 30;
        xPosition = 280;
        yPosition = 190;
        color = Color.BLACK;
        isVisible = false;
    }

    /**
     * Mache diese Person sichtbar. Wenn sie bereits sichtbar ist, tue nichts.
     */
     void setVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Mache diese Person unsichtbar. Wenn sie bereits unsichtbar ist, tue
     * nichts.
     */
    public void setInvisible() {
        delete();
        isVisible = false;
    }

    /**
     * Bewege diese Person einige Bildschirmpunkte nach rechts.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Bewege diese Person einige Bildschirmpunkte nach links.
     */
    public void moveLeft() {
        moveHorizontal(-20);
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
     * Bewege diese Person horizontal um 'entfernung' Bildschirmpunkte.
     */
    private void moveHorizontal(int distance) {
        delete();
        xPosition += distance;
        draw();
    }

    /**
     * Bewege diese Person vertikal um 'entfernung' Bildschirmpunkte.
     */
    private void moveVertical(int distance) {
        delete();
        yPosition += distance;
        draw();
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
     * Ändere die Farbe dieser Person in 'neueFarbe'.
     */
    public void setColor(Color color) {
        this.color = color;
        draw();
    }

    /**
     * Zeichne diese Person mit ihren aktuellen Werten auf den Bildschirm.
     */
    private void draw() {
        int bh = (int)(height * 0.7);  // Körpergröße
        int hh = (height - bh) / 2;  // halbe Kopfgröße
        int hw = width / 2;  // halbe Breite
        int x = xPosition;
        int y = yPosition;

        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpunkte = { x-3, x-hw, x-hw, x-(int)(hw*0.2)-1, x-(int)(hw*0.2)-1, x-hw,
                              x-hw+(int)(hw*0.4)+1, x, x+hw-(int)(hw*0.4)-1, x+hw, x+(int)(hw*0.2)+1,
                              x+(int)(hw*0.2)+1, x+hw, x+hw, x+3, x+(int)(hw*0.6),
                              x+(int)(hw*0.6), x+3, x-3, x-(int)(hw*0.6), x-(int)(hw*0.6) };
            int[] ypunkte = { y, y+(int)(bh*0.2), y+(int)(bh*0.4), y+(int)(bh*0.2),
                              y+(int)(bh*0.5), y+bh, y+bh, y+(int)(bh*0.65), y+bh, y+bh,
                              y+(int)(bh*0.5), y+(int)(bh*0.2), y+(int)(bh*0.4), y+(int)(bh*0.2),
                              y, y-hh+3, y-hh-3, y-hh-hh, y-hh-hh, y-hh-3, y-hh+3 };
            canvas.draw(this, color, new Polygon(xpunkte, ypunkte, 21));
            canvas.pause(10);
        }
    }

    /**
     * Lösche diese Person vom Bildschirm.
     */
    private void delete() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.remove(this);
        }
    }
}
