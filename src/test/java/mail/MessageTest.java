package mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * User: Daniel Senften <daniel@senften.org>
 * Date: 2019-02-20, 13:31
 */
@SuppressWarnings("FieldCanBeLocal")
class MessageTest {

    private Message message;
    private String sender;
    private String receiver;
    private String text;

    private final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        sender = "Peter";
        receiver = "Pan";
        text = "Text Meldung";
        
        message = new Message(sender, receiver, text);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void getSender() {
        assertEquals(sender, message.getSender());
    }

    @Test
    void getReceiver() {
        assertEquals(receiver, message.getReceiver());
    }

    @Test
    void getText() {
        assertEquals(text, message.getText());

    }

    @Test
    void print() {
        message.print();

        String expected = "Von: Peter\nAn: Pan\nText: Text Meldung\n";
        assertEquals(expected, outContent.toString());
    }
}
