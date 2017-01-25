import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

// For Duke's Vault - This class is used to project System.out[put] to the DV console.

public class TextAreaOutputStream extends OutputStream {
private JTextArea textControl;

public TextAreaOutputStream(JTextArea control) {
	textControl = control;
}

// Here's the bit where we write to the console.

public void write(int b) throws IOException {
	textControl.append(String.valueOf((char)b));
}
}
