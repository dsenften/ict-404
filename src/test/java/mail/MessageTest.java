package mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    void setUp() {
        sender = "Peter";
        message = new Message(sender,receiver,text);
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
        // TODO To be done
    }
}
