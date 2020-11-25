package uhr_sunan;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;

/**
 * eine Uhr mit Sekundenzählung
 */
public class Uhr {
	private int stunde, minute, sekunde;
	private int alt_stunde, alt_minute, alt_sekunde;

	private PropertyChangeSupport prop = new PropertyChangeSupport(this);

	/**
	 * erstellt die Uhr
	 */
	public Uhr() {
		// Thread starten, um die Uhrzeit laufen zu lassen:
		new Thread() {
			/**
			 * löst jede Sekunde die Aktualisierung der Uhrzeit aus
			 */
			@Override
			public void run() {
				try {
					while (true) {
						laufen();
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
				}
			}
		}.start();
	}

	/**
	 * liefert die aktuelle Stunde
	 * 
	 * @return
	 */
	public int getStunde() {
		return stunde;
	}

	/**
	 * liefert die aktuelle Minute
	 * 
	 * @return
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * liefert die aktuelle Sekunde
	 * 
	 * @return
	 */
	public int getSekunde() {
		return sekunde;
	}

	private void laufen() {
		alt_stunde = this.stunde;
		alt_minute = this.minute;
		alt_sekunde = this.sekunde;
		LocalTime jetzt = LocalTime.now();
		stunde = jetzt.getHour();
		minute = jetzt.getMinute();
		sekunde = jetzt.getSecond();
		prop.firePropertyChange("Stunde", alt_stunde, stunde);
		prop.firePropertyChange("Minute", alt_minute, minute);
		prop.firePropertyChange("Sekunde", alt_sekunde, sekunde);
	}

	/**
	 * meldet p am Subjekt an
	 * 
	 * @param p der Beobachter
	 */
	public void anmelden(PropertyChangeListener p) {
		prop.addPropertyChangeListener(p);
	}

	/**
	 * meldet p am Subjekt ab
	 * 
	 * @param p der Beobachter
	 */
	public void abmelden(PropertyChangeListener p) {
		prop.removePropertyChangeListener(p);
	}

}
