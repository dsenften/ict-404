package figures;

import java.awt.*;

@SuppressWarnings("unused")
public class Person extends Figure {

    private int height;
    private int width;

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
     * Ändere die Höhe in 'height' und die Breite in 'width'. Beide
     * Angaben müssen grösser gleich Null sein.
     */
    public void setSize(int height, int width) {
        this.height = height;
        this.width = width;
        draw();
    }


    /**
     * Zeichne diese Person mit ihren aktuellen Werten auf den Bildschirm.
     */
    protected void draw() {
        int bh = (int)(height * 0.7);  // Körpergröße
        int hh = (height - bh) / 2;  // halbe Kopfgröße
        int hw = width / 2;  // halbe Breite
        int x = xPosition;
        int y = yPosition;

        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { x-3, x-hw, x-hw, x-(int)(hw*0.2)-1, x-(int)(hw*0.2)-1, x-hw,
                              x-hw+(int)(hw*0.4)+1, x, x+hw-(int)(hw*0.4)-1, x+hw, x+(int)(hw*0.2)+1,
                              x+(int)(hw*0.2)+1, x+hw, x+hw, x+3, x+(int)(hw*0.6),
                              x+(int)(hw*0.6), x+3, x-3, x-(int)(hw*0.6), x-(int)(hw*0.6) };
            int[] ypoints = { y, y+(int)(bh*0.2), y+(int)(bh*0.4), y+(int)(bh*0.2),
                              y+(int)(bh*0.5), y+bh, y+bh, y+(int)(bh*0.65), y+bh, y+bh,
                              y+(int)(bh*0.5), y+(int)(bh*0.2), y+(int)(bh*0.4), y+(int)(bh*0.2),
                              y, y-hh+3, y-hh-3, y-hh-hh, y-hh-hh, y-hh-3, y-hh+3 };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 21));
            canvas.pause(10);
        }
    }

}
