package figures;

import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class HomeSweetHome
{
    private Square wall;
    private Square window;
    private Triangle roof;
    private Circle sun;

    public static void main(String[] args) {

        HomeSweetHome homeSweetHome = new HomeSweetHome();
        homeSweetHome.draw();

        //noinspection InfiniteLoopStatement
        while (true) {
            Canvas.getCanvas().pause(1000);
            homeSweetHome.setBlackAndWhite();

            Canvas.getCanvas().pause(1000);
            homeSweetHome.changeColor();
        }
    }
    /**
     * Zeichne das Bild.
     */
    public void draw()
    {
        wall = new Square();
        wall.moveHorizontal(-140);
        wall.moveVertical(20);
        wall.setSize(120);
        wall.setVisible();

        window = new Square();
        window.setColor(Color.BLACK);
        window.moveHorizontal(-120);
        window.moveVertical(40);
        window.setSize(40);
        window.setVisible();

        roof = new Triangle();
        roof.changeSize(60, 180);
        roof.moveHorizontal(20);
        roof.moveVertical(-60);
        roof.setVisible();

        sun = new Circle();
        sun.setColor(Color.YELLOW);
        sun.moveHorizontal(100);
        sun.moveVertical(-40);
        sun.setSize(80);
        sun.setVisible();
    }

    /**
     * Ändere die Darstellung in schwarz-weiß.
     */
    public void setBlackAndWhite()
    {
        if(wall != null)   // nur wenn schon gezeichnet wurde ...
        {
            wall.setColor(Color.BLACK);
            window.setColor(Color.WHITE);
            roof.setColor(Color.BLACK);
            sun.setColor(Color.BLACK);
        }
    }

    /**
     * Ändere die Darstellung in Farbe.
     */
    public void changeColor()
    {
        if(wall != null)   // nur wenn schon gezeichnet wurde ...
        {
            wall.setColor(Color.RED);
            window.setColor(Color.BLACK);
            roof.setColor(Color.GREEN);
            sun.setColor(Color.YELLOW);
        }
    }
}
