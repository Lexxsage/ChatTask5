package GUI;

import javax.swing.*;
import java.awt.*;


public class ChatFrame extends JFrame {

    private JPanel chatPanel;
    private JTextField messageField;
    private JScrollPane messagesPane;
    private JScrollPane onlineUsersPane;
    private JTextPane messagesArea;
    private JTextPane onlineUsers;
    private JButton sendButton;
    private String message = null;
    private String str;

    public ChatFrame(String login) {

        chatPanel = new JPanel();
        setTitle("Chat - " + "[" + login + "]");
        add(chatPanel);
        messageField = new JTextField();

        chatPanel.setLayout(new GridBagLayout());
        chatPanel.setBackground(new Color(-16751002));
        chatPanel.setEnabled(true);
        chatPanel.setFont(new Font("Comic Sans MS", chatPanel.getFont().getStyle(), chatPanel.getFont().getSize()));

        messagesArea = new JTextPane();
        messagesPane = new JScrollPane(messagesArea);
        //messagesPane.add(messagesArea);
        messagesPane.setVisible(true);
        messagesArea.setEditable(false);

        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 300;
        gbc.ipady = 400;
        gbc.insets = new Insets(10, 10, 5, 10);
        chatPanel.add(messagesPane, gbc);
        sendButton = new JButton();
        sendButton.setText("SEND");
        sendButton.setVerticalAlignment(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 10, 10);
        chatPanel.add(sendButton, gbc);
        messageField.setColumns(30);
        messageField.setEditable(true);
        messageField.setEnabled(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 50.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 5;
        gbc.insets = new Insets(5, 10, 10, 0);
        chatPanel.add(messageField, gbc);
        final JLabel label1 = new JLabel();
        label1.setEnabled(true);
        label1.setFont(new Font("Comic Sans MS", label1.getFont().getStyle(), 14));
        label1.setForeground(new Color(-16711936));
        label1.setText("Online");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 0, 0);
        chatPanel.add(label1, gbc);

        //gbc.ipadx = 50;
        //gbc.ipady = 50;
        onlineUsers = new JTextPane();
        onlineUsersPane = new JScrollPane(onlineUsers);
        //onlineUsersPane.setSize(100,250);
        //onlineUsersPane.add(onlineUsers);
        onlineUsersPane.setVisible(true);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 50.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 10);
        chatPanel.add(onlineUsersPane, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 620, 500);
        chatPanel.setVisible(true);
        setResizable(false);
        setVisible(true);

    }

    public JButton getSendButton(){
        return sendButton;
    }

    public JTextField getTextField(){
        return messageField;
    }

    public JTextPane getMessagesPane() {
        return messagesArea;
    }

    public JTextPane getOnlineUsersList() {
        return onlineUsers;
    }

    public String getText() {
        //str = message;
        //message = null;
        //System.out.println("Get: " +message );
        return messageField.getText();
    }

}
