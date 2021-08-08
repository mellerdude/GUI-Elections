package Model;

public class IllegalInput extends Exception {
	private String msg;
	
	public IllegalInput(String str) {
		msg = str;
	}
	
	public String getMessage() {
		return msg;
	}

}