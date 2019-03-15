import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
	private int Id;
	private double balance;
	List<String> history = new ArrayList<String>(5);

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Account(int id, double balance) {
		Id = id;
		this.balance = balance;
	}

	public Account(int id) {
		Id = id;
		this.balance = 0;
	}

	public Account(String[] props) {
		Id = Integer.parseInt(props[0]);
		balance = Double.parseDouble(props[1]);
		history.add(props[2]);
		history.add(props[3]);
		history.add(props[4]);
		history.add(props[5]);
		history.add(props[6]);
	}

	public Account(String pass, int id, double bal) {
		Id = id;
		balance = bal;
	}

	public double getBalance() {
		return balance;
	}

	public int setBalance(double diff) {
		if (diff < -this.balance) {
			return -1;
		}
		Date date = new Date();
		boolean flg = false;
		for (int i = 0; i < 5; i++) {
			if (history.get(i).equals("  ")) {
				history.set(i, dateFormat.format(date) + " " + ((diff > 0) ? " deposit " : " Withdraw ")
						+ Math.abs(diff)  );
				flg = true;
				break;
			}
		}
		if (!flg) {
			history.add(dateFormat.format(date) + " " + ((diff > 0) ? " deposit " : " Withdraw ") + Math.abs(diff)
					 );
		}
		if (history.size() > 5) {
			history.remove(0);
		}
		this.balance = this.balance + diff;
		return 1;
	}

	public String getHistory(int i) {
		return history.get(i);
	}
	@Override
	public String toString() {
		return String.valueOf(Id)+"," +String.valueOf(balance)+"," + getHistory(0)+"," +
				getHistory(1)+"," +getHistory(2)+"," +getHistory(3)+"," +getHistory(4);
	}


	public static List<Account> ReadAccount(String filename) {
		List<Account> accounts = new ArrayList<>();
		Path fpath = Paths.get(filename);
		try (BufferedReader buffer = Files.newBufferedReader(fpath, StandardCharsets.US_ASCII)) {
			String nxline = buffer.readLine();
			while (nxline != null) {
				String[] props = nxline.split(",");
				Account account = new Account(props);
				accounts.add(account);
				nxline = buffer.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		return accounts;

	}
	public static void save(String filename, ArrayList<Account> accounts)  {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename,false);
		} catch (IOException e) {
			// TODO Auto-generated catch block\
			System.out.println("error");
		}
		PrintWriter pw=new PrintWriter(fw);
		for (int i=0;i<accounts.size();i++) {
			pw.println(accounts.get(i).toString());
		}
		
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error2");
		}
		
    }
}
