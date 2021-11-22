package de.uk.java;

import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		/*
		 * Spielvariablen definieren Maximale Leben Das Wort, was es zu erraten gilt
		 * Benutzte Buchstaben als String gespeichert Das bisher erratene Wort (mit den
		 * Buchstaben die man auswählt)
		 */
		int lives = 5;
		String wordToGuess = "Donnerstag";
		String usedChars = "";
		String guessedWord = "";

		// Aus dem wordToGuess ein Wort mit Unterstrichen generieren
		for (int i = 0; i < wordToGuess.length(); i++) {
			guessedWord += "_";
		}
		System.out.println(guessedWord);

		// Gameloop - Läuft solange wir noch Leben haben
		while (lives != 0) {
			// In jedem Durchlauf fragen wir nach einem Buchstaben
			System.out.println("Welchen Buchstaben willst du erraten?");

			// Input in der Konsole einlesen und als Buchstaben (char) als Variable
			// speichern
			char guessedChar = new Scanner(System.in).next().charAt(0);

			/*
			 * Überprüfen, ob der Buchstabe bereits genannt wurde Wir schauen ob mit dem
			 * eingegebenen Buchstaben in dem String usedChars ein Index verbunden ist Falls
			 * das der Fall ist ( >=0 ) wurde der Buchstabe schon genannt Siehe Definition
			 * der Methode indexOf
			 */
			if (usedChars.indexOf(guessedChar) >= 0) {
				System.out.println("Den Buchstaben hast du schon geraten.");
				// Falls nicht fügen wir den Buchstaben dem String usedChars hinzu
			} else {
				usedChars += guessedChar;
				// Überprüfen ob der Buchstabe im zu erratenden Wort enthalten ist
				if (wordToGuess.indexOf(guessedChar) >= 0) {
					// Falls ja, wird das guessedWord aktualisiert und mit dem entsprechenden
					// Buchstaben ergänzt
					guessedWord = "";

					/*
					 * In der Schleife wird das guessedWord mit Buchstaben gefüllt, die sowohl im
					 * wordToGuess, als auch im usedChars String enthalten sind. Die entsprechende
					 * Position (anhand der Zählervariable i) wird übernommen. Falls an einer
					 * Position des Wortes der richtige Buchstabe noch nicht erraten wurde, wird
					 * wieder ein Unterstrich eingefügt
					 */
					for (int i = 0; i < wordToGuess.length(); i++) {
						guessedWord += usedChars.indexOf(wordToGuess.charAt(i)) >= 0 ? wordToGuess.charAt(i) : "_";
					}
					// Wenn das Wort noch Leerstellen beinhaltet, haben wir noch nicht gewonnen und
					// können weiter raten.
					if (guessedWord.contains("_")) {
						System.out.printf(
								"Gut geraten, " + guessedChar + " gibt es im Wort. " + "Aber es fehlt noch was!%n");
						System.out.println(guessedWord);
						// Falls kein Unterstrich mehr im Wort enthalten ist, haben wir das Wort
						// vollständig erraten und haben gewonnen.
					} else {
						System.out.println("Du hast gewonnen, du hast das Wort erraten. --> " + guessedWord
								+ "\nDu hattest " + lives + " Leben übrig.");
						// Bricht die While-Schleife ab
						break;
					}
					// Wenn der Buchstabe nicht existiert:
				} else {
					// Überprüfen, ob wir noch genug Leben haben
					if (lives > 1) {
						// Leben um 1 reduzieren
						lives--;
						System.out.println("Der Buchstabe existiert nicht im Wort. Somit verlierst du ein Leben."
								+ "\nDamit bleiben dir noch " + lives + " Leben.");
						System.out.println(guessedWord);
						// Wenn alle Leben verbraucht sind, ist das Spiel vorbei.
					} else {
						System.out.println("Du hast verloren!");
						break;
					}
				}
			}
		}

	}

}
