package mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Ein simples Modell eines Mail-Servers. Der Server kann Nachrichten
 * entgegen nehmen und auf Anfrage an Mail-Klienten weiterleiten.
 */
public class MailServer
{
    // Speicherstruktur für die Nachrichten, die auf dem Server
    // zwischengespeichert werden.
    private List<Message> messages;

    /**
     * Erzeuge einen MailServer.
     */
    public MailServer()
    {
        messages = new ArrayList<Message>();
    }

    /**
     * Liefere die Anzahl der Nachrichten für den angegebenen Benutzer.
     * @param user Benutzer, dessen Nachrichtenanzahl geliefert
     *                  werden soll.
     * @return die Anzahl der Nachrichten für 'benutzer'.
     */
    public int numberOfMessagesFor(String user)
    {
        int anzahl = 0;
        for (Message message : messages) {
            if(message.getReceiver().equals(user)) {
                anzahl++;
            }
        }
        return anzahl;
    }

    /**
     * Liefere die nächste Message für den angegebenen Benutzer.
     * Liefere null, falls keine Nachrichten vorhanden sind.
     * @param user Benutzer, für den die Message geliefert
     *                  werden soll.
     * @return die nächste Message, falls vorhanden, null sonst.
     */
    public Message getNextMessageFor(String user)
    {
        Iterator<Message> it = messages.iterator();
        while(it.hasNext()) {
            Message message = it.next();
            if(message.getReceiver().equals(user)) {
                it.remove();
                return message;
            }
        }
        return null;
    }

    /**
     * Leite die angegebene Message auf Anfrage weiter.
     * @param message die Message, die auf dem Server hinterlegt
     *                   werden soll
     */
    public void forward(Message message)
    {
        messages.add(message);
    }
}
