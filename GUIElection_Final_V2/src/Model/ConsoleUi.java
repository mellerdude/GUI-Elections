package Model;

import java.util.Scanner;

public class ConsoleUi implements Printable {

	@Override
	public void print(String str) {
		System.out.println(str);
	}

	@Override
	public String getString(Scanner s) {
		return s.next();
	}

	@Override
	public int getInt(Scanner s) {
		return s.nextInt();
	}

}
