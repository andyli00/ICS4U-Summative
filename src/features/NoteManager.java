package features;

import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.ByteBuffer;

/**
 * Class that manages notes
 * @author Andy Li
 * @since Nov 17, 2017
 */
public class NoteManager {
	
	private ArrayList<Note> notes = new ArrayList<>(1);
	
	public NoteManager() {
		run();
	}
	
	/**
	 * creates a new note
	 * @param body body text of the note
	 */
	void create(String body) {
		Note n = new Note(body, this);
		notes.add(n);
	}
	
	/**
	 * deletes a note
	 * @param n note object to be deleted
	 */
	void delete(Note n) {
		notes.remove(n);
		n.window.close();
	}
	
	/**
	 * reads a text file
	 * @param path system path to the file
	 * @param encoding charset used to decode the output
	 * @return the file's contents
	 * @throws IOException
	 */
	private static String readFile(String path, Charset encoding)
			throws IOException
	{
		byte[] encodedText = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encodedText)).toString();
	}
	
	/**
	 * loads text into new notes
	 */
	private void loadNotes() {
		//creates a folder for notes at C:/Users/username/.common_management/stickynotes
		File notesFolder = new File(System.getProperty("user.home") + "/.common_management/stickynotes");
		notesFolder.mkdirs();
		File[] noteFiles = notesFolder.listFiles();
		
		for (File note: noteFiles)
			if (note.isFile()) {
				try {
					create(readFile(note.getPath(), Charset.forName("UTF-8")));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "File error", JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	/**
	 * saves text from notes into files
	 */
	private void saveNotes() {
		File notesFolder = new File(System.getProperty("user.home") + "/.common_management/stickynotes");
		notesFolder.mkdirs();
		File[] noteFiles = notesFolder.listFiles();
		
		for (File note: noteFiles) {
			if (note.isFile())
				note.delete();
		}
		
		int i = 0;
		for (Note note: notes) {
			try {
				PrintWriter writer = new PrintWriter(notesFolder.getPath() + '/' + i++ + ".txt");
				writer.write(note.getText());
				writer.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "File error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * main run function
	 */
	private void run() {
		loadNotes();
		
		if (notes.isEmpty())
			create("");
		
		showAll();
		
		//when the program is closed, makes sure the notes are saved
		//a shutdown hook basically holds off on closing the program
		//until a certain action is performed (saving the all notes in this case)
		Runtime.getRuntime().addShutdownHook(new Thread(this::saveNotes));
	}
	
	/**
	 * shows all hidden notes
	 */
	void showAll() {
		for (Note note: notes)
			if (!note.window.isShowing())
				note.window.show();
	}
	
	/**
	 * @return whether or not all notes are hidden
	 */
	boolean allHidden() {
		for (Note note: notes)
			if (note.window.isShowing())
				return false;
		return true;
	}
}
