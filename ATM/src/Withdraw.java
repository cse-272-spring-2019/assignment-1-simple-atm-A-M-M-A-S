import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Withdraw {
	static double rtrn;

	public static double display(String title, Account account) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		TextField textField = new TextField();
		textField.setOnKeyTyped(e -> {
			if (!("0123456789.".contains(e.getCharacter()))) {
				e.consume();
			}
		});

		textField.setPromptText("enter amount");
		Button Withdraw = new Button("Ok");
		Withdraw.setOnAction(e -> {
			if (textField.getText().length() != 0) {
				if (account.setBalance(rtrn = -Double.parseDouble(textField.getText())) == -1) {
					Alertbox.display("Insuffecient funds", "your don't have enough balance in your account");
				} else {
					Alertbox.display("success", "operation done succesfully");
					window.close();
				}
			}
		});
		VBox layout = new VBox();
		layout.getChildren().addAll(textField, Withdraw);
		layout.setAlignment(Pos.CENTER);
		layout.setMinWidth(350);
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("flatred.css");
		window.setScene(scene);

		window.showAndWait();
		return rtrn;
	}

}
