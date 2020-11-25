package uhr_sunan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

public class Controller extends KeyAdapter implements ActionListener {

	private static final String KNOPF_EIN = "Ein";
	private static final String KNOPF_AUS = "Aus";
	private List<DigitalUhr> dUhren = new LinkedList<>();
	private List<KreisUhr> kUhren = new LinkedList<>();
	private DigitalUhr du;
	private KreisUhr ku;
	private Uhr uhr;

	/**
	 * Ein Controller fuer die Starter
	 * 
	 * @param uhrzeit
	 */
	public Controller(Uhr uhrzeit) {
		uhr = uhrzeit;
	}

	/**
	 * Wenn Kreis geklickt wird, wird KreisUhr in die Liste reingehen. Wenn Digital
	 * geklickt wird, wird DigitalUhr in die Liste reingehen.
	 * 
	 * @param button das zu klickende Button
	 */
	public void buttonklickBehandeln(JButton button) {
		if (button.getText().equals("Kreis")) {
			ku = new KreisUhr(this);
			kUhren.add(ku);
			uhr.anmelden(ku);
		} else if (button.getText().equals("Digital")) {
			du = new DigitalUhr(this);
			dUhren.add(du);
			uhr.anmelden(du);
		} else {
			for (KreisUhr k : kUhren)
				k.dispose();
			for (DigitalUhr d : dUhren)
				d.dispose();
			kUhren.clear();
			dUhren.clear();
		}
	}

	/**
	 * Taste 'E' schaltet die Uhr ein, 'A' schaltet sie aus, alle anderen Tasten
	 * werden ignoriert
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		for (KreisUhr k : kUhren) {
			switch (Character.toUpperCase(e.getKeyChar())) {
			case 'E':
				k.uhrAn = true;
				break; // "Ein"
			case 'A':
				k.uhrAn = false;
				break; // "Aus"
			}
			k.repaint();
		}
	}

	/**
	 * schaltet je nach gedrückten Knopf die Uhrzeitanzeige ein (Ein), die
	 * Uhrzeitanzeige aus (Aus)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (DigitalUhr d : dUhren) {
			switch (e.getActionCommand()) {
			case KNOPF_EIN: // Uhrzeitanzeige anschalten
				d.uhrAn = true;
				break;
			case KNOPF_AUS: // Uhrzeitanzeige ausschalten
				d.uhrAn = false;
				break;
			}
			d.knoepfe[0].setEnabled(!d.uhrAn);
			d.knoepfe[1].setEnabled(d.uhrAn);
		}
	}

}
