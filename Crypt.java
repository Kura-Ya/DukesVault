
//Duke's Vault v0.5

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class Crypt extends JFrame {
	// Serial Version ID, whatever that is
	private static final long serialVersionUID = -6464190016691859673L;

	static public String mode = "Nilfgaardian Empire";
	static public String f;
	static volatile public boolean wait = true;

	public static void main(String[] args) throws IOException, NullPointerException {

		// *** Creating the GUI *** //

		// JFrame
		JFrame frame = new JFrame("Duke's Vault v0.5");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel textpanel = new JPanel(new BorderLayout());
		JPanel inputpanel = new JPanel(new BorderLayout());
		

		// Text Area
		JTextArea textarea = new JTextArea();
		textarea.setFont(new Font("Arial", Font.PLAIN, 16));
		textarea.setLineWrap(true);
		textarea.setEditable(false);
		textarea.setVisible(true);
		textarea.setBackground(Color.GRAY);

		// Text Field
		JTextField input = new JTextField("commands");
		input.setFont(new Font("Arial", Font.BOLD, 13));
		input.setEditable(true);
		input.setBackground(Color.GRAY);
		input.setForeground(Color.GREEN);
		input.setVisible(true);

		// JButton
		JButton b = new JButton("Enter");
		b.setFont(new Font("Arial", Font.ITALIC, 13));
		b.setVisible(true);

		// JScrollPane (not functional)
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Initializing
		inputpanel.add(input, BorderLayout.SOUTH);
		inputpanel.add(b, BorderLayout.EAST);
		textpanel.add(scroll);
		textpanel.add(textarea, BorderLayout.CENTER);
		frame.add(textpanel, BorderLayout.NORTH);
		frame.add(inputpanel, BorderLayout.SOUTH);
		frame.setResizable(false);
		frame.setSize(500, 180);
		frame.setVisible(true);

		// *** End of GUI ***//

		// IO Streams
		PrintStream con = new PrintStream(new TextAreaOutputStream(textarea));
		System.setOut(con);
		System.setErr(con);
		FileInputStream in = null;
		FileOutputStream out = null;

		// FUNCTIONAL START
		Scanner stan = new Scanner(System.in);
		System.out.println("Duke's Vault: v0.5");
		System.out.println("E - Encrypt    D - Decrypt");

		// Action Listener

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = input.getText();
				input.setText("");
				wait = false;
			}
		});

		while (wait) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted!");
			}
		}

		wait = true;

		if (mode.equals("e")) {

			// Prompt for Filepath
			System.out.println("File:");

			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f = input.getText();
					input.setText("");
					wait = false;
				}
			});
			while (wait) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Sleep interrupted!");
				}
			}
		}

		wait = true;

		try {
			in = new FileInputStream(f);
			out = new FileOutputStream("Encrypted.vault");

			int q;

			while ((q = in.read()) != -1) {
				out.write(q - 1);
			}
		} finally {
			if (in != null) {
				in.close();
				stan.close();
				System.out.println("Encryption successful");
			}
			if (out != null) {
				out.close();
				try {
				Thread.sleep(3000);
				}
				catch (InterruptedException e) {
					System.err.println("The program wasn't able to exit... wtf?");
				}
				System.exit(0);
			}
		}

		if (mode.equals("d")) {

			// Prompt for Filepath
			System.out.println("File:");

			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f = input.getText();
					input.setText("");
					wait = false;
				}
			});
			while (wait) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Sleep interrupted!");
				}
			}
			wait = true;
			try {
				in = new FileInputStream(f);
				out = new FileOutputStream("Decrypted.txt");

				int q;

				while ((q = in.read()) != -1) {
					out.write(q + 1);
				}

			} finally {
				if (in != null) {
					in.close();
					stan.close();
					System.out.println("Decryption successful");
				}
				if (out != null) {
					out.close();
					try {
						Thread.sleep(3000);
					}
					catch (InterruptedException e) {
						System.err.println("The program couldn't even kill itself... :(");
					}
					System.exit(0);
				}
			}
		}
		
		else {

		System.err.println("Are you stupid?");
		System.err.println("Terminating Duke's Vault: Operator retarded");
		try {
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {
			System.err.println("Interrupted Exception");
		}
		System.exit(0);
		
		}
		
	}
}
