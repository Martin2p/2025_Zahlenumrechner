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
	 * Deklarationen
	*/
	
	@FXML private Stage meineBuehne;
	@FXML private Label ausgabeWert;
	@FXML private Label ausgabeRom;
	
	@FXML private TextField eingabeRom;
	@FXML private TextField eingabeWert;
	
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
		
		//Hilfsmethode für Umwandeln Char zu Wert, sie benötigt einen Buchstaben als Parameter
		public int charZuWert(char c) {
			int wert = 0;
			
			//eingelesene Buchstaben auswerten und einem Wert zuweißen
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
			
			//einer Stringvariabel wird der Eingabewert zugewießen per getText()
			String rom = eingabeRom.getText();
			
			//ein neues Character-Array wird mit dem Inhalt aus der Stringvariabel gefüllt.
			//Ein Array ist hier notwendig um mehrere eingegebene Zeichen einzeln auswerten zu können.
			char[] inhaltRom = rom.toCharArray();
			
			//Startvariabel für das Ergebnis
			int summe = 0;
			
			//per For-Schleife wird das char-Array durchlaufen
			for (int i = 0; i < inhaltRom.length; i++) {

				/*
				 * Die Hilfsmethode "charZuWert" wird nun an der Stelle im Char-Array 
				 * angewendet wo die Schleifenvariabel i sich befindet
				 * 
				 * Den Rückgabewert der Hilfsmethode wird einer int-Variabel zugewießen
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
			
			
			
			for (int i = 0; i < werte.length; i++) {
				
				while (zahl >= werte[i]) {
					ergebnis += roemisch[i];
					zahl -= werte[i];
				}
			}
		return ergebnis;
		}
		
		@FXML protected void umwandelnWert(ActionEvent event) {
			
			int zahl = Integer.parseInt(eingabeWert.getText());
			String rom = wertZuChar(zahl);
			
			ausgabeRom.setText(rom);
		}
}
