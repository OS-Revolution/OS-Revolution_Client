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

}