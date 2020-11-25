package uhr_sunan;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Startet eine Uhrenoberfläche
 * 
 * @author Doro
 *
 */
public class Starter extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JButton btnKreis;
	private final JButton btnDigital;
	private final JButton btnHalt;

	/**
	 * erzeugt die Oberfläche und bringt sie auf den Bildschirm
	 */
	public Starter(Controller con) {
		setTitle("Uhren-Anzeiger");
		setSize(400, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		// der Button btnKreis erzeugt eine KreisUhr
		btnKreis = new JButton("Kreis");
		btnKreis.addActionListener(e -> con.buttonklickBehandeln(btnKreis));
		add(btnKreis);

		// der Button btnDigital erzeugt eine DigitalUhr
		btnDigital = new JButton("Digital");
		btnDigital.addActionListener(e -> con.buttonklickBehandeln(btnDigital));
		add(btnDigital);

		// der Button btnHalt löscht alle Uhranzeige-Fenster vom
		// Bildschirm und leert die beiden Listen
		btnHalt = new JButton("alle entfernen");
		btnHalt.addActionListener(e -> con.buttonklickBehandeln(btnHalt));
		add(btnHalt);

		// Auf den Bildschirm bringen:
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Starter(new Controller(new Uhr()));
	}
}
