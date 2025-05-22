package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXMLController {
	/*
	 * Die Methoden	
	*/
		
		//die Methode setzt die Bühne auf den übergebenen Wert
		public void setMeineStage(Stage meineStage) {
			this.meineBuehne = meineStage;
		}
		
		//Methode zum Beenden
		@FXML protected void beendenKlick(ActionEvent event) {
			Platform.exit();
		}
		
		//Methode für die Software-Info
		@FXML protected void infoKlick(ActionEvent event) {
			Alert info = new Alert(AlertType.INFORMATION, "Von Martin Tastler");
			info.setHeaderText("Zahlenumrechner Version 1.0");
			info.show();
		}
		
		
		//Hilfsmethode wandelt römisches Zeichen in arabisches um
		//benötigt einen Buchstaben als Parameter
		public int charZuWert(char c) {
			int wert = 0;
			
			//eingelesene Buchstaben auswerten und einem Wert zuweisen
			switch (c) {
		    	case 'I': wert = 1; 
		    		break;
		        case 'V': wert = 5; 
		        	break;
		        case 'X': wert = 10; 
		        	break;
		        case 'L': wert = 50; 
		        	break;
		        case 'C': wert = 100; 
		        	break;
		        case 'D': wert = 500; 
		        	break;
		        case 'M': wert = 1000; 
		        	break;
		        //für ungültiges Zeichen
		        default: return 0;
		    }
		    return wert;
		}
		
		
		//Methode zum Umwandeln
		@FXML protected void umwandelnRom(ActionEvent event) {
			
			//einer Stringvariabel wird der Eingabewert zugewiesen per getText()
			String rom = eingabeRom.getText();
			
			//Ein neues char-Array wird mit dem Inhalt aus der Stringvariable gefüllt.
			//Ein Array ist hier notwendig um mehrere eingegebene Zeichen einzeln auswerten zu können.
			char[] inhaltRom = rom.toCharArray();
			
			//Startvariable für das Ergebnis
			int summe = 0;
			
			//per For-Schleife wird das char-Array durchlaufen
			for (int i = 0; i < inhaltRom.length; i++) {

				/*
				 * Die Hilfsmethode "charZuWert" wird nun an der Stelle im Char-Array 
				 * angewendet wo sich die Schleifenvariable i befindet
				 * 
				 * Den Rückgabewert der Hilfsmethode wird einer int-Variable zugewiesen
				*/
				int aktuellerWert = charZuWert(inhaltRom[i]);
			
				//Fehlerhafte Eingabe mitteilen, falls "aktueller Wert" gleich 0 ist,
				//aufgrund des Rückgabewerts der Hilfsmethode
				if (aktuellerWert == 0) {
					ausgabeWert.setText("Ungültiges Zeichen verwendet: " + inhaltRom[i]);
					//abbrechen
					return;
				}
				
				//ein 2. Wert für die Berechnung
				int naechsterWert = 0;
					
				//überprüfen ob es noch einen nachfolgende Wert nach i gibt,
				//sonst würde über das Array-Ende hinaus gelesen werden 
				if (i + 1 < inhaltRom.length) {
					
					//der nächste Wert ergibt sich nun wieder durch die Hilfsmethode. 
					//Diese wird auf das Char-Array angewendet an der Stelle i + 1 -> also nachfolgendes Element
					naechsterWert = charZuWert(inhaltRom[i+1]);
				}
					
				/*
				 * Jetzt kommt der Vergleich der 2 Werte. Ist die 1. römische Ziffer kleiner als die 2.
				 * so wird diese von der ursprünglichen Summe = 0 abgezogen. Es ergibt sich also ein negativer Wert.
				 * Da es aber noch einen 2. Wert gibt erfolgt nun die Verrechnung der beiden. 
				 * Bsp: I -> wäre hier -1 , X = 10 , ergibt zusammen 9.
				 * 
				 * Grundlage ist die Berechnungsweise der römischen Zahlen:
				 * 
				 * Wenn ein kleinerer Wert vor einem größeren steht, dann wird subtrahiert.
				*/ 
				if (aktuellerWert < naechsterWert) {
					summe -= aktuellerWert;
				} else {
					summe += aktuellerWert;
				}
	
			}
			//Ausgabe der Summe in das entsprechende Label
			ausgabeWert.setText(String.valueOf(summe));
		}
		
		//Hilfsmethode für das Umwandeln von Arabischen Zahlen in Römische
		//Die Methode benötigt eine Integer Zahl als Parameter
		public String wertZuChar(int zahl) {
			
			//Anlegen von 2 Arrays
			//dies ist notwendig um alle Zuweisungen von Ziffern und Zahlen zu hinterlegen
			//dank des festen römischen Systems ist dies übersichtlich möglich
			int[] werte = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};	
			String[] roemisch = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		
			//Startvariabel: ein leerer String
			String ergebnis = "";
			
			//eine For-Schleife durchläuft das Werte-Array
			for (int i = 0; i < werte.length; i++) {
				
				/*
				 * Solange die eingegebene Zahl größer oder gleich dem Schleifenindex i an einer Stelle
				 * im Werte-Array ist, wird der entsprechende Buchstabe an der selben Indexstelle im 
				 * roemisch-Array zum Ergebnis hinzugefügt
				 *
				 * Von der eingegebenen Zahl wird anschließend der Wert wieder abgezogen, der soeben als Bedingung
				 * in der While-Schleife aufgeführt wurde.
				*/
				while (zahl >= werte[i]) {
					ergebnis += roemisch[i];
					zahl -= werte[i];
				}
			}
		//das Ergebnis zurück geben
		return ergebnis;
		}
		
		//Die Methode zum Umwandeln von Wert zu Character
		@FXML protected void umwandelnWert(ActionEvent event) {
			
			//Eingabewert in eine ganze Zahl umwandeln
			int zahl = Integer.parseInt(eingabeWert.getText());
			
			//String-Variable ergibt sich aus der integer-Zahl umgewandelt mit der Hilfsmethode in einen Character
			String rom = wertZuChar(zahl);
			
			//Ausgabe der römischen Ziffern
			ausgabeRom.setText(rom);
		}
}
