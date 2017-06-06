package gui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class FileManager {
	
	public void FileManager(){
	}
	
	public String[] fileCounter(File folder){
		String[] fileName = null;
		fileName = folder.list();
		return fileName;
	}
	
	public HashMap<String, String> anonymousFinder(String[] fileName, File folder){
		HashMap<String, String> data = new HashMap<String, String>();
		String ns = null;
		for(int x = 0; x < fileName.length; x++) {
			
			try {
				Scanner sf = new Scanner(new File(folder.toString()+ "\\" + fileName[x]));
				String ss = sf.nextLine();

				if (ss.equals("Name:	Anonymous")) {
					
					do {
						ns = sf.nextLine();
					} while (!(ns.equals("Resume")));
					sf.nextLine();
					ns = sf.nextLine();
					
					data.put(fileName[x], ns);
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return data;
		
	}

	public void deleteFolder(File dir){
		String[] fileName = dir.list();
		while (dir.exists()) {
			if (dir.length()==0) {
				dir.delete();
			} else {
				for (String s : fileName) {
					File currentFile = new File(dir.getPath(), s);
					currentFile.delete();
				}
			}
		}
		
	}
	
	public void nameReplacer(HashMap<String, String> data, File from, File to){
		
		Set<String> filenames = data.keySet();
		Iterator<String> it = filenames.iterator();
		
		while (it.hasNext()) {
			String ss = it.next();
									
						// replace the name
						String linedata = null;
						String rname = null;
										
						try {
							FileReader fr = new FileReader(from.toString() + "\\" + ss);
							BufferedReader br = new BufferedReader(fr);
							FileWriter fw = new FileWriter(to.toString() + "\\" + ss);
							BufferedWriter bw = new BufferedWriter(fw);
							
							while ((linedata = br.readLine())!=null) {

								if (linedata.endsWith("Anonymous")) {
									// Name:	
									rname = "Name:	" + data.get(ss);
									bw.write(rname +"\r\n");
								}else {
									bw.write(linedata +"\r\n");
								}
								
							}
							bw.close();	
							br.close();
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								
		}
			
		/*
			private static void copyFileUsingFileStreams(File source, File dest)
		throws IOException {
	InputStream input = null;
	OutputStream output = null;
	try {
		input = new FileInputStream(source);
		output = new FileOutputStream(dest);
		byte[] buf = new byte[1024];
		int bytesRead;
		while ((bytesRead = input.read(buf)) > 0) {
			output.write(buf, 0, bytesRead);
		}
	} finally {
		input.close();
		output.close();
	}
}
			
		*/	
			
		
		
		
	}
}
