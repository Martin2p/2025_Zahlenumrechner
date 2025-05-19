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
		//Methode zum Umwandeln (in Arbeit)
		@FXML protected void umwandeln(ActionEvent event) {
			
			String inhalt = eingabe.getText();
			
			System.out.println(inhalt);
			
			for (int i = 0; i < inhalt.length(); i++) {
				
				switch (inhalt) {
					case "I": ausgabe.setText("1");
					break;
					case "V": ausgabe.setText("5");
					break;
					case "X": ausgabe.setText("10");
					break;
					case "L": ausgabe.setText("50");
					break;
					case "C": ausgabe.setText("100");
					break;
					case "D": ausgabe.setText("500");
					break;
					case "M": ausgabe.setText("1000");
					break;
				}
			}
}
