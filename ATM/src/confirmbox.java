import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class confirmbox {
	static boolean answer;

	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Button yes = new Button("Yes");
		Button no = new Button("NO");
		yes.setOnAction(e -> {
			answer = true;
			window.close();
		});
		no.setOnAction(e -> {
			answer = false;
			window.close();
		});
		Label label = new Label(message);
		GridPane layout = new GridPane();
		layout.setVgap(30);
		layout.setHgap(30);
		layout.add(label, 0, 0);
		layout.add(yes, 0, 1);
		layout.add(no, 1, 1);
		layout.setMinWidth(350);
		window.setTitle(title);
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("flatred.css");
		window.setScene(scene);
		window.showAndWait();
		return answer;

	}
}
