package time;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Eine sehr einfache GUI (graphical user interface) für die Display.
 * In dieser Implementierung rückt die Watch pro Sekunde um 3 Minuten vor, um
 * das Testen der Anzeige zu beschleunigen.
 *
 * @author Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */
public class Watch {
    private JFrame window;
    private JLabel label;
    private Display display;
    private boolean isRunning = false;

    /**
     * Konstruktor für ein Exemplar von Watch
     */
    public Watch() {
        displayWindow();
        display = new Display();
    }

    /**
     *
     */
    private void start() {
        isRunning = true;
        TimerThread timerThread = new TimerThread();
        timerThread.start();
    }

    /**
     *
     */
    private void stop() {
        isRunning = false;
    }

    /**
     *
     */
    private void advance() {
        display.clockSignal();
        label.setText(display.getWatch());
    }

    /**
     * 'Info'-Funktion: Zeige Informationen zur Anwendung.
     */
    private void showInfo() {
        JOptionPane.showMessageDialog(window,
                "Watch Version 1.0\n" +
                        "Eine einfache Benutzeroberfläche für das 'Java lernen mit BlueJ'-Projekt Zeitanzeige",
                "Info Watch",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 'Beenden'-Funktion: Beenden der Anwendung.
     */
    private void exit() {
        System.exit(0);
    }


    /**
     * Erzeuge das Swing-Fenster und seinen Inhalt.
     */
    private void displayWindow() {
        window = new JFrame("Watch");
        JPanel contentPane = (JPanel) window.getContentPane();
        contentPane.setBorder(new EmptyBorder(1, 60, 1, 60));

        showMenu(window);

        //Ein Layout mit hübschen Abständen definieren
        contentPane.setLayout(new BorderLayout(12, 12));

        // Das mittige Anzeigefeld erzeugen
        label = new JLabel("00:00", SwingConstants.CENTER);
        Font displayFont = label.getFont().deriveFont(96.0f);
        label.setFont(displayFont);
        //imagePanel.setBorder(new EtchedBorder());
        contentPane.add(label, BorderLayout.CENTER);

        // Die Werkzeugleiste mit den Knöpfen erzeugen
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(1, 0));

        JButton startButton = new JButton("start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        toolbar.add(startButton);

        JButton stopButton = new JButton("stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });
        toolbar.add(stopButton);

        JButton advanceButton = new JButton("advance");
        advanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                advance();
            }
        });
        toolbar.add(advanceButton);

        // Werkzeugleiste mit Flow-Layout zur räumlichen Trennung in ein Panel legen
        JPanel flow = new JPanel();
        flow.add(toolbar);

        contentPane.add(flow, BorderLayout.SOUTH);

        // Aufbau abgeschlossen - Komponenten arrangieren
        window.pack();

        // Das Fenster in die Bildschirmmitte platzieren und anzeigen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(d.width / 2 - window.getWidth() / 2, d.height / 2 - window.getHeight() / 2);
        window.setVisible(true);
    }

    /**
     * Erzeugen der Menüzeile.
     *
     * @param window Das Fenster, in das die Menüleiste eingefügtwerden soll.
     */
    private void showMenu(JFrame window) {
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        JMenuBar menuLine = new JMenuBar();
        window.setJMenuBar(menuLine);

        JMenu menue;
        JMenuItem eintrag;

        // Das Datei-Menü erzeugen
        menue = new JMenu("Datei");
        menuLine.add(menue);

        eintrag = new JMenuItem("Info...");
        eintrag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInfo();
            }
        });
        menue.add(eintrag);

        menue.addSeparator();

        eintrag = new JMenuItem("exit");
        eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        eintrag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        menue.add(eintrag);
    }

    class TimerThread extends Thread {
        public void run() {
            while (isRunning) {
                advance();
                pause();
            }
        }

        private void pause() {
            try {
                Thread.sleep(300);   // Pause für 300 Millisekunden
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static void main(String[] args) {
        Watch watch = new Watch();
    }
}
