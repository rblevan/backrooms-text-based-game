package fr.univpoitiers.backrooms;

import fr.univpoitiers.backrooms.classes.Commands;
import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private JTextArea textArea;
    private JTextField inputField;
    private Timer typewriterTimer;
    private String textToType;
    private int charIndex;

    // NEW: Add an attribute to hold the command processor
    private final Commands commands;

    // NEW: The constructor now requires a Commands object
    public GameWindow(Commands commands) {
        this.commands = commands; // Store the provided Commands instance

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
                // 1. Display the user's command immediately
                appendText("> " + command + "\n");
                inputField.setText(""); // Clear the input field right away

                // 2. Process the command and get the result
                String result = this.commands.processCommand(command);

                // 3. Display the result if there is one
                if (result != null && !result.trim().isEmpty()) {
                    appendText(result + "\n");
                }
            }
        });

        int delay = 40;
        typewriterTimer = new Timer(delay, e -> {
            if (charIndex < textToType.length()) {
                textArea.append(String.valueOf(textToType.charAt(charIndex)));
                textArea.setCaretPosition(textArea.getDocument().getLength());
                charIndex++;
            } else {
                typewriterTimer.stop();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Appends text to the game's output area with a typewriter effect.
     * @param text The text to append.
     */
    public void appendText(final String text) {
        if (typewriterTimer.isRunning()) {
            typewriterTimer.stop();
        }
        this.textToType = text;
        this.charIndex = 0;
        typewriterTimer.start();
    }
}
