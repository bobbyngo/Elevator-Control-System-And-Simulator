package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * Creates a TextArea for subsystem print logs.
 * 
 * @author Trong Nguyen
 */
public class LogConsole {
	private JTextArea console;
	private String title;

	/**
	 * Constructor for LogConsole.
	 * 
	 * @param name String, to identify the frame
	 */
	public LogConsole(String name) {
		this.title = name;
		console = new JTextArea();
		initConsole();
	}

	/**
	 * Creates a frame text area for console log output.
	 */
	private void initConsole() {
		console.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
		console.setLineWrap(true);
		console.setWrapStyleWord(true);
		JScrollPane areaScrollPane = new JScrollPane(console);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(800, 500));
		areaScrollPane.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)), areaScrollPane.getBorder()));

		DefaultCaret caret = (DefaultCaret) console.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JPanel schedulerPanel = new JPanel(new BorderLayout());
		schedulerPanel.add(areaScrollPane, BorderLayout.CENTER);
		// Create and set up the window.
		JFrame frame = new JFrame(title + " Log");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create and set up the content pane.
		Container newContentPane = schedulerPanel;
		frame.setContentPane(newContentPane);
		frame.setPreferredSize(new Dimension(500, 300));
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Updates the console log with each print statement.
	 * 
	 * @param log String, the text to be displayed
	 */
	public void appendLog(String log) {
		console.append(log);
	}
	
}
