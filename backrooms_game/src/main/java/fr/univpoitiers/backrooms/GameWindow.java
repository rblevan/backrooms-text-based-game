package fr.univpoitiers.backrooms;

import fr.univpoitiers.backrooms.classes.Commands;
import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Represents the main Graphical User Interface (GUI) of the game.
 * It extends JFrame to create a window simulating a retro command-line terminal.
 * <p>
 * Key features:
 * <ul>
 * <li>Displays game text with a "typewriter" effect for immersion.</li>
 * <li>Captures user input and sends it to the {@link Commands} processor.</li>
 * <li>Allows toggling instant text display by pressing the 'M' key.</li>
 * </ul>
 */
public class GameWindow extends JFrame {

    private final JTextArea textArea;
    private final JTextField inputField;
    private Timer typewriterTimer;
    private final Commands commands;

    private boolean instantTextEnabled = false;
    private final Queue<String> textQueue = new LinkedList<>();
    private String currentTextToType;
    private int charIndex;

    /**
     * Constructs the Game Window and initializes all UI components.
     * Sets up the black background, text areas, input fields, and event listeners.
     *
     * @param commands The {@link Commands} controller instance used to process user inputs.
     */
    public GameWindow(Commands commands) {
        this.commands = commands;

        setTitle("Backrooms game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        // --- UI Components ---
        Color backgroundColor = Color.BLACK;
        Color textColor = Color.WHITE;
        Font font = new Font("Monospaced", Font.PLAIN, 14);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
        setContentPane(panel);

        // Text area for game output
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(backgroundColor);
        textArea.setForeground(textColor);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        // For this | to blink
        Caret caret = textArea.getCaret();
        caret.setVisible(true);
        ((DefaultCaret) caret).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Input field for user commands
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(backgroundColor);
        inputPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
        panel.add(inputPanel, BorderLayout.SOUTH);

        // Prompt label and input field
        JLabel promptLabel = new JLabel("> ");
        promptLabel.setForeground(textColor);
        promptLabel.setFont(font);
        promptLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        inputPanel.add(promptLabel, BorderLayout.WEST);

        // Input field
        inputField = new JTextField();
        inputField.setBackground(backgroundColor);
        inputField.setForeground(textColor);
        inputField.setFont(font);
        inputField.setCaretColor(textColor);
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 10));
        inputPanel.add(inputField, BorderLayout.CENTER);

        inputField.addActionListener(e -> {
            String command = inputField.getText();
            if (!command.trim().isEmpty()) {
                appendText("> " + command + "\n");
                inputField.setText("");

                String result = this.commands.processCommand(command);

                if ("QUIT_GAME".equals(result)) {
                    // This is the signal to close the game.
                    // dispose() closes the window and releases its resources.
                    // Because we set EXIT_ON_CLOSE, this will also terminate the application.
                    dispose();
                    return;
                }

                if (result != null && !result.trim().isEmpty()) {
                    appendText(result + "\n");
                }
            }
        });

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    instantTextEnabled = !instantTextEnabled;
                    String mode = instantTextEnabled ? "ENABLED" : "DISABLED";

                    if (instantTextEnabled && typewriterTimer.isRunning()) {
                        typewriterTimer.stop();
                        if (currentTextToType != null && charIndex < currentTextToType.length()) {
                            textArea.append(currentTextToType.substring(charIndex));
                        }
                        while (!textQueue.isEmpty()) {
                            textArea.append(textQueue.poll());
                        }
                        textArea.setCaretPosition(textArea.getDocument().getLength());
                        inputField.setEditable(true);
                    }
                }
            }
        });

        int delay = 30;
        typewriterTimer = new Timer(delay, e -> {
            // Check if we have finished the current text
            if (currentTextToType == null || charIndex >= currentTextToType.length()) {
                currentTextToType = textQueue.poll();
                charIndex = 0;

                if (currentTextToType == null) {
                    typewriterTimer.stop();
                    inputField.setEditable(true);
                    inputField.requestFocusInWindow();
                    return;
                }
            }

            textArea.append(String.valueOf(currentTextToType.charAt(charIndex)));
            textArea.setCaretPosition(textArea.getDocument().getLength());
            charIndex++;
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Appends text to the game's display area.
     * <p>
     * If "Instant Text" mode is enabled, the text appears immediately.
     * Otherwise, the text is added to a queue and displayed character-by-character
     * by a Timer to simulate a typing effect.
     *
     * @param text The string to be displayed in the terminal window.
     */
    public void appendText(final String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        if (instantTextEnabled) {
            textArea.append(text);
            textArea.setCaretPosition(textArea.getDocument().getLength());
        } else {
            textQueue.add(text);
            if (!typewriterTimer.isRunning()) {
                inputField.setEditable(false);
                typewriterTimer.start();
            }
        }
    }
}
