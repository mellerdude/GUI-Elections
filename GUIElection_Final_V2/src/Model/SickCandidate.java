package Model;

public class SickCandidate extends Candidate {
	
	public SickCandidate() {
		super();
		
	}
	
	
	public SickCandidate(String n, int ident, int yob, boolean inQuar, Ballot<?> cBallot,int pRank,
			Party cParty) {
		super(n, ident, yob, inQuar, cBallot,pRank,cParty);
	}
	
}
