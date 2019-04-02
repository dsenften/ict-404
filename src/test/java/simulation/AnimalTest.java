package simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: Daniel Senften <daniel@senften.org>
 * Date: 2019-03-19, 20:42
 */
class AnimalTest {

    private final Field field = new Field(12, 24);
    private final Position position = new Position(0,0);

    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Animal(field, position) {
            @Override
            public void act(List<Animal> animals) {
                throw new RuntimeException("Dies ist nur ein Testfall!");
            }
        };
    }

    @Test
    void isAlive() {
        assertTrue(animal.isAlive());
    }

    @Test
    void die() {
    }

    @Test
    void getPosition() {
        assertEquals(position, animal.getPosition());
    }

    @Test
    void setLocation() {
    }

    @Test
    void getField() {
    }
}
