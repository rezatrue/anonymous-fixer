package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.events.Event;


public class MainFrame extends JFrame {
	private ComponentPanel componentPanel;
	private ExtendedPanel extendedPanel;
	private FileManager filemanager;
	private HashMap<String, String> data;
	private static File file;
	
	public MainFrame(){
		super("Anonymous finder");
		filemanager = new FileManager();
		data = new HashMap<String, String>();
		
		setLayout(new BorderLayout());
		
		componentPanel = new ComponentPanel();
		extendedPanel = new ExtendedPanel();
		
		add(componentPanel, BorderLayout.NORTH);
		add(extendedPanel, BorderLayout.SOUTH);
		
		
		
		componentPanel.setGoListener(new GoListioner(){
			public void start() {

				if (file == null) {
					// error massage: Select a Folder containing files 
					JOptionPane.showMessageDialog(MainFrame.this, "Select a Folder containing files", "Error", JOptionPane.ERROR_MESSAGE);

				}else {
					// count & collect folder name
					
					String[] fileName = null;
					try {
						fileName = filemanager.fileCounter(file);
						
						if (fileName != null) {
							data = filemanager.anonymousFinder(fileName, file);
							File dir = new File("Anonymous-files");
							while(dir.exists()) {
								// this method create problem 
//								filemanager.deleteFolder(dir);
								JOptionPane.showMessageDialog(MainFrame.this, "Please remove previous file named \"Anonymous-files\"", "Warning", JOptionPane.INFORMATION_MESSAGE);
							}
							dir.mkdir();
							
							filemanager.nameReplacer(data, file, dir);
							// send massage : Rename completed
							JOptionPane.showMessageDialog(MainFrame.this, "File Rename Completed", "Done", JOptionPane.INFORMATION_MESSAGE);
						} else {
							// send massage : Folder doesn't have an file
							JOptionPane.showMessageDialog(MainFrame.this, "Folder doesn't have an file", "Error", JOptionPane.ERROR_MESSAGE);

						}
						
					} catch (Exception e) {
						// send massage 
						System.out.println("File Not Found");
						System.out.println("Please select right folder to processed");
					}
				}

			}

		});
		
		componentPanel.setSourceListener(new SourceListener(){
			public void sourcechooser(SourceEvent ev){
				MainFrame.file = ev.getFile();

			}
		});


		
		
		setMinimumSize(new Dimension(450,100));
		setMaximumSize(new Dimension(450, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

}
