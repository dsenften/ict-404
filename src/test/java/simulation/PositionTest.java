package simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: Daniel Senften <daniel@senften.org>
 * Date: 2019-03-16, 21:06
 */
class PositionTest {

    private static final int ROW = 12;
    private static final int COLUMN = 13;

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(ROW,COLUMN);
    }

    @Test
    void equals() {
        Position otherPosition = new Position(ROW,COLUMN);
        assertEquals(position, otherPosition);
    }

    @Test
    void testToString() {
        String expected = ROW + "," + COLUMN;
        assertEquals(expected, position.toString());
    }

    @Test
    void testHashCode() {
        int hashCode = (ROW << 16) + COLUMN;
        assertEquals(hashCode, position.hashCode());
    }

    @Test
    void getRow() {
        assertEquals(ROW, position.getRow());
    }

    @Test
    void getColumn() {
        assertEquals(COLUMN, position.getColumn());
    }
}
