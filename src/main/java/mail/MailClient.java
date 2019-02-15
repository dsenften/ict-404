package mail;

/**
 * Eine Klasse, die einen einfachen Email-Client modelliert.
 * Ein Client ist einem bestimmten Benutzer zugeordnet und
 * sendet und empfängt Nachrichten über einen festgelegten Server.
 *
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class MailClient
{
    // der Server zum Senden und Empfangen
    private MailServer server;
    // der Benutzer, dem dieser Client zugeordnet ist
    private String user;

    /**
     * Erzeuge einen MailClient für den angegebenen Benutzer
     * und verbinde ihn mit dem gegebenen Server.
     * @param server der MailServer für diesen Client
     * @param user der Name des Benutzers dieses MailClients.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
    }

    /**
     * Liefere die nächste Message (falls vorhanden)
     * für den Benutzer dieses Clients.
     */
    public Message gibNaechsteNachricht()
    {
        return server.getNextMessageFor(user);
    }

    /**
     * Gib die nächste Message (falls vorhanden) für den
     * Benutzer dieses Clients auf der Konsole aus.
     */
    public void printNextMessage()
    {
        Message message = server.getNextMessageFor(user);
        if(message == null) {
            System.out.println("Keine neue Message.");
        }
        else {
            message.print();
        }
    }

    /**
     * Sende den gegebenen Nachrichtentext an den angebenen
     * Empfänger über den zugeordneten Mail-Server.
     * @param empfaenger der gewünschte Empfänger
     * @param text der Text der Message
     */
    public void sendMessage(String empfaenger, String text)
    {
        Message message = new Message(user, empfaenger, text);
        server.forward(message);
    }
}
