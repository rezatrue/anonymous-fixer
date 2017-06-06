package gui;
import java.io.File;
import java.util.EventObject;


public class SourceEvent extends EventObject {
	private File file;
	public SourceEvent(Object eev) {
		super(eev);
	}
	
	public SourceEvent(Object eev, File file) {
		super(eev);
	this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}
