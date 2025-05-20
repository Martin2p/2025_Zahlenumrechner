package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FXMLController {
	
	/*
	 * Deklarationen
	*/
	
	@FXML private Stage meineBuehne;
	
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
	
		//Hilfsmethode für Umwandeln Char zu Wert
		public int charZuWert(char c) {
			int wert = 0;
			
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
			
			String rom = eingabeRom.getText();
			
			char[] inhaltRom = rom.toCharArray();
			
			int summe = 0;
			
			for (int i = 0; i < inhaltRom.length; i++) {

				int aktuellerWert = charZuWert(inhaltRom[i]);
			
				//Fehlerhafte Eingabe mitteilen
				if (aktuellerWert == 0) {
					ausgabeWert.setText("Ungültiges Zeichen verwendet: " + inhaltRom[i]);
					//abbrechen
					return;
				}
				
				int naechsterWert = 0;
					
					
				if (i + 1 < inhaltRom.length) {
						
					naechsterWert = charZuWert(inhaltRom[i+1]);
				}
					
				if (aktuellerWert < naechsterWert) {
					summe -= aktuellerWert;
				} else {
					summe += aktuellerWert;
				}
			}
			ausgabeWert.setText(String.valueOf(summe));
		}
		
		//Hilfsmethode für das Umwandeln von Arabischen Zahlen in Römische
		public String wertZuChar(int zahl) {
			
			//Anlegen von 2 Arrays
			int[] werte = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};	
			String[] roemisch = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		
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
