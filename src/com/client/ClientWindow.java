package com.client;

import com.client.sign.Signlink;

import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class ClientWindow extends Client implements ActionListener, WindowListener {

    private static final long serialVersionUID = -6978617783576386732L;

<<<<<<< Updated upstream
	private void addButtons() {
		JPanel buttonBar = new JPanel();
		buttonBar.setSize(frame.getWidth(),30);
		buttonBar.setBorder(BorderFactory.createLineBorder(Color.yellow));
		frame.getContentPane().add(buttonBar,BorderLayout.PAGE_END);
	}

	public void initUI() {
		try {
			icon = new ImageIcon(new URL(ClientProperties.getInstance().getClientIcon())).getImage();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			frame = new JFrame(ClientProperties.getInstance().getGameName());
			frame.setLayout(new BorderLayout());
			setFocusTraversalKeysEnabled(false);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setPreferredSize(new Dimension(765, 503));
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
//			addButtons();
			frame.pack();
			insets = frame.getInsets();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
=======
    public void initUI() {
        try {
            String iconUrl = "https://i.imgur.com/7d2q7wU.png";
            ImageIcon icon = new ImageIcon(new URL(iconUrl));
            Image resizedIcon = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIconImage = new ImageIcon(resizedIcon);
>>>>>>> Stashed changes

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);
            frame = new JFrame(ClientProperties.getInstance().getGameName());
            frame.setIconImage(icon.getImage());
            frame.setLayout(new BorderLayout());
            setFocusTraversalKeysEnabled(false);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create the panel to hold the game view, buttons, and membership benefits button
            JPanel mainPanel = new JPanel(new BorderLayout());
            // Set the background color of the main panel to the system's background color
            Color backgroundColor = UIManager.getColor("Panel.background");
            mainPanel.setBackground(backgroundColor);

            // Create the game panel
            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new BorderLayout());
            gamePanel.add(this);
            gamePanel.setPreferredSize(new Dimension(765, 503));
            mainPanel.add(gamePanel, BorderLayout.CENTER);

            // Create a panel for the buttons and benefits button
            JPanel buttonsAndBenefitsPanel = new JPanel(new BorderLayout());

            // Create the button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

            // Create additional buttons
            JButton lolButton = createStyledButton("Discord");
            lolButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.google.com/"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            buttonPanel.add(lolButton);

            JButton helloButton = createStyledButton("Jewel Store");
            helloButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.google.com/"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            buttonPanel.add(helloButton);

            JButton noButton = createStyledButton("Hiscores");
            noButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.google.com/"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            buttonPanel.add(noButton);

            JButton hahaButton = createStyledButton("YouTube");
            hahaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.google.com/"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            buttonPanel.add(hahaButton);

            // Add the button panel to the buttons and benefits panel
            buttonsAndBenefitsPanel.add(buttonPanel, BorderLayout.LINE_START);

            // Create the membership benefits button
            JButton benefitsButton = createStyledButton("Membership Benefits");
            benefitsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create a new frame for displaying the benefits
                    JFrame benefitsFrame = new JFrame("Membership Benefits");
                    String benefitsIconUrl = "https://i.imgur.com/t01MyqO.png";  // Replace with the URL of the benefits icon
                    ImageIcon benefitsIcon = null;
                    try {
                        benefitsIcon = new ImageIcon(new URL(benefitsIconUrl));
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                    benefitsFrame.setIconImage(benefitsIcon.getImage());
                    // Resize the icon image
                    int iconWidth = 11200;  // Adjust the desired width
                    int iconHeight = 11200; // Adjust the desired height
                    Image resizedBenefitsIcon = benefitsIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                    benefitsIcon = new ImageIcon(resizedBenefitsIcon);
                    benefitsFrame.setIconImage(resizedBenefitsIcon);
                    JTextArea textArea = new JTextArea("+5% magic find \r\n"
                            + "+3 Exchange Slots\r\n"
                            + "Exclusive Shop Offers\r\n"
                            + "2 Shiny keys a day\r\n"
                            + "Access to exclusive items (see exclusive item list)\r\n"
                            + "+20% Play Pass XP\r\n"
                            + "Up-to 2 additional play pass rewards per level\r\n"
                            + "+50% Minnows caught at fishing platform\r\n"
                            + "50% less likely to lose minnows while fishing\r\n"
                            + "Can catch the 'Red Herring' while fishing\r\n"
                            + "Can craft exclusive magic tablets\r\n"
                            + "Item Retrieval is 10% the item's base value instead of 15%\r\n"
                            + "Can use the \"Open all\" option on all available interactions\r\n"
                            + "Exclusive boss instances\r\n"
                            + "Tier 3 boss instances");
                    textArea.setEditable(false);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    benefitsFrame.getContentPane().add(scrollPane);
                    benefitsFrame.setSize(450, 350);
                    benefitsFrame.setLocationRelativeTo(frame);
                    benefitsFrame.setVisible(true);
                }
            });

            // Create a panel to hold the benefits button with right alignment
            JPanel benefitsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            benefitsPanel.add(benefitsButton);

            // Add the benefits panel to the buttons and benefits panel
            buttonsAndBenefitsPanel.add(benefitsPanel, BorderLayout.LINE_END);

            // Add the buttons and benefits panel to the main panel
            mainPanel.add(buttonsAndBenefitsPanel, BorderLayout.PAGE_END);

            frame.getContentPane().add(mainPanel);
            frame.pack();
            insets = frame.getInsets();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(new Color(30, 144, 255)); // Blue color
        button.setBackground(new Color(0, 0, 255)); // Blue color
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFocusPainted(false); // Remove the button's focus border
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovering over the button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 120, 215)); // Change background color on mouse hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255)); // Reset background color when mouse exits
            }
        });
        return button;
    }

    public ClientWindow(String args[]) {
        super();
        try {
            Signlink.startpriv(InetAddress.getByName(ClientProperties.getInstance().getServerAddress()));
            initUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL("http://" + ClientProperties.getInstance().getServerAddress() + "/overlays");
        } catch (Exception e) {
            return super.getCodeBase();
        }
    }

    @Override
    public URL getDocumentBase() {
        return getCodeBase();
    }

    public void loadError(String s) {
        System.out.println("loadError: " + s);
    }

    @Override
    public String getParameter(String key) {
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    private static JFrame frame;

    public static JFrame getFrame() {
        return frame;
    }

    private static Insets insets;

    public static Insets getInset() {
        return insets;
    }
    
    // Logout method to move the client box to the center of the screen
    public void logout() {
        frame.setLocationRelativeTo(null);
    }
}
