import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class alertbox {
	public static void display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		Button close = new Button("Ok");
		close.setOnAction(e -> window.close());
		VBox layout = new VBox();
		layout.getChildren().addAll(label, close);
		layout.setAlignment(Pos.CENTER);
layout.setMinWidth(350);
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("flatred.css");
		window.setScene(scene);

		window.showAndWait();
	}

	public void close() {

	}
}
