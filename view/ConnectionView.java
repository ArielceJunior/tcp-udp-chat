package br.edu.ifsuldeminas.sd.chat.view;

import javax.swing.*;
import java.awt.*;

public class ConnectionView extends JFrame {

    private JTextField txtLocalPort;
    private JTextField txtRemotePort;
    private JTextField txtRemoteIp;
    private JTextField txtName;
    private JButton btnConnect;
    private JRadioButton udpButton;
    private JRadioButton tcpButton;
    
    public ConnectionView() {

        setTitle("Conectar ao Chat");
        setSize(420, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(
                new Color(30,30,30)
        );

        initializeComponents();
        setVisible(true);
    }

    private JTextField createField(
            JPanel panel,
            String labelText,
            Font font
    ) {

        JLabel label = new JLabel(labelText);

        label.setForeground(Color.WHITE);

        label.setFont(font);

        JTextField field = new JTextField();

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE,35));
        field.setFont(font);
        field.setBackground(new Color(40,40,40));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(10));

        return field;
    }
    
    private void initializeComponents() {
    	
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24,24,24));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,80));

        Font font = new Font("Arial", Font.PLAIN, 14);
        JLabel title = new JLabel("UDP / TCP CHAT");

        title.setForeground(Color.WHITE);

        title.setFont(new Font("Arial", Font.BOLD, 22));

        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
        panel.add(title);

        panel.add(Box.createVerticalStrut(20));

        JLabel lblProtocol = new JLabel("Protocolo:");
        lblProtocol.setForeground(Color.WHITE);

        udpButton = new JRadioButton("UDP");
        tcpButton = new JRadioButton("TCP");

        udpButton.setSelected(true);

        udpButton.setBackground(new Color(24,24,24));
        tcpButton.setBackground(new Color(24,24,24));

        udpButton.setForeground(Color.WHITE);
        tcpButton.setForeground(Color.WHITE);

        ButtonGroup group = new ButtonGroup();

        group.add(udpButton);
        group.add(tcpButton);

        JPanel protocolPanel =
                new JPanel(new FlowLayout(FlowLayout.LEFT));

        protocolPanel.setBackground(new Color(24,24,24));
        protocolPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        protocolPanel.add(lblProtocol);
        protocolPanel.add(udpButton);
        protocolPanel.add(tcpButton);
        protocolPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));

        panel.add(protocolPanel);

        txtLocalPort = createField(
                panel,
                "Porta Local:",
                font
        );

        txtRemoteIp = createField(
                panel,
                "IP Remoto:",
                font
        );

        txtRemotePort = createField(
                panel,
                "Porta Remota:",
                font
        );

        txtName = createField(
                panel,
                "Nome:",
                font
        );

        panel.add(Box.createVerticalStrut(15));

        btnConnect = new JButton("Conectar");

        styleButton(btnConnect);

        btnConnect.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnConnect.setMaximumSize(
                new Dimension(140,40)
        );

        panel.add(btnConnect);

        add(panel);

        btnConnect.addActionListener(
                e -> connect()
        );
    }

    private void styleButton(JButton button) {

        button.setBackground(new Color(70,130,180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void connect() {

        try {

            int localPort = Integer.parseInt(txtLocalPort.getText());
            int remotePort = Integer.parseInt(txtRemotePort.getText());
            String remoteIp = txtRemoteIp.getText().trim();
            String name = txtName.getText().trim();
            String protocol = udpButton.isSelected()? "UDP" : "TCP";
           
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite um nome.");

                return;
            }

            new ChatView(
            		protocol,
                    localPort,
                    remoteIp,
                    remotePort,
                    name
            );

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Dados inválidos."
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                ConnectionView::new
        );
    }
}