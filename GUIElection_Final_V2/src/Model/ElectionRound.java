package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Model.Ballot;
import Model.Candidate;
import Model.Citizen;
import Model.Party;
import Model.SickCandidate;
import Model.SickCitizen;
import Model.SickSoldier;
import Model.Soldier;
import javafx.scene.Group;

public class ElectionRound {
	private int voteMonth;
	private int voteYear;
	private boolean voteTookPlace;
	private Party winParty;
	private int winIndex;
	private int numberOfVoters = 0;
	// private String currentlyToggled;
	// each List contains one type of Ballot by the Citizen's type
	private List<Ballot<Citizen>> CitizenBallots = new ArrayList<Ballot<Citizen>>();
	private List<Ballot<SickCitizen>> SickCitizenBallots = new ArrayList<Ballot<SickCitizen>>();
	private List<Ballot<Soldier>> SoldierBallots = new ArrayList<Ballot<Soldier>>();
	private List<Ballot<SickSoldier>> SickSoldierBallots = new ArrayList<Ballot<SickSoldier>>();
	private List<Ballot<Candidate>> CandidateBallots = new ArrayList<Ballot<Candidate>>();
	private List<Ballot<SickCandidate>> SickCandidateBallots = new ArrayList<Ballot<SickCandidate>>();
	// List that contains all the Ballot, created for much easier way to perform
	// election
	private List<Ballot<?>> tempListOfBallots = new ArrayList<Ballot<?>>();

	private List<Party> candidateParties = new ArrayList<>();
	private List<Citizen> votersRegistry = new ArrayList<>();
	private List<Integer> totalVoteForParty = new ArrayList<>();

	// private Set<Citizen> allCitizens = new Set<Citizen>();
	public ElectionRound() {
		this(1, 2020);
	}

	public ElectionRound(int month, int year) {
		setVoteMonth(month);
		setVoteYear(year);
		setWinIndex(0);
	}

	public boolean addCitizen(Citizen newCitizen) {
		votersRegistry.add((Citizen) newCitizen);
		int index;
		// add to the right Ballot according to Citizen's type
		// if Soldier
		if (newCitizen.getClass() == Soldier.class) {
			index = SoldierBallots.indexOf(newCitizen.getCitizenBallot());
			SoldierBallots.get(index).addCitizen((Soldier) newCitizen);
			// if Sick Soldier
		} else if (newCitizen.getClass() == SickSoldier.class) {
			index = SickSoldierBallots.indexOf(newCitizen.getCitizenBallot());
			SickSoldierBallots.get(index).addCitizen((SickSoldier) newCitizen);
			// if Citizen
		} else if (newCitizen.getClass() == Citizen.class) {
			index = CitizenBallots.indexOf(newCitizen.getCitizenBallot());
			CitizenBallots.get(index).addCitizen((Citizen) newCitizen);
			// if Candidate
		} else if (newCitizen.getClass() == Candidate.class) {
			index = CandidateBallots.indexOf(newCitizen.getCitizenBallot());
			CandidateBallots.get(index).addCitizen((Candidate) newCitizen);
			// if Sick Citizen
		} else if ((newCitizen.getClass() == SickCitizen.class)) {
			index = SickCitizenBallots.indexOf(newCitizen.getCitizenBallot());
			SickCitizenBallots.get(index).addCitizen((SickCitizen) newCitizen);
			// if Sick Candidate
		} else if (newCitizen.getClass() == SickCandidate.class) {
			index = SickCandidateBallots.indexOf(newCitizen.getCitizenBallot());
			SickCandidateBallots.get(index).addCitizen((SickCandidate) newCitizen);
		} else {
			return false;
		}
		return true;

	}

	public boolean addParty(Object newParty) {
		if (newParty instanceof Party) {
			candidateParties.add((Party) newParty);
			return true;
		}
		return false;
	}

	// find the wanted ballot by type
	public String showBallot(String type) {
		StringBuffer sb = new StringBuffer();
		int index = 1;
		boolean isAll = false;
		tempListOfBallots.clear();
		if (type.equalsIgnoreCase("Regular")) {
			tempListOfBallots.addAll(CitizenBallots);
		} else if (type.equalsIgnoreCase("Quarantined")) {
			tempListOfBallots.addAll(SickCitizenBallots);
		} else if (type.equalsIgnoreCase("Candidate")) {
			tempListOfBallots.addAll(CandidateBallots);
		} else if (type.equalsIgnoreCase("Candidate Quarantined")) {
			tempListOfBallots.addAll(SickCandidateBallots);
		} else if (type.equalsIgnoreCase("Military")) {
			tempListOfBallots.addAll(SoldierBallots);
		} else if (type.equalsIgnoreCase("Military Quarantined")) {
			tempListOfBallots.addAll(SickSoldierBallots);
		} else {
			setAllBallotsInList();
			isAll = true;
		}

		for (int i = 0; i < tempListOfBallots.size(); i++) {
			if (isAll)
				sb.append(i + 1 + "-" + tempListOfBallots.get(i).toString() + "\n");
			else {
				sb.append(index + "-" + tempListOfBallots.get(i).toString() + "\n");
				index++;
			}
		}
		return sb.toString();
	}

