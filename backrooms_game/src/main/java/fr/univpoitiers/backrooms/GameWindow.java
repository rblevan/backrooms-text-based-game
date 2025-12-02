package fr.univpoitiers.backrooms;

import fr.univpoitiers.backrooms.classes.Commands;
import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class GameWindow extends JFrame {

    private final JTextArea textArea;
    private final JTextField inputField;
    private Timer typewriterTimer;
    private final Commands commands;

    private final Queue<String> textQueue = new LinkedList<>();
    private String currentTextToType; // The text currently being typed.
    private int charIndex;

    public GameWindow(Commands commands) {
        this.commands = commands;

        setTitle("Backrooms game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        // UI Components
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
                if (result != null && !result.trim().isEmpty()) {
                    appendText(result + "\n");
                }
            }
        });

        int delay = 40;
        typewriterTimer = new Timer(delay, e -> {
            // Check if we have finished the current text
            if (currentTextToType == null || charIndex >= currentTextToType.length()) {
                // Try to get the next text from the queue
                currentTextToType = textQueue.poll(); // poll() returns null if the queue is empty
                charIndex = 0; // Reset for the new text

                // If the queue was empty, there's nothing more to do. Stop the timer.
                if (currentTextToType == null) {
                    typewriterTimer.stop();
                    return; // Exit the action listener for this tick
                }
            }

            // If we are here, we have text to type. Append one character.
            textArea.append(String.valueOf(currentTextToType.charAt(charIndex)));
            textArea.setCaretPosition(textArea.getDocument().getLength());
            charIndex++;
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * NEW: Adds text to a queue to be displayed sequentially.
     * Starts the typewriter effect if it's not already running.
     * @param text The text to append.
     */
    public void appendText(final String text) {
        if (text == null || text.isEmpty()) {
            return; // Don't add empty text to the queue
        }
        textQueue.add(text); // Add the new text to the end of the queue

        // If the timer isn't already running, start it.
        // It will then automatically pick up the text from the queue.
        if (!typewriterTimer.isRunning()) {
            typewriterTimer.start();
        }
    }
}
