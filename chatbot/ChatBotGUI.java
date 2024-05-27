package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChatBotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Map<String, String> responses;

    public ChatBotGUI() {
        // Set up the frame
        setTitle("ChatBot");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new JButton("Send");
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        // Initialize responses
        responses = new HashMap<>();
        responses.put("hello", "Hello! How can I help you today?");
        responses.put("how are you", "I'm just a bunch of code, but I'm doing fine! How about you?");
        responses.put("bye", "Goodbye! Have a great day!");
        responses.put("what's your name", "I am a simple chatbot created to assist you.");
        responses.put("who created you", "I was created by a programmer.");
        responses.put("what can you do", "I can chat with you and answer simple questions.");
        responses.put("tell me a joke", "Why did the scarecrow win an award? Because he was outstanding in his field!");
        responses.put("what's the weather", "I can't check the weather, but you can look it up online!");
        responses.put("what's your favorite color", "I love all colors equally, but blue is quite calming!");
        responses.put("how old are you", "I don't have an age like humans do, but I was created recently.");
        responses.put("what's your favorite food", "I don't eat, but I imagine pizza would be delicious!");
        responses.put("where are you from", "I live in the digital world, inside your computer.");
        responses.put("what's your purpose", "My purpose is to assist and chat with you!");


        // Add action listener for the send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Add action listener for the input field (press Enter to send message)
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String input = inputField.getText().trim().toLowerCase();
        if (input.isEmpty()) {
            return;
        }

        chatArea.append("You: " + input + "\n");
        inputField.setText("");

        if (input.equalsIgnoreCase("exit")) {
            chatArea.append("ChatBot: Goodbye!\n");
            System.exit(0);
        }

        String response = generateResponse(input);
        chatArea.append("ChatBot: " + response + "\n");
    }

    private String generateResponse(String input) {
        for (String key : responses.keySet()) {
            if (input.contains(key)) {
                return responses.get(key);
            }
        }
        return "I'm sorry, I don't understand that. Can you please rephrase?";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatBotGUI().setVisible(true);
            }
        });
    }
}
