package Model;

public class SickSoldier extends Soldier {
	
	public SickSoldier() {
		super();
	}

	public SickSoldier(String n, int ident, int yob, boolean inQuar, Ballot<?> cBallot, boolean hasWeapon) {
		super(n, ident, yob, inQuar, cBallot, hasWeapon);
	}
}
