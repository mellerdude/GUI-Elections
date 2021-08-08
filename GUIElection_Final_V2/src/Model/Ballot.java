package Model;

import java.util.ArrayList;
import java.util.List;

public class Ballot<T extends Citizen> {
	
	public static final int ID_NUMBER = 1;
	private static int idCount = ID_NUMBER;
	private int idBox;// number of box
	private String boxAdress;
	private String ballotType;
	private List<T> canVoteInBallot;
	private List<Integer> votersForEachParty = new ArrayList<Integer>(); // list of votes for each party
	private double voterPercentage; // citizen who vote from the rest of citizen in ballot
	private int voteCount; // count the total votes

	public Ballot() {
		boxAdress = "emptyBox";
	}

	public Ballot(String address,T bType) {
		idBox = idCount++;
		boxAdress = address;
		initCounters();
		//this.ballotType = bType;
		canVoteInBallot = new ArrayList<T>();
		ballotType = bType.getClass().getSimpleName();
	}

	//reset data for the next election
	public void initBallotVoting(int size) {
		if (votersForEachParty.size() > 0)
			votersForEachParty.clear();
		for (int i = 0; i < size; i++) {
			votersForEachParty.add(0);
		}
		initCounters();
	}

	public void initCounters() {
		voterPercentage = 0;
		voteCount = 0;
	}

	public boolean addVote(int numberParty) {
		voteCount++;
		votersForEachParty.set(numberParty, (votersForEachParty.get(numberParty)) + 1);
		return true;
	}

	
	public void addCitizen(T newCitizen) {
		if (newCitizen instanceof Citizen) {
			canVoteInBallot.add(newCitizen);
		}		
	}


	public static int getIdNumber() {
		return ID_NUMBER;
	}

	public void showCitizen() {
		for (int i = 0; i < canVoteInBallot.size(); i++) {
			if (canVoteInBallot.get(i) != null)
				System.out.println(canVoteInBallot.get(i).toString());
		}
	}

	//calculate the voter percentage in each ballot
	public boolean setVoterPercentage() {
		double tempVCount = voteCount;
		double tempcanVote = canVoteInBallot.size();
		voterPercentage = ((tempVCount / tempcanVote) * 100);
		voterPercentage = (double) Math.round(voterPercentage * 100) / 100;
		return true;
	}

	public double getVoterPercentage() {
		return voterPercentage;
	}

	public int getIdBox() {
		return idBox;
	}

	public String getBoxAdress() {
		return boxAdress;
	}

	public List<T> getCanVoteInBallot() {
		return canVoteInBallot;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public List<Integer> getVotersForEachParty() {
		return votersForEachParty;
	}

	public String getBallotType() {
		return ballotType;
	}

	public String toString() {
		return "Ballot number " + idBox + " located at " + boxAdress + " has " + canVoteInBallot.size() + " "
				+ getBallotType() + " voting";
	}

	public boolean equals(Object other) {
		if (other instanceof Ballot<?> && this.idBox == ((Ballot<?>) other).idBox
				&& this.boxAdress.equals(((Ballot<?>) other).boxAdress))
			return true;
		return false;
	}
}