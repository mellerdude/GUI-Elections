package Model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;

public class Model {
	private ElectionRound election2020;
	private static Set<Citizen> allCitizens = new Set<Citizen>();
	private String name;
	private int ID;
	private int year;
	private boolean inQur;
	//private boolean isSoldier;
	private boolean hasWeapon;
	private String ballotType;
	
	
	public Model() {
		election2020 = new ElectionRound(9,2020);
		addFirst(election2020);
	}

	public void draw(Group root) {
		//			ElectionRound.draw(root);
	}
	public static boolean addFirst(ElectionRound election2020) {
		Ballot<Citizen> bRegular = new Ballot<Citizen>("Haglima 7", new Citizen());
		Ballot<SickCitizen> bCorona = new Ballot<SickCitizen>("Haketer 3", new SickCitizen());
		Ballot<Candidate> bCandidate = new Ballot<Candidate>("Star Center", new Candidate());
		Ballot<SickCandidate> bCandidateCorona = new Ballot<SickCandidate>("The White House", new SickCandidate());
		Ballot<Soldier> bSoldier = new Ballot<Soldier>("Base 7", new Soldier());
		Ballot<SickSoldier> bSoldierCorona = new Ballot<SickSoldier>("Assuta Hospital", new SickSoldier());

		// adding 6 Ballot
		election2020.getCitizenBallots().add(bRegular);
		election2020.getSickCitizenBallots().add(bCorona);
		election2020.getCandidateBallots().add(bCandidate);
		election2020.getSickCandidateBallots().add(bCandidateCorona);
		election2020.getSoldierBallots().add(bSoldier);
		election2020.getSickSoldierBallots().add(bSoldierCorona);

		// adding 6 Citizen
		Citizen c1 = new Citizen("Moshe", 287462123, 1994, false, bRegular);
		election2020.addCitizen(c1);
		allCitizens.add(c1);
		SickCitizen c2 = new SickCitizen("Yehuda", 587462123, 1992, true, bCorona);
		election2020.addCitizen(c2);
		allCitizens.add(c2);
		Soldier c3 = new Soldier("Yoni", 123456789, 1999, false, bSoldier, true);
		election2020.addCitizen(c3);
		allCitizens.add(c3);
		Soldier c4 = new Soldier("Dan", 589475682, 2001, false, bSoldier, false);
		election2020.addCitizen(c4);
		allCitizens.add(c4);
		Citizen c5 = new Citizen("Assaf", 320254213, 1991, false, bRegular);
		election2020.addCitizen(c5);
		allCitizens.add(c5);
		SickSoldier c6 = new SickSoldier("Eldar", 215468957, 2001, true, bSoldierCorona, true);
		election2020.addCitizen(c6);
		allCitizens.add(c6);
		// adding citizen to ballot happens in election2020 when we add citizen to vote
		// list

		// adding 3 Party
		Party p1 = new Party("Licod", "right", 05, 12, 1973);
		election2020.addParty(p1);
		Party p2 = new Party("Havoda", "left", 01, 01, 2020);
		election2020.addParty(p2);
		Party p3 = new Party("Yesh-Atid", "center", 05, 12, 2010);
		election2020.addParty(p3);
		election2020.setWinParty(p1);

		// adding 2 candidate to each party
		Candidate cd1 = new Candidate("Benni", 123456789, 1970, false, bCandidate, 1, p1);
		SickCandidate cd2 = new SickCandidate("Israel", 365842789, 1980, true, bCandidateCorona, 3, p1);
		Candidate cd3 = new Candidate("Amir", 875421563, 1976, false, bCandidate, 1, p2);
		Candidate cd4 = new Candidate("Orly", 547821356, 1986, false, bCandidate, 2, p2);
		Candidate cd5 = new Candidate("Yair", 365478925, 1978, false, bCandidate, 1, p3);
		SickCandidate cd6 = new SickCandidate("Moshe", 658742359, 1974, true, bCandidateCorona, 2, p3);

		allCitizens.add(cd1);
		allCitizens.add(cd2);
		allCitizens.add(cd3);
		allCitizens.add(cd4);
		allCitizens.add(cd5);
		allCitizens.add(cd6);

		election2020.addCitizen(cd1);
		election2020.addCitizen(cd2);
		election2020.addCitizen(cd3);
		election2020.addCitizen(cd4);
		election2020.addCitizen(cd5);
		election2020.addCitizen(cd6);

		election2020.getCandidateParties().get(0).addCandidate(cd1);
		election2020.getCandidateParties().get(0).addCandidate(cd2);
		election2020.getCandidateParties().get(1).addCandidate(cd3);
		election2020.getCandidateParties().get(1).addCandidate(cd4);
		election2020.getCandidateParties().get(2).addCandidate(cd5);
		election2020.getCandidateParties().get(2).addCandidate(cd6);

		// add all Ballots to the same List
		election2020.setAllBallotsInList();
		return true;
	}
	
