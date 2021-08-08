package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Ballot;
import Model.IllegalInput;
import Model.Model;
import View.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Toggle;

public class Controller {
	private Model theModel;
	private View theView;
	private ArrayList<String> wantBallot;

	public Controller(Model m, View v) {
		theModel = m;
		theView = v;
		wantBallot = new ArrayList<String>();

		// theView.update(theModel);

		// 1. add event: Change wanted Action
		ChangeListener<Toggle> chl = new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				// MAIN EVENT SELECTED
				String action = theView.getAction();
				if (action == "Add Ballot")
					theView.addCreateBallotData();
				else if (action == "Add Citizen")
					theView.addCreateCitizenData(false);
				else if (action == "Add Party")
					theView.addCreatePartyData();
				else if (action == "Add Candidate")
					theView.addCreateCitizenData(true);
				else if (action == "Show All Ballots") {
					theView.showBallot(theModel.getElection());
				} else if (action == "Show All Citizens")
					theView.showCitizen(theModel.getElection());
				else if (action == "Show All Parties")
					theView.showParty(theModel.getElection());
				else if (action == "Vote") {
					theModel.getElection().setNumberOfVoters();
					theModel.getElection().setAllBallotsInList();
					theModel.getElection().initVoters();
					theView.showVote(theModel.getElection());
				} else if (action == "Show Results") {
					theView.showResults(theModel.getElection(), theModel.getElection().getVoteTookPlace());
					theModel.getElection().setVoteTookPlace(false);
				}

			}
		};

		// 2. connect the ChangeListener to view
		theView.addChangeListenerToToggleGroup(chl);

		// 1. add event: Change show if in quarantine
		ChangeListener<Toggle> chYNCitizen = new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {

				boolean inQurantine = theView.getQurValue();
				if (inQurantine) {
					theView.addInQuarantine(false);
				} else {
					theView.removeInQuarantine(false);
				}
			}
		};
		// 2. connect the ChangeListener to view
		theView.addChangeListenerToToggleGroupYNCitizen(chYNCitizen);

		// 1. add event: Change show if in quarantine
		ChangeListener<Toggle> chYNCandidate = new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				//
				boolean inQurantine = theView.getCandQurValue();
				if (inQurantine) {
					theView.addInQuarantine(true);
				} else {
					theView.removeInQuarantine(true);
				}
			}
		};
		// 2. connect the ChangeListener to view
		theView.addChangeListenerToToggleGroupYNCandidate(chYNCandidate);

		// 1.add event to Add Ballot Option
		EventHandler<ActionEvent> eventForSubmitBallot = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				theModel.updateBallot(theView.getAddress(), theView.getType());
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setContentText("Ballot created");
				a.show();
				theView.addCreateBallotData();
			}
		};
		// attach this event to Submit Button pressed
		theView.addEventHandlerToSubmitBallot(eventForSubmitBallot);

		// 1.add event to Add Citizen Option
		EventHandler<ActionEvent> eventForSubmitCitizen = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String typeCitizen = theModel.findTypeOfCitizen(theView.getYear(), theView.getQurValue());

				// createBallotButtons
				List<Ballot<?>> tempList = new ArrayList<Ballot<?>>();
				wantBallot.clear();
				if (typeCitizen == "Citizen") {
					tempList.addAll(theModel.getElection().getCitizenBallots());
				} else if (typeCitizen == "Sick Citizen") {
					tempList.addAll(theModel.getElection().getSickCitizenBallots());
				} else if (typeCitizen == "Soldier") {
					tempList.addAll(theModel.getElection().getSoldierBallots());
				} else if (typeCitizen == "Sick Soldier") {
					tempList.addAll(theModel.getElection().getSickSoldierBallots());
				}
				for (int i = 0; i < tempList.size(); i++) {
					String address = tempList.get(i).getBoxAdress();
					wantBallot.add(address);

				}
				boolean isIdOkay;
				try {
					String strID = "" + theView.getID();
					if (strID.length() == 9) {
						isIdOkay = true;
					} else if (strID.length() < 9) {
						throw new IllegalInput("Citizen's ID contain less digit than 9");
					} else {
						throw new IllegalInput("Citizen's ID contain more digit than 9");
					}
				} catch (IllegalInput exep) {
					Alert a = new Alert(Alert.AlertType.CONFIRMATION);
					a.setContentText("Citizen's Id exception: " + exep.getMessage());
					a.show();
					isIdOkay = false;
				}
				if (isIdOkay) {
					theView.createBallotButtons(wantBallot, false);
					theModel.sendCitizenInfo(theView.getName(), theView.getID(), theView.getYear(),
							theView.getQurValue(), theView.getSuitValue(), theView.getWeaponValue(), typeCitizen);
				}
			}
		};
		// 2.attach this event to Submit Button pressed
		theView.addEventHandlerToSubmitCitizen(eventForSubmitCitizen);

		// 1.add event to selected ballot for Citizen
		EventHandler<ActionEvent> eventForChooseBallotCitizen = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String ballot = theView.getBallotCitizenValue();
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				if (theModel.updateCitizen(ballot)) {
					a.setContentText("Citizen created");
				} else {
					a.setContentText("Fail! Citizen has not created! Citizen already exists!");
				}
				a.show();
				theView.addCreateCitizenData(false);
			}

		};
		// 2.attach it to the view
		theView.addEventHandlerToSubmitBallotCitizen(eventForChooseBallotCitizen);

		ChangeListener<Integer> clYearBox = new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				int year = theView.getYear();
				year = 2020 - year;
				if (year >= 18 && year <= 21) {
					theView.addSoldierCheck();
				} else {
					theView.removeSoldierCheck();
				}
			}
		};
		// 2.attach clYearBox to combo box
		theView.addChangeListenerToYearBirth(clYearBox);

		// 1.add event to Add Party Option
		EventHandler<ActionEvent> eventForSubmitParty = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				theModel.updateParty(theView.getNameParty(), theView.getPartyWing(), theView.getDayCreate(),
						theView.getMonthCreate(), theView.getYearCreate());
				Alert a = new Alert(Alert.AlertType.INFORMATION);
				a.setContentText("Party created");
				a.show();
				theView.addCreatePartyData();
			}
		};
		// 2.attach this event to Submit Button pressed
		theView.addEventHandlerToSubmitParty(eventForSubmitParty);

		// 1.add event to Add Candidate Option
		EventHandler<ActionEvent> eventForSubmitCandidate = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String typeCandidate = theModel.findTypeOfCandidate(theView.getCandQurValue());

				// createBallotButtons
				List<Ballot<?>> tempList = new ArrayList<Ballot<?>>();
				wantBallot.clear();
				if (typeCandidate == "Candidate") {
					tempList.addAll(theModel.getElection().getCandidateBallots());
				} else if (typeCandidate == "Sick Candidate") {
					tempList.addAll(theModel.getElection().getSickCandidateBallots());
				}
				for (int i = 0; i < tempList.size(); i++) {
					String address = tempList.get(i).getBoxAdress();
					wantBallot.add(address);

				}
				boolean isIdOkay;
				try {
					String strID = "" + theView.getCandID();
					if (strID.length() == 9) {
						isIdOkay = true;
					} else if (strID.length() < 9) {
						throw new IllegalInput("Candidate's ID contain less digit than 9");
					} else {
						throw new IllegalInput("Candidate's ID contain more digit than 9");
					}
				} catch (IllegalInput exep) {
					Alert a = new Alert(Alert.AlertType.CONFIRMATION);
					a.setContentText("Citizen's Id exception: " + exep.getMessage());
					a.show();
					isIdOkay = false;
				}
				if (isIdOkay) {
					theView.createPartyButtons(theModel.getElection().getCandidateParties());
					theView.createBallotButtons(wantBallot, true);
					theModel.sendCandidateInfo(theView.getCandName(), theView.getCandID(), theView.getCandYear(),
							theView.getCandQurValue(), typeCandidate);
				}
			}
		};
		// 2.attach this event to Submit Button pressed
		theView.addEventHandlerToSubmitCandidate(eventForSubmitCandidate);

		// 1.add event to selected ballot/party for Candidate
		EventHandler<ActionEvent> eventForChooseBallotCandidate = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String ballot = theView.getBallotCandidateValue();
				String party = theView.getPartyCandidateValue();
				int rank = theView.getCandidateRank();
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				if (theModel.updateCandidate(ballot, party, rank)) {
					a.setContentText("Candidate created");
				} else {
					a.setContentText("Fail! Candidate has not created");
				}
				a.show();
				theView.addCreateCitizenData(true);
			}
		};
		// 2.attach this event to Submit Ballot for candidate
		theView.addEventHandlerToSubmitBallotCandidate(eventForChooseBallotCandidate);

		// 1. define ChangeListener
		ChangeListener<String> clShowBallot = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				theView.showBallot(theModel.getElection());
			}
		};
		// attach event to combo box
		theView.addChangeListenerToShowBallot(clShowBallot);
		EventHandler<ActionEvent> eventForSubmitVote = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (theView.getYesOrNoVote().equalsIgnoreCase("Yes")) {
					theModel.getElection().updateVote(theView.getPartySelected(), theView.getBallotNum());
					theModel.getElection().updateNumberOfVoters();
				}
				if (theView.getBallotNum() < theModel.getElection().getAllBallotInList().size() - 1) {
					if (theView.getPersonNum() < theModel.getElection().getAllBallotInList().get(theView.getBallotNum())
							.getCanVoteInBallot().size() - 1) {
						theView.setPersonNum(theView.getPersonNum() + 1);
					} else {
						theModel.getElection().getAllBallotInList().get(theView.getBallotNum()).setVoterPercentage();
						theView.setPersonNum(0);
						theView.setBallotNum(theView.getBallotNum() + 1);
					}
					theView.showVote(theModel.getElection());
				} else {
					theView.setBallotNum(0);
					theView.setPersonNum(0);
					theView.showEnd();
					theModel.getElection().setVoteTookPlace(true);

				}

			}
		};
		// attach this event to Submit Button pressed
		theView.addEventHandlerToSubmitVote(eventForSubmitVote);

		// 1. define ChangeListener
		ChangeListener<String> clVoteOrNo = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if (theView.getYesOrNoVote().equalsIgnoreCase("Yes")) {
					theView.addVote(theModel.getElection());
				}
			}
		};
		// attach event to combo box
		theView.addChangeListenerToVoteOrNo(clVoteOrNo);
	}

	//

}
