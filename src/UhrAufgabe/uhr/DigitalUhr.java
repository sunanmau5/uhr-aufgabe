package uhr;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Stellt eine Digitale Uhr dar, die man anhalten und weiterlaufen lassen kann
 *
 */
public class DigitalUhr extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private static final String TITEL = "Digitaluhr";
	private static final String KNOPF_EIN = "Ein";
	private static final String KNOPF_AUS = "Aus";
	private static final int BREITE = 500;
	private static final int HOEHE = 300;

	private JLabel anzeige;
	private JButton[] knoepfe; // Ein = Einschalten der Anzeige, 
							  // Aus = Ausschalten der Anzeige, 
	
	private boolean uhrAn = true;
	private Uhr uhr;
	
	/**
	 * erstellt das Fenster für die digitale Uhr und bringt es auf den
	 * Bildschirm; zu Beginn läuft die Uhr im 1-Sekunden-Takt
	 */
	public DigitalUhr() {
		uhrAn = true;
		uhr = new Uhr();

		// Erstellung der Oberflächenelemente:
		setTitle(TITEL);
		setSize(BREITE, HOEHE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(new JLabel(TITEL));
		anzeige = new JLabel();
		add(anzeige);
		knoepfe = new JButton[2];
		knoepfe[0] = new JButton(KNOPF_EIN);
		knoepfe[1] = new JButton(KNOPF_AUS);
		for (JButton knopf : knoepfe) {
			super.add(knopf);
		}
		knoepfe[0].setEnabled(false); // "Ein"

		// Erstellen des ActionListeners für die beiden Buttons (Ein, Aus):
		ActionListener lauscher = new ActionListener() {
			/**
			 * schaltet je nach gedrückten Knopf die Uhrzeitanzeige ein (Ein), die Uhrzeitanzeige
			 * aus (Aus) 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case KNOPF_EIN:   //Uhrzeitanzeige anschalten
					uhrAn = true;
					break;
				case KNOPF_AUS:  //Uhrzeitanzeige ausschalten
					uhrAn = false;
					break;
				}
				knoepfe[0].setEnabled(!uhrAn);
				knoepfe[1].setEnabled(uhrAn);
			}
		};

		// Zufügen des ActionListeners zu den Buttons
		for (JButton knopf : knoepfe)
		{
			knopf.addActionListener(lauscher);
		}
		
		//Thread starten, um die Uhrzeit laufen zu lassen:
		new Thread() {
			/**
			 * löst jede Sekunde die Aktualisierung der Anzeige aus
			 */
			@Override
			public void run() {
				try {
					while (true) {
						tick();
						Thread.sleep(1000);
					}
				}
				catch (InterruptedException e) {}
			}
		}.start();

		// Auf den Bildschirm bringen:
		pack();
		setVisible(true);
	}

	/**
	 * Holen der aktuellen Uhrzeit und Anzeige, wenn die Uhr angestellt ist
	 */
	private void tick() 
	{
		if (uhrAn)
		{
			anzeige.setText(String.format("%02d:%02d:%02d", uhr.getStunde(), uhr.getMinute(),
					uhr.getSekunde()));
		}
	}
}
