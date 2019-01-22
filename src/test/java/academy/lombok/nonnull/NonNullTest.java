package academy.lombok.nonnull;

import academy.inheritance.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NonNullTest {

    @Test
    @DisplayName("Test plain Java with 'null' argument")
    void testVanillaJavaWithNull() {
        assertThrows(NullPointerException.class,
                () -> new VanillaJava(null));
    }

    @Test
    @DisplayName("Test plain Java with valid argument")
    void testVanillaJavaWithoutNull() {
        Person person = new Person("Peter", "Müller");
        assertDoesNotThrow(() -> new VanillaJava(person));
    }

    @Test
    @DisplayName("Test lombok version with 'null' argument")
    void testWithLombokAndNull() {
        assertThrows(NullPointerException.class,
                () -> new WithLombok(null));
    }

    @Test
    @DisplayName("Test lombok version with valid argument")
    void testWithLombokAndNotNull() {
        Person person = new Person("Peter", "Müller");
        assertDoesNotThrow(() -> new WithLombok(person));
    }
}