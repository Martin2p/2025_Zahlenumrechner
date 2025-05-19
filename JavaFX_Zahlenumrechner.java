package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFX_Zahlenumrechner extends Application {

	@Override
	public void start(Stage meineBuehne) throws Exception {
			
		//eine Instanz von FXMLLoader erzeugen
		FXMLLoader zahlenUmrechner = new FXMLLoader(getClass().getResource("fxml_Zahlenumrechner.fxml"));
			
		//die Datei laden
		Parent root = zahlenUmrechner.load();
			
		//Kontroller beschaffen
		FXMLController trController = zahlenUmrechner.getController();
			
		//und die Bühne übergeben
		trController.setMeineStage(meineBuehne);
			
		//Scene erstellen
		Scene meineScene = new Scene(root, 500, 700);
			
		//den Titel über stage setzen
		meineBuehne.setTitle("Zahlenumrechner von Martin Tastler");
		//die Szene setzen
		meineBuehne.setScene(meineScene);
		//Fenstergröße fix setzen
		meineBuehne.setResizable(false);
		//anzeigen
		meineBuehne.show();
	}
		
	public static void main(String[] args) {
		//der Start
		launch(args);
	}
}
