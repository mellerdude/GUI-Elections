package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Party {

	public enum wing {
		left, center, right
	};

	private String name;
	private wing partyWing;
	private int creationDay;
	private int creationMonth;
	private int creationYear;
	private List<Candidate> candidateList = new ArrayList<Candidate>();
	private List<SickCandidate> sickCandidateList = new ArrayList<SickCandidate>();
	private List<Candidate> allCandidate = new ArrayList<Candidate>();

	public Party() {
		this("","left",1,1,2020);
	}
	public Party(String name, String Pwing, int cDay, int cMonth, int cYear) {
		this.name = name;
		partyWing = wing.valueOf(Pwing);
		setCreationDay(cDay);
		setCreationMonth(cMonth);
		setCreationYear(cYear);
	}
	
	//check if regular or sick candidate
	public boolean addCandidate(Candidate newCand) {
		if (newCand.getClass() == Candidate.class) {
			candidateList.add(newCand);
		}else if (newCand.getClass() == SickCandidate.class) {
			sickCandidateList.add((SickCandidate)newCand);
		}
		sortList();
		return true;
	}

	//sort the candidate according to the primaries rank
	private void sortList() {
		setAllCandidate();
		Comparator<Candidate> compareByRank = new Comparator<Candidate>() {

			// return -1 if o1<o2, 1 if o1>o2, 0 if o1==o2
			public int compare(Candidate o1, Candidate o2) {
				if (o1.getPrimariesRank() < o2.getPrimariesRank())
					return -1;
				else if (o1.getPrimariesRank() == o2.getPrimariesRank())
					return 0;
				else
					return 1;
			}
		};
		Collections.sort(allCandidate, compareByRank);
	}

	private boolean setCreationYear(int cYear) {
		if (cYear < 1920)
			creationYear = 1920;
		else
			creationYear = cYear;
		return true;

	}

	private boolean setCreationMonth(int cMonth) {
		if (cMonth < 1 || cMonth > 12)
			creationMonth = 1;
		else
			creationMonth = cMonth;
		return true;

	}

	private boolean setCreationDay(int cDay) {
		if (cDay < 1 || cDay > 31)
			creationDay = 1;
		else
			creationDay = cDay;
		return true;

	}

	public String getName() {
		return name;
	}
	
	//move all the candidate to one list
	public void setAllCandidate() {
		allCandidate.clear();
		allCandidate.addAll(candidateList);
		allCandidate.addAll(sickCandidateList);
	}

	public List<Candidate> getCandidateList() {
		return candidateList;
	}

	public List<SickCandidate> getSickCandidateList(){
		return sickCandidateList;	
	}
	
	public List<Candidate> getAllCandidateList(){
		return allCandidate;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name + ":\nWing: " + partyWing + "\nCreation date: " + creationDay + "/" + creationMonth + "/"
				+ creationYear + "\nThe candidates of the party are:\n");
		for (int i = 0; i < allCandidate.size(); i++) {
			sb.append(allCandidate.get(i).toString());
		}
		return sb.toString();
	}

	public boolean equals() {
		return true;
	}
}
