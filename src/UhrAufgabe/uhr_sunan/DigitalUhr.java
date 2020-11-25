package uhr_sunan;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Stellt eine Digitale Uhr dar, die man anhalten und weiterlaufen lassen kann
 *
 */
public class DigitalUhr extends JFrame implements PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private static final String TITEL = "Digitaluhr";
	private static final String KNOPF_EIN = "Ein";
	private static final String KNOPF_AUS = "Aus";
	private static final int BREITE = 500;
	private static final int HOEHE = 300;

	private JLabel anzeige;
	protected JButton[] knoepfe; // Ein = Einschalten der Anzeige,
									// Aus = Ausschalten der Anzeige,

	protected boolean uhrAn = true;
	private Uhr uhr;

	/**
	 * erstellt das Fenster für die digitale Uhr und bringt es auf den Bildschirm;
	 * zu Beginn läuft die Uhr im 1-Sekunden-Takt
	 */
	public DigitalUhr(Controller con) {
		uhrAn = true;

		// Erstellung der Oberflächenelemente:
		setTitle(TITEL);
		setSize(BREITE, HOEHE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(new JLabel(TITEL));
		anzeige = new JLabel(String.format("%02d:%02d:%02d", 00, 00, 00));
		add(anzeige);
		knoepfe = new JButton[2];
		knoepfe[0] = new JButton(KNOPF_EIN);
		knoepfe[1] = new JButton(KNOPF_AUS);
		for (JButton knopf : knoepfe) {
			super.add(knopf);
		}
		knoepfe[0].setEnabled(false); // "Ein"

		// Zufügen des ActionListeners zu den Buttons
		for (JButton knopf : knoepfe) {
			knopf.addActionListener(con);
		}

		// Auf den Bildschirm bringen:
		pack();
		setVisible(true);
	}

	/**
	 * Holen der aktuellen Uhrzeit und Anzeige, wenn die Uhr angestellt ist
	 */
	private void tick() {
		if (uhrAn) {
			anzeige.setText(String.format("%02d:%02d:%02d", uhr.getStunde(), uhr.getMinute(), uhr.getSekunde()));
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		uhr = (Uhr) evt.getSource();
		tick();
	}
}
