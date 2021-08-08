package Model;

public class Citizen {
	private String name;
	private int id;
	private int yearOfBirth;
	private Ballot<?> citizenBallot;
	private boolean inQuarantine;
	//private boolean isSoldiser;
	//private boolean hasWeapon;

	public Citizen() {
		this("name", 123456789, 1996, false, null);
	}

	public Citizen(String n, int ident, int yob, boolean inQuar, Ballot<?> cBallot) {
		setName(n);
		id = ident;
		yearOfBirth = yob;
		setQuar(inQuar);
		setBallot(cBallot);
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}


	public boolean setQuar(boolean isQua) {
		inQuarantine = isQua;
		return true;
	}

	public boolean setBallot(Ballot<?> sentBallot) {
		if (sentBallot == null)
			return false;
		citizenBallot = sentBallot;
		return true;
	}

	public boolean isInQuarantine() {
		return inQuarantine;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public Ballot<?> getCitizenBallot() {
		return citizenBallot;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(
				"\n" + name + ", " + id + ", born in " + yearOfBirth + "\nis in Quarantine? " + inQuarantine
						+ "\nCan vote in " + citizenBallot.getBallotType() + " Ballot number " + citizenBallot.getIdBox() + "\n");
		return sb.toString();
	}

	public boolean equals(Object other) {
		if (other instanceof Citizen && this.name.equals(((Citizen) other).getName())
				&& this.id == ((Citizen) other).getId() && this.yearOfBirth == ((Citizen) other).getYearOfBirth()
				&& this.inQuarantine == ((Citizen) other).isInQuarantine()
				&& this.citizenBallot.equals(((Citizen) other).getCitizenBallot()))
			return true;

		return false;
	}
}
