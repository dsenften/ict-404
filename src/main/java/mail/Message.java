package mail;

/**
 * Eine Klasse, die einfache Nachrichten modelliert. Eine Message hat
 * einen Absender und einen Empfänger und enthält Text.
 */
@SuppressWarnings("WeakerAccess")
public class Message {
    // Der Absender der Message
    private String sender;
    // Der gewünschte Empfänger der Message
    private String receiver;
    // Der Text der Message
    private String text;

    /**
     * Erzeuge eine Message vom gegebenen 'sender' an den gegebenen
     * 'receiver' mit dem gegebenen 'text'.
     *
     * @param sender   der Absender dieser Message
     * @param receiver der gewünschte Empfänger dieser Message.
     * @param text     der Text der Message.
     */
    public Message(String sender, String receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
    }

    /**
     * @return den Absender dieser Message.
     */
    public String getSender() {
        return sender;
    }

    /**
     * @return den gewünschten Empfänger dieser Message.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @return den Text dieser Message.
     */
    public String getText() {
        return text;
    }

    /**
     * Gib Informationen über diese Message auf der Konsole aus.
     */
    public void print() {
        System.out.println("Von: " + sender);
        System.out.println("An: " + receiver);
        System.out.println("Text: " + text);
    }

    @Override
    public boolean equals(Object obj) {
        boolean returnValue = false;
        if (obj instanceof Message) {
            Message message = (Message) obj;
            returnValue = (this.sender == message.sender &&
                    this.receiver == message.receiver &&
                    this.text == message.text);
        }
        return returnValue;
    }
}
