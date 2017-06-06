package gui;
import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

}
