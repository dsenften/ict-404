package figures;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Canvas ist eine Klasse, die einfache Zeichenoperationen auf einer
 * leinwandartigen Zeichenfläche ermöglicht. Sie ist eine vereinfachte Version
 * der Klasse Canvas (englisch für Canvas) des JDK und wurde speziell für das
 * Projekt "Figuren" geschrieben.
 */
public class Canvas {

    // Hinweis: Die Implementierung dieser Klasse (insbesondere die
    // Verwaltung der Farben und Identitäten der Figuren) ist etwas
    // komplizierter als notwendig. Dies ist absichtlich so, weil damit
    // die Schnittstellen und Exemplarvariablen der Figuren-Klassen
    // für den Lernanspruch dieses Projekts einfacher und klarer
    // sein können.

    private static Canvas canvasSingleton;

    /**
     * Fabrikmethode, die eine Referenz auf das einzige Exemplar dieser Klasse
     * zurückliefert. Wenn es von einer Klasse nur genau ein Exemplar gibt, wird
     * dieses als 'Singleton' bezeichnet.
     */
    static Canvas getCanvas() {
        if (canvasSingleton == null) {
            canvasSingleton = new Canvas("Figuren Demo", 500, 300,
                    Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    // Eigenschaften/Attribute der Klasse

    private JFrame window;
    private DrawingArea drawingSurface;
    private Graphics2D graphic;
    private Color backgroudColor;
    private Image canvasImage;
    private List<Object> figures;
    private Map<Object, ShapeWithColor> shapeFigure;

    /**
     * Erzeuge eine Canvas.
     *
     * @param title          Titel, der im Rahmen der Canvas angezeigt wird
     * @param width          die gewünschte Breite der Canvas
     * @param height         die gewünschte Höhe der Canvas
     * @param backgoundColor die Hintergrundfarbe der Canvas
     */
    private Canvas(String title, int width, int height, Color backgoundColor) {
        window = new JFrame();
        drawingSurface = new DrawingArea();
        window.setContentPane(drawingSurface);
        window.setTitle(title);
        window.setLocation(30, 30);
        drawingSurface.setPreferredSize(new Dimension(width, height));
        backgroudColor = backgoundColor;
        window.pack();
        figures = new ArrayList<>();
        shapeFigure = new HashMap<>();
    }

    /**
     * Setze, ob diese Canvas sichtbar sein soll oder nicht. Wenn die Canvas
     * sichtbar gemacht wird, wird ihr Fenster in den Vordergrund geholt. Diese
     * Operation kann auch benutzt werden, um ein bereits sichtbares
     * Leinwandfenster in den Vordergrund (vor andere Fenster) zu holen.
     *
     * @param isVisible boolean für die gewünschte Sichtbarkeit: true für sichtbar,
     *                  false für nicht sichtbar.
     */
    private void setVisible(boolean isVisible) {
        if (graphic == null) {
            Dimension size = drawingSurface.getSize();
            canvasImage = drawingSurface.createImage(size.width, size.height);
            graphic = (Graphics2D) canvasImage.getGraphics();
            graphic.setColor(backgroudColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        window.setVisible(isVisible);
    }

    /**
     * Zeichne für das gegebene Figur-Objekt eine Java-Figur (einen Shape) auf
     * die Canvas.
     *
     * @param figure das Figur-Objekt, für das ein Shape gezeichnet werden soll
     * @param color  die Farbe der Figur
     * @param shape  ein Objekt der Klasse Shape, das tatsächlich gezeichnet wird
     */
    void draw(Object figure, Color color, Shape shape) {
        figures.remove(figure); // entfernen, falls schon eingetragen
        figures.add(figure);    // am Ende hinzufügen
        shapeFigure.put(figure, new ShapeWithColor(shape, color));
        redraw();
    }

    /**
     * Entferne die gegebene Figur von der Canvas.
     *
     * @param figure die Figur, deren Shape entfernt werden soll
     */
    void remove(Object figure) {
        figures.remove(figure); // entfernen,falls schon eingetragen
        shapeFigure.remove(figure);
        redraw();
    }

    /**
     * Setze die Zeichenfarbe der Canvas.
     *
     * @param color der Name der neuen Zeichenfarbe.
     */
    public void setColor(Color color) {
        graphic.setColor(color);
    }

    /**
     * Warte für die angegebenen Millisekunden. Mit dieser Operation wird eine
     * Verzögerung definiert, die für animierte Zeichnungen benutzt werden kann.
     *
     * @param milliseconds die zu wartenden Millisekunden
     */
    void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            // Exception ignorieren
        }
    }

    /**
     * Zeichne erneut alle Figuren auf der Canvas.
     */
    private void redraw() {
        delete();
        for (Object figur : figures) {
            shapeFigure.get(figur).draw(graphic);
        }
        drawingSurface.repaint();
    }

    /**
     * Lösche die gesamte Canvas.
     */
    private void delete() {
        Color original = graphic.getColor();
        graphic.setColor(backgroudColor);
        Dimension size = drawingSurface.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }

    /***************************************************************************
     * Interne Klasse DrawingArea - die Klasse für die GUI-Komponente, die
     * tatsächlich im Canvas-Fenster angezeigt wird. Diese Klasse definiert
     * ein JPanel mit der zusätzlichen Möglichkeit, das auf ihm gezeichnet Image
     * aufzufrischen (erneut zu zeichnen).
     */
    private class DrawingArea extends JPanel {
        private static final long serialVersionUID = 20060330L;

        public void paint(Graphics g) {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }

    /***************************************************************************
     * Interne Klasse ShapeWithColor - Da die Klasse Shape des JDK nicht auch
     * eine Farbe mitverwalten kann, muss mit dieser Klasse die Verknüpfung
     * modelliert werden.
     */
    private class ShapeWithColor {
        private Shape shape;
        private Color color;

        ShapeWithColor(Shape shape, Color color) {
            this.shape = shape;
            this.color = color;
        }

        void draw(Graphics2D graphic) {
            setColor(color);
            graphic.fill(shape);
        }
    }

}
