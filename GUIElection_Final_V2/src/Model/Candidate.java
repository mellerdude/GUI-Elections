package Model;

public class Candidate extends Citizen {
	protected int primariesRank;
	protected Party candidateParty;

	public Candidate() {

	}

	public Candidate(String n, int identification, int yob, boolean inQuarantine, Ballot<?> cdBallot, int pRank,
			Party cParty) {
		super(n, identification, yob, inQuarantine, cdBallot);
		setParty(cParty);
		setRank(pRank);
	}

	public boolean setRank(int rank) {
		for (int i = 0; i < candidateParty.getAllCandidateList().size(); i++) {
			if(rank == candidateParty.getAllCandidateList().get(i).getPrimariesRank()) {
				primariesRank = candidateParty.getAllCandidateList().size() + 1;
				return true;
			}
		}
		primariesRank = rank;
		return true;
	}

	public boolean setParty(Party cParty) {
		if (cParty == null)
			return false;
		candidateParty = cParty;
		return true;
	}

	public int getPrimariesRank() {
		return primariesRank;
	}

	public Party getCandidateParty() {
		return candidateParty;
	}

	public String toString() {
		return super.toString() + "A candidate for the party " + candidateParty.getName()
		+ "\nHis rank in the party is " + primariesRank + "\n";
	}

	public boolean equals(Object other) {
		if (other instanceof Citizen && super.equals(other)
				&& this.candidateParty.equals(((Candidate) other).getCandidateParty())
				&& this.primariesRank == ((Candidate) other).getPrimariesRank()) {
		}
		return false;
	}
}