	public void updateBallot(String address,String type) {
		if(type == "Regular") {
			Ballot<Citizen> newBallot = new Ballot<Citizen>(address,new Citizen());
			election2020.getCitizenBallots().add(newBallot);
		}else if(type == "Military") {
			Ballot<Soldier> newBallot = new Ballot<Soldier>(address, new Soldier());
			election2020.getSoldierBallots().add(newBallot);
		}else if(type == "Quarantined") {
			Ballot<SickCitizen> newBallot = new Ballot<SickCitizen>(address, new SickCitizen());
			election2020.getSickCitizenBallots().add(newBallot);
		}else if(type == "Military Quarantined") {
			Ballot<SickSoldier> newBallot = new Ballot<SickSoldier>(address, new SickSoldier());
			election2020.getSickSoldierBallots().add(newBallot);
		}else if(type == "Candidate") {
			Ballot<Candidate> newBallot = new Ballot<Candidate>(address, new Candidate());
			election2020.getCandidateBallots().add(newBallot);
		}else {
			Ballot<SickCandidate> newBallot = new Ballot<SickCandidate>(address, new SickCandidate());
			election2020.getSickCandidateBallots().add(newBallot);
		}
	}
	
	public boolean updateCitizen(String ballotAddress) {
		Ballot<?> tempBallot;
		Citizen newCitizen ;
		if(ballotType == "Citizen") {
			tempBallot = getBallot("Regular", ballotAddress);
			newCitizen = new Citizen(name, ID, year, inQur, tempBallot);
		}else if(ballotType == "Sick Citizen"){
			tempBallot = getBallot("Corona", ballotAddress);
			newCitizen = new SickCitizen(name, ID, year, inQur, tempBallot);
		}else if(ballotType == "Soldier") {
			tempBallot = getBallot("Military", ballotAddress);
			newCitizen = new Soldier(name, ID, year, inQur, tempBallot,hasWeapon);
		}else {
			tempBallot = getBallot("Military Quarantined", ballotAddress);
			newCitizen = new SickSoldier(name, ID, year, inQur, tempBallot,hasWeapon);
		}
		if(allCitizens.add(newCitizen)) {
			if(election2020.addCitizen(newCitizen))
				return true;
		}
		return false;
	}
	public boolean updateCandidate(String ballotAddress,String party,int rank) {
		Ballot<?> tempBallot;
		Citizen newCitizen ;
		Party tempParty = new Party();
		int index = 0;
		for (int i = 0; i < election2020.getCandidateParties().size(); i++) {
			if (party == election2020.getCandidateParties().get(i).getName()) {
				tempParty =  election2020.getCandidateParties().get(i);
				index= i;
			}
		}
		if(ballotType == "Candidate") {
			tempBallot = getBallot("Candidate", ballotAddress);
			newCitizen = new Candidate(name, ID, year, inQur, tempBallot,rank,tempParty);
			election2020.getCandidateParties().get(index).addCandidate((Candidate)newCitizen);
		}else {
			tempBallot = getBallot("Corona Candidate", ballotAddress);
			newCitizen = new SickCandidate(name, ID, year, inQur, tempBallot,rank,tempParty);
			election2020.getCandidateParties().get(index).addCandidate((SickCandidate)newCitizen);
		}
		
		if(allCitizens.add(newCitizen)) {
			if(election2020.addCitizen(newCitizen))
				return true;
		}
		return false;
	}
	
	public String findTypeOfCitizen(int year,boolean inQur) {
		year = 2020-year;
	//check if is soldier or sick soldier
		if(year>=18 && year<=21) {
			if(inQur) {
				return "Sick Soldier";
			}
			return "Soldier";
		}
		//check if citizen or sick citizen
		if(inQur) {
			return "Sick Citizen";
		}
		return "Citizen";
	}
	
	public String findTypeOfCandidate(boolean inQur) {
		if(inQur)
			return "Sick Candidate";
		else
			return "Candidate";
	}
	
	public Ballot<?> getBallot(String bType, String ballotddress) {
		List<Ballot<?>> tempList = new ArrayList<Ballot<?>>();
		switch (bType) {
		case "Regular":
			tempList.addAll(election2020.getCitizenBallots());
			break;
		case "Military Quarantined":
			tempList.addAll(election2020.getSickSoldierBallots());
			break;
		case "Military":
			tempList.addAll(election2020.getSoldierBallots());
			break;
		case "Corona":
			tempList.addAll(election2020.getSickCitizenBallots());
			break;
		case "Candidate":
			tempList.addAll(election2020.getCandidateBallots());
			break;
		case "Corona Candidate":
			tempList.addAll(election2020.getSickCandidateBallots());
			break;
		}
		for (int i = 0; i < tempList.size(); i++) {
			if (ballotddress == tempList.get(i).getBoxAdress())
				return tempList.get(i);
		}
	return null;
	}
	
	public void sendCitizenInfo(String name,String id,int year,boolean inQur,boolean hasSuit,boolean hasWeapon,String ballotType) {
		this.name = name;
		this.ID = Integer.decode(id);
		this.year = year;
		this.inQur = inQur;
		this.hasWeapon = hasWeapon;
		this.ballotType = ballotType;
	}
	
	public void sendCandidateInfo(String name, String id, int year, boolean inQur, String ballotType) {
		this.name = name;
		this.ID = Integer.decode(id);
		this.year = year;
		this.inQur = inQur;
		this.ballotType = ballotType;
	}
	
	public void updateParty(String name,String wing,int day,int month,int year) {
		Party newParty = new Party(name,wing,day,month,year);
		election2020.addParty(newParty);
	}
	
	public ElectionRound getElection() {
		return election2020;
	}

}

