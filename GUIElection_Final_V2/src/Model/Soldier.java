package Model;

public class Soldier extends Citizen {
	private boolean hasWeapon;
	
	public Soldier() {
		
	}

	public Soldier(String n, int ident, int yob, boolean inQuar, Ballot<?> cBallot, boolean hasWeapon) {
		super(n, ident, yob, inQuar, cBallot);
		this.hasWeapon = hasWeapon;
	}

	public String toString() {
		return super.toString() + "Is Soldier. Has weapon? " + hasWeapon;
	}
}
