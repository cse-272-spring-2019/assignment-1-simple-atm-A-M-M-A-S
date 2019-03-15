import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		String fl = "src/data.csv";
		List<Account> accounts = Account.ReadAccount(fl);// 656561 is a password ////open data.csv to find the rest of
															// the data
		int i = Login.display((ArrayList<Account>) accounts);// please note all data are arbitrary, edit the csv file
																// for any specefic testcase
		if (i != -1) {
			accounts.set(i, operations.display(accounts.get(i)));
			Account.save(fl, (ArrayList<Account>) accounts);
		}

	}

}
