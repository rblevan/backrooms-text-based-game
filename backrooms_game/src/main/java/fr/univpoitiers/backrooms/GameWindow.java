package fr.univpoitiers.backrooms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private JTextArea textArea;
    private JTextField inputField;

    public GameWindow() {
        setTitle("Backrooms game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        // --- UI Components ---
        // Colors and Font
        Color backgroundColor = Color.BLACK;
        Color textColor = Color.WHITE;
        Font font = new Font("Monospaced", Font.PLAIN, 14);

        // Main Panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
        setContentPane(panel);

        // Text Area for game output
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(backgroundColor);
        textArea.setForeground(textColor);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        // --- Input Panel at the bottom ---
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(backgroundColor);
        // Add a top border to separate it from the text area
        inputPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
        panel.add(inputPanel, BorderLayout.SOUTH);

        // The ">" prompt
        JLabel promptLabel = new JLabel("> ");
        promptLabel.setForeground(textColor);
        promptLabel.setFont(font);
        promptLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0)); // Padding
        inputPanel.add(promptLabel, BorderLayout.WEST);

        // Input field for user commands
        inputField = new JTextField();
        inputField.setBackground(backgroundColor);
        inputField.setForeground(textColor);
        inputField.setFont(font);
        inputField.setCaretColor(textColor); // Make the cursor visible
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 10)); // Remove default border and add padding
        inputPanel.add(inputField, BorderLayout.CENTER);

        // --- Event Listener for input ---
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = inputField.getText();
                if (!command.trim().isEmpty()) {
                    // Display the command in the text area
                    appendText("> " + command + "\n");
                    // TODO: Process the command here
                    inputField.setText(""); // Clear the input field after processing
                }
            }
        });

        // Center and show the window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Appends text to the game's output area.
     * @param text The text to append.
     */
    public void appendText(final String text) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(text);
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }
}
