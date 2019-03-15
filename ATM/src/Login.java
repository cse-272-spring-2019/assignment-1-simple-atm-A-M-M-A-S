import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Login {
	static int n = -1;

	public static int display(ArrayList<Account> data) {
		Button login;
		TextField id;
		Stage window = new Stage();
		Scene scene1;
		Label wrong = new Label();
		GridPane layout1 = new GridPane();
		window.setTitle("Login");
		window.initModality(Modality.APPLICATION_MODAL);
		id = new TextField();
		id.setPromptText("Please enter your pin");
		id.setOnKeyTyped(e -> {
			if ((!("0123456789".contains(e.getCharacter())) || id.getText().length() > 5)) {
				e.consume();
			}
		});
		GridPane.setConstraints(id, 1, 0);
		login = new Button("Login");
		login.setOnAction(e -> {
			if (id.getText().length() == 0) {
				e.consume();
			} else {
				for (int i = 0; i < data.size(); i++)

					if (data.get(i).getId() == Integer.parseInt(id.getText())) {
						found(i);
						window.close();
					}
				if (n == -1 || id.getText().equals("")) {
					wrong.setText("Wrong Pin");
				}
			}
		});
		GridPane.setConstraints(login, 1, 1);
		GridPane.setConstraints(wrong, 1, 2);
		layout1.getChildren().addAll(id, login, wrong);
		scene1 = new Scene(layout1, 300, 200);
		scene1.getStylesheets().add("flatred.css");

		window.setScene(scene1);
		window.showAndWait();
		return n;

	}

	private static void found(int i) {
		n = i;
	}

}