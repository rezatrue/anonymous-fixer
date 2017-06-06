package gui;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ComponentPanel extends JPanel {
	private JLabel sourceLebel;
	private JTextField textField;
	private JButton sourceButton;
	private JButton goButton;
	private JButton openButton;
	private GoListioner goListioner;
	private SourceListener sourceListener;
	private JFileChooser fileChooser;
	private Desktop desktop;
	

	
	public ComponentPanel(){
		
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		sourceLebel = new JLabel("Source :");
		textField = new JTextField(15);
		sourceButton = new JButton("Browse");
		goButton = new JButton("GO");
		openButton = new JButton("Open Files");
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Select CV Folder");
//		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		fileChooser.setAcceptAllFileFilterUsed(false);
		
		sourceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eev) {
				SourceEvent ev = null;
				if (fileChooser.showOpenDialog(ComponentPanel.this) == JFileChooser.APPROVE_OPTION) {
							File file = fileChooser.getCurrentDirectory();
							textField.setText(fileChooser.getCurrentDirectory().toString());
							ev = new SourceEvent(this, file);
						}
						if (sourceListener != null) {
							sourceListener.sourcechooser(ev);
						}
						
			}
		});
		
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (goListioner != null) {
					goListioner.start();
				}
			}
		});
		
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktop = Desktop.getDesktop();
				File dir = new File("Anonymous-files");
				if(dir.exists()) {
					try {
						desktop.open(new File("Anonymous-files"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(ComponentPanel.this, "There is no folder named \"Anonymous-files\" \r\n Please run application.", "Notification", JOptionPane.INFORMATION_MESSAGE);

				}
				
			}
		});
		
		
		componentLayout();
		
	}

	private void componentLayout() {
		GridBagConstraints gc = new GridBagConstraints();

		// first row 
		
				gc.gridx = 0;
				gc.gridy = 0;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.gridheight = 1;
				gc.gridwidth = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.LINE_START;
				gc.insets = new Insets(5, 10, 5, 5);
				add(sourceLebel, gc);
				
				gc.gridx++;
				gc.insets = new Insets(5, 0, 5, 0);

				add(textField, gc);
				
				gc.gridx++;
				gc.fill = GridBagConstraints.BOTH;
				gc.insets = new Insets(5, 5, 5, 5);

				add(sourceButton, gc);
				
				gc.gridx++;
				gc.gridheight = 2;
				gc.fill = GridBagConstraints.BOTH;
				gc.anchor = GridBagConstraints.CENTER;
				add(goButton, gc);
				
				gc.gridx = 2;
				gc.gridy = 2;
				gc.weightx = 1;
				gc.weighty = 2;
				gc.gridheight = 1;
				gc.gridwidth = 1;
				gc.fill = GridBagConstraints.BOTH;
				gc.anchor = GridBagConstraints.LINE_START;
				gc.insets = new Insets(1, 5, 5, 5);
				add(openButton, gc);
	}

	public void setGoListener(GoListioner golistioner) {
		this.goListioner = golistioner;		
	}

	public void setSourceListener(SourceListener sListener) {
		this.sourceListener = sListener;
	}
}