	// show all the citizen
	public String showCitizen() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < votersRegistry.size(); i++) {
			sb.append(i + 1 + "-" + votersRegistry.get(i).toString() + "\n");
		}
		return sb.toString();
	}

	// show all the partys
	public String showParty() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < candidateParties.size(); i++) {
			sb.append(i + 1 + "-" + candidateParties.get(i).toString() + "\n");
		}
		return sb.toString();
	}

	// reset the votes for the next election
	public void initVoters() {
		totalVoteForParty.clear();
		for (int i = 0; i < candidateParties.size(); i++) {
			totalVoteForParty.add(0);
		}
		for (int i = 0; i < tempListOfBallots.size(); i++) {
			tempListOfBallots.get(i).initBallotVoting(candidateParties.size());
		}
	}

	// check the results after the election took place
	public boolean checkResult() {
		Ballot<?> tempB;
		Party tempP;
		int calc = 0;
		Party winParty = candidateParties.get(0);
		int winInd = 0;
		boolean isNotTie = true;
		// count votes from each Ballot
		for (int i = 0; i < tempListOfBallots.size(); i++) {
			tempB = tempListOfBallots.get(i);
			// update the vote for each party
			for (int j = 0; j < candidateParties.size(); j++) {
				calc = totalVoteForParty.get(j) + (Integer) tempB.getVotersForEachParty().get(j);
				totalVoteForParty.set(j, calc);
			}
		}
		// check the winner
		for (int i = 1; i < candidateParties.size(); i++) {
			tempP = candidateParties.get(i);
			// if not equal check if i>win
			if (totalVoteForParty.get(i) != totalVoteForParty.get(winInd)) {
				if (totalVoteForParty.get(i) > totalVoteForParty.get(winInd)) {
					winParty = tempP;
					winInd = i;
					// return true, there is a final answer to the election
					isNotTie = true;
				}
			} else // if equal return false, there is a tie
				isNotTie = false;
		}
		setWinParty(winParty);
		setWinIndex(winInd);
		return isNotTie;
	}

	public int getVoteMonth() {
		return voteMonth;
	}

	public int getVoteYear() {
		return voteYear;
	}

	public List<Ballot<Citizen>> getCitizenBallots() {
		return CitizenBallots;
	}

	public List<Ballot<SickCitizen>> getSickCitizenBallots() {
		return SickCitizenBallots;
	}

	public List<Ballot<Soldier>> getSoldierBallots() {
		return SoldierBallots;
	}

	public List<Ballot<SickSoldier>> getSickSoldierBallots() {
		return SickSoldierBallots;
	}

	public List<Ballot<Candidate>> getCandidateBallots() {
		return CandidateBallots;
	}

	public List<Ballot<SickCandidate>> getSickCandidateBallots() {
		return SickCandidateBallots;
	}

	// add all the lists of ballots to one list
	public void setAllBallotsInList() {
		tempListOfBallots.clear();
		tempListOfBallots.addAll(CitizenBallots);
		tempListOfBallots.addAll(SickCitizenBallots);
		tempListOfBallots.addAll(CandidateBallots);
		tempListOfBallots.addAll(SickCandidateBallots);
		tempListOfBallots.addAll(SoldierBallots);
		tempListOfBallots.addAll(SickSoldierBallots);
		sortList();
	}

	// sort the list according to the index of Ballot
	private void sortList() {
		Comparator<Ballot<?>> compareByIndex = new Comparator<Ballot<?>>() {

			@Override
			// return -1 if b1<b2, 1 if b1>b2, 0 if b1==b2
			public int compare(Ballot<?> b1, Ballot<?> b2) {
				if (b1.getIdBox() < b2.getIdBox())
					return -1;
				else if (b1.getIdBox() == b2.getIdBox())
					return 0;
				else
					return 1;
			}
		};
		Collections.sort(tempListOfBallots, compareByIndex);
	}

	public List<Ballot<?>> getAllBallotInList() {
		return tempListOfBallots;
	}

	public List<Party> getCandidateParties() {

		return candidateParties;
	}

	public List<Citizen> getVotersRegistry() {
		return votersRegistry;
	}

	public List<Integer> getTotalVoteForParty() {
		return totalVoteForParty;
	}

	public Party getWinParty() {
		return winParty;
	}

	public int getWinIndex() {
		return winIndex;
	}

	public boolean getVoteTookPlace() {
		return voteTookPlace;
	}

	public void setVoteTookPlace(boolean hasVote) {
		voteTookPlace = hasVote;
	}

	private boolean setVoteYear(int y) {
		if (y > 2021 || y < 1950)
			this.voteYear = 2020;
		else
			this.voteYear = y;
		return true;
	}

	private boolean setVoteMonth(int m) {
		if (m > 12 || m < 1)
			this.voteMonth = 1;
		else
			this.voteMonth = m;
		return true;
	}

	public boolean setWinParty(Party party) {
		this.winParty = party;
		return true;
	}

	private boolean setWinIndex(int index) {
		winIndex = index;
		return true;
	}

	public String toString() {
		return "Election " + voteMonth + "/" + voteYear + ": \n" + "The winning party is : " + winParty.getName()
				+ " with " + totalVoteForParty.get(winIndex) + " votes!!!";
	}

	public boolean equals(ElectionRound other) {
		if (this.voteMonth == other.voteMonth && this.voteYear == other.voteYear
				&& this.winParty.equals(other.winParty))
			return true;
		return false;
	}

	public void draw(Group root) {

	}

	public void updateNumberOfVoters() {
		numberOfVoters++;

	}

	public int getNumberOfVoters() {
		return numberOfVoters;
	}

	public void setNumberOfVoters() {
		numberOfVoters = 0;
	}

	public void updateVote(int partySelected, int ballotNum) {
		getAllBallotInList().get(ballotNum).addVote(partySelected);
	}
}