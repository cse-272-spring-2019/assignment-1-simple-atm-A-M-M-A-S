import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Menue {
	static int i = 0;
	static Account save;

	public static Account display(Account account) {
		save = account;
		Button witbtn, dpptn, BalanceInquiry, previous, next;
		Label hstry, balance;
		Stage window = new Stage();
		Scene scene1;
		GridPane layout = new GridPane();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("What do you Want to do?");
		hstry = new Label(account.getHistory(i));
		witbtn = new Button("withdrawn");
		dpptn = new Button("dposit");
		BalanceInquiry = new Button("balance inqury");
		balance = new Label();
		BalanceInquiry.setOnAction(e -> balance.setText(String.format("Your  current balance is: %.2f", account.getBalance())));
		previous = new Button("previous");
		next = new Button("next");
		witbtn.setOnAction(e -> {
			
			Withdraw.display("withdraw", account);
			hstry.setText(account.getHistory(i++));

		});
		dpptn.setOnAction(e -> {
			Deposit.display("withdraw", account);	
			hstry.setText(account.getHistory(i++));
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
		witbtn.setMinSize(150, 150);
		dpptn.setMinSize(150, 150);
		balance.setMinWidth(300);
		hstry.setMinWidth(300);
		layout.setVgap(8);
		layout.setHgap(10);
		layout.setMinSize(1, 1);
		layout.add(witbtn, 0, 0);
		layout.add(dpptn, 1, 0);
		layout.add(BalanceInquiry, 2, 0);
		layout.add(next, 3, 0);
		layout.add(hstry, 3, 1, 3, 2);
		layout.add(balance, 1, 1, 3, 2);
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
