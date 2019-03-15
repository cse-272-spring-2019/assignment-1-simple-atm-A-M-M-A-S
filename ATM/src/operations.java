import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class operations {
	static int i = 0;
	static Account save;

	public static Account display(Account account) {
		save = account;
		Button Withdraw, deposit, BalanceInquiry, previous, next;
		TextField Withdrawl, depositl;
		Label hstry, balance;
		Stage window = new Stage();
		Scene scene1;
		GridPane layout = new GridPane();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("What do you Want to do?");
		hstry = new Label(account.getHistory(i));
		Withdraw = new Button("Withdraw");
		deposit = new Button("deposit");
		BalanceInquiry = new Button("balance inqury");
		balance = new Label();
		BalanceInquiry.setOnAction(e -> balance.setText(String.format("%.2f", account.getBalance())));
		previous = new Button("previous");
		next = new Button("next");
		Withdrawl = new TextField();
		Withdrawl.setPromptText("enter amount");
		Withdrawl.setOnKeyTyped(e -> {
			if (!("0123456789.".contains(e.getCharacter()))) {
				e.consume();
			}
		});
		depositl = new TextField();
		depositl.setPromptText("enter amount");
		depositl.setOnKeyTyped(e -> {
			if (!("0123456789.".contains(e.getCharacter()))) {
				e.consume();
			}
		});
		Withdraw.setOnAction(e -> {
			if (Withdrawl.getText().length() != 0) {
				if (account.setBalance(-Double.parseDouble(Withdrawl.getText())) == -1) {
					alertbox.display("Insuffecient funds", "your don't have enough balance in your account");
				}
			} else {
				e.consume();
			}
		});
		deposit.setOnAction(e -> {
			if (depositl.getText().length() != 0) {
				account.setBalance(Double.parseDouble(depositl.getText()));
			} else {
				e.consume();
			}

		});
		next = new Button("next");
		previous = new Button("previous");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (account.getHistory(i).equals("  ")) {
					hstry.setText("Nothing more to show");
					event.consume();
				}

				else if (i == 4)
					event.consume();
				else
					hstry.setText(account.getHistory(i++));
			}

		});
		previous.setMinSize(150, 150);
		next.setMinSize(150, 150);
		BalanceInquiry.setMinSize(150, 150);
		Withdraw.setMinSize(150, 150);
		deposit.setMinSize(150, 150);
		Withdrawl.setMaxWidth(150);
		depositl.setMaxWidth(150);
		balance.setMaxWidth(150);
		balance.setMinWidth(150);
		hstry.setMinWidth(300);
		layout.setVgap(8);
		layout.setHgap(10);
		layout.setMinSize(1, 1);
		layout.add(Withdraw, 0, 0);
		layout.add(Withdrawl, 0, 1);
		layout.add(deposit, 1, 0);
		layout.add(depositl, 1, 1);
		layout.add(BalanceInquiry, 2, 0);
		layout.add(balance, 2, 1);
		layout.add(next, 3, 0);
		layout.add(hstry, 3, 1, 3, 2);
		layout.add(previous, 4, 0);
		layout.setPadding(new Insets(10, 10, 10, 10));

		previous.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (i == 0)
					event.consume();
				else
					hstry.setText(account.getHistory(--i));
			}

		});
		layout.backgroundProperty();
		scene1 = new Scene(layout);
		scene1.getStylesheets().add("flatred.css");
		window.setScene(scene1);
		window.setOnCloseRequest(e -> {
			if (confirmbox.display("save", "do you want to save"))
				save = account;

		});
		window.showAndWait();
		return save;

	}

}
