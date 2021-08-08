package Model;

public class SickCitizen extends Citizen {
	
	public SickCitizen() {
		
	}

	public SickCitizen(String n, int ident, int yob, boolean inQuar, Ballot<?> cBallot) {
		super(n, ident, yob, inQuar, cBallot);
	}
}
