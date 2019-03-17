package simulation;

import java.util.List;
import java.util.Random;

/**
 * Animal ist eine abstrakte Superklasse für Tiere.
 * Sie verwaltet Eigenschaften, die alle Tiere gemein haben,
 * wie etwas das Alter oder eine Position.
 */
@SuppressWarnings("WeakerAccess")
public abstract class Animal {

    // Ein gemeinsamer Zufallsgenerator, der die Geburten steuert.
    protected static final Random rand = Randomizer.getRandomizer();

    // Das Alter des Tieres
    protected int age;

    // Ist dieses Tier noch lebendig?
    private boolean alive;

    // Das Feld des Tieres
    private Field field;

    // Die Position dieses Tieres.
    private Position position;

    /**
     * Erzeuge ein neues Animal an der gegebenen Position im Field.
     *
     * @param field Das aktuelle belegte Field
     * @param position Die Position im Field
     */
    public Animal(Field field, Position position) {
        alive = true;
        this.field = field;
        setLocation(position);
    }

    /**
     * Lasse dieses Tier agieren - es soll das tun, was
     * es tun muss oder möchte.
     *
     * @param animals Liste zum Aufnehmen neuer Tiere.
     */
    abstract public void act(List<Animal> animals);

    /**
     * Prüfe, ob dieses Tier noch lebendig ist.
     *
     * @return true wenn dieses Animal noch alive ist.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Anzeigen, dass das Tier nicht mehr länger lebendig ist
     * Es wird aus dem Field entfernt.
     */
    protected void die() {
        alive = false;
        if (position != null) {
            field.clear(position);
            position = null;
            field = null;
        }
    }

    /**
     * Liefere die Position dieses Tieres.
     */
    protected Position getPosition() {
        return position;
    }

    /**
     * Setze das Tier auf die gegebene Position im aktuellen Field.
     *
     * @param position Die neue Position des Tieres.
     */
    protected void setLocation(Position position) {
        if (this.position != null) {
            field.clear(this.position);
        }
        this.position = position;
        field.place(this, position);
    }

    /**
     * Liefere das Field des Tieres.
     *
     * @return das Field des Tieres.
     */
    protected Field getField() {
        return field;
    }
}
