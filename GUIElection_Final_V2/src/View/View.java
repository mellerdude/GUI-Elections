package View;

import java.util.ArrayList;
import java.util.List;
import Model.ElectionRound;
import Model.Model;
import Model.Party;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {
	private Group root;
	private ToggleGroup tg;
	private VBox vb1;
	private VBox vb2;
	
	// main radio buttons
	private RadioButton rbBallot, rbCitizen, rbCandidate, rbParty;
	private RadioButton rbShowBallot, rbShowCitizen, rbShowParty;
	private RadioButton rbVote, rbShowResults;
	
	// field of add ballot
	private ComboBox<String> cxBallotType;
	private Button bBallotSubmit;
	private TextField tfAddress;
	
	// field of add citizen
	private Text citiName;
	private Text citiID;
	private Text citiBirth;
	private Text citiQur;
	private Text soldiWep;
	private Text citiSuit;
	private Text citiChoseBallot;
	private ToggleGroup tg2;
	private Button bCitizenSubmit;
	private TextField tfName;
	private TextField tfID;
	private ComboBox<Integer> cxYearBirth;
	private RadioButton rbYesQur;
	private RadioButton rbNoQur;
	private ComboBox<Boolean> cxWeapen;
	private ComboBox<Boolean> cxSuit;
	private ComboBox<String> cxcitiBallot;
	private Button bBallCitiSubmit;
	
	// field of add party
	private ComboBox<String> cxPartyWing;
	private Button bPartySumbit;
	private TextField tfNameParty;
	private ComboBox<Integer> cxDayCreate;
	private ComboBox<Integer> cxMonthCreate;
	private ComboBox<Integer> cxYearCreate;
	
	// field of add candidate
	private Text candName;
	private Text candID;
	private Text candBirth;
	private Text candQur;
	private Text candSuit;
	private Text candChoseBallot;
	private Text candChoseParty;
	private Text candPartyRank;
	private ToggleGroup tg3;
	private Button bCandidateSubmit;
	private TextField tfCandName;
	private TextField tfCandID;
	private ComboBox<Integer> cxCandYearBirth;
	private RadioButton rbCandYesQur;
	private RadioButton rbCandNoQur;
	private ComboBox<Boolean> cxCandSuit;
	private ComboBox<String> cxCandBallot;
	private Button bBallCandSubmit;
	private ComboBox<String> cxCandParty;
	private ComboBox<Integer> cxCandRank;
	
	// field of show ballot
	private ComboBox<String> cxBallotTypeShow;
	
	// field of show vote
	private Button bVoteSubmit;
	private ComboBox<String> cxVoteOrNo;
	private int ballotNum = 0, personNum = 0;
	private ComboBox<String> cxVoteForParty;

	public View(Stage stage) {
		// MAIN SCENARIO
		root = new Group();
		BorderPane bp = new BorderPane();
		//CHANGE
		Text txt1 = new Text("Welcome to the Election Round! Choose an action");
		txt1.setFont(Font.font("Baskerville Old Face", 26));
		//END CANGE
		txt1.setStroke(Color.BLACK);
		HBox hb = new HBox();
		hb.getChildren().addAll(txt1);
		HBox.setMargin(txt1, new Insets(10));
		hb.setAlignment(Pos.TOP_CENTER);
		tg = new ToggleGroup();
		rbBallot = new RadioButton("Add Ballot");
		rbCitizen = new RadioButton("Add Citizen");
		rbCandidate = new RadioButton("Add Candidate");
		rbParty = new RadioButton("Add Party");
		rbShowBallot = new RadioButton("Show All Ballots");
		rbShowCitizen = new RadioButton("Show All Citizens");
		rbShowParty = new RadioButton("Show All Parties");
		rbVote = new RadioButton("Vote");
		rbShowResults = new RadioButton("Show Results");

		rbBallot.setToggleGroup(tg);
		rbCitizen.setToggleGroup(tg);
		rbCandidate.setToggleGroup(tg);
		rbParty.setToggleGroup(tg);
		rbShowBallot.setToggleGroup(tg);
		rbShowCitizen.setToggleGroup(tg);
		rbBallot.setToggleGroup(tg);
		rbBallot.setToggleGroup(tg);
		rbShowParty.setToggleGroup(tg);
		rbVote.setToggleGroup(tg);
		rbShowResults.setToggleGroup(tg);

		vb1 = new VBox();
		vb1.getChildren().addAll(rbBallot, rbCitizen, rbCandidate, rbParty, rbShowBallot, rbShowCitizen, rbShowParty,
				rbVote, rbShowResults);
		double red = 120;
		double blue = 0;
		double green = 0;
		for (int i = 0; i < vb1.getChildren().size(); i++) {
			VBox.setMargin(vb1.getChildren().get(i), new Insets(5, 30, 20, 20));
			((RadioButton) vb1.getChildren().get(i)).setFont(Font.font("Arial", 14));
			((RadioButton) vb1.getChildren().get(i)).setTextFill(Color.rgb((int) red +(i*10) , (int) green, (int) blue));

		}
		vb1.setAlignment(Pos.TOP_LEFT);

		vb2 = new VBox();
		vb2.setAlignment(Pos.TOP_CENTER);
		// create Ballot scenario props
		tfAddress = new TextField();// to read address
		tfAddress.setMaxSize(350, 200);
		cxBallotType = new ComboBox<String>();
		cxBallotType.getItems().addAll("Regular", "Military", "Quarantined", "Military Quarantined", "Candidate",
				"Candidate Quarantined");
		cxBallotType.setValue("Regular");
		bBallotSubmit = new Button("Submit");
		bBallotSubmit.setTextFill(Color.BLUEVIOLET);
		bBallotSubmit.setFont(Font.font("Tahoma",16));

		// create Citizen scenario props
		citiName = new Text("Citizen's Name: ");
		citiName.setFont(Font.font("Tahoma",16));
		citiID = new Text("Citizen's ID: ");
		citiID.setFont(Font.font("Tahoma",16));
		citiBirth = new Text("Citizen's Year of birth: ");
		citiBirth.setFont(Font.font("Tahoma",16));
		citiQur = new Text("Are Citizen in Quarantine? ");
		citiQur.setFont(Font.font("Tahoma",16));
		soldiWep = new Text("Does the Soldier has weapen?");
		soldiWep.setFont(Font.font("Tahoma",16));
		citiSuit = new Text("Does the Citizen has hazard suit?");
		citiSuit.setFont(Font.font("Tahoma",16));
		citiChoseBallot = new Text("Choose Citizen's Ballot");
		citiChoseBallot.setFont(Font.font("Tahoma",16));
		cxWeapen = new ComboBox<Boolean>();
		cxWeapen.getItems().addAll(true, false);
		cxWeapen.setValue(false);
		tfName = new TextField();// to read name
		tfName.setMaxSize(350, 200);
		tfID = new TextField();// to read ID
		tfID.setMaxSize(350, 200);
		cxYearBirth = new ComboBox<Integer>();
		for (int i = 1920; i < 2002; i++) {
			cxYearBirth.getItems().add(i);
		}
		tg2 = new ToggleGroup();
		rbYesQur = new RadioButton("YES");
		rbNoQur = new RadioButton("NO");
		rbYesQur.setToggleGroup(tg2);
		rbNoQur.setToggleGroup(tg2);
		cxSuit = new ComboBox<Boolean>();
		cxSuit.getItems().addAll(true, false);
		cxSuit.setValue(false);
		bCitizenSubmit = new Button("Submit");
		bCitizenSubmit.setTextFill(Color.CORAL);
		bCitizenSubmit.setFont(Font.font("Tahoma",16));
		cxcitiBallot = new ComboBox<String>();
		bBallCitiSubmit = new Button("Submit");
		bBallCitiSubmit.setFont(Font.font("Tahoma",16));
		bBallCitiSubmit.setTextFill(Color.CORAL);

		// create Party scenario props
		cxPartyWing = new ComboBox<String>();
		cxPartyWing.getItems().addAll("left", "center", "right");
		tfNameParty = new TextField();
		tfNameParty.setMaxSize(350, 200);
		cxDayCreate = new ComboBox<Integer>();
		for (int i = 1; i < 31; i++) {
			cxDayCreate.getItems().add(i);
		}
		cxMonthCreate = new ComboBox<Integer>();
		for (int i = 1; i <= 12; i++) {
			cxMonthCreate.getItems().add(i);
		}
		cxYearCreate = new ComboBox<Integer>();
		for (int i = 1980; i <= 2020; i++) {
			cxYearCreate.getItems().add(i);
		}
		bPartySumbit = new Button("Submit");
		bPartySumbit.setFont(Font.font("Tahoma",16));
		bPartySumbit.setTextFill(Color.GOLDENROD);

		// create candidate scenario props
		candName = new Text("Candidate's Name: ");
		candName.setFont(Font.font("Tahoma",16));
		candID = new Text("Candidate's ID: ");
		candID.setFont(Font.font("Tahoma",16));
		candBirth = new Text("Candidate's Year of birth: ");
		candBirth.setFont(Font.font("Tahoma",16));
		candQur = new Text("Are Candidate in Quarantine? ");
		candQur.setFont(Font.font("Tahoma",16));
		candSuit = new Text("Does the Candidate has hazard suit?");
		candSuit.setFont(Font.font("Tahoma",16));
		candChoseBallot = new Text("Choose Candidate's Ballot");
		candChoseBallot.setFont(Font.font("Tahoma",16));
		candChoseParty = new Text("Choose Candidate's Party");
		candChoseParty.setFont(Font.font("Tahoma",16));
		candPartyRank = new Text("Choose Candidate's Rank");
		candPartyRank.setFont(Font.font("Tahoma",16));
		tfCandName = new TextField();// to read name
		tfCandName.setMaxSize(350, 200);
		tfCandID = new TextField();// to read ID
		tfCandID.setMaxSize(350, 200);
		cxCandYearBirth = new ComboBox<Integer>();
		for (int i = 1920; i < 1998; i++) {
			cxCandYearBirth.getItems().add(i);
		}
		tg3 = new ToggleGroup();
		rbCandYesQur = new RadioButton("YES");
		rbCandNoQur = new RadioButton("NO");
		rbCandYesQur.setToggleGroup(tg3);
		rbCandNoQur.setToggleGroup(tg3);
		cxCandSuit = new ComboBox<Boolean>();
		cxCandSuit.getItems().addAll(true, false);
		cxCandSuit.setValue(false);
		bCandidateSubmit = new Button("Submit");
		bCandidateSubmit.setFont(Font.font("Tahoma",16));
		bCandidateSubmit.setTextFill(Color.SALMON);
		cxCandBallot = new ComboBox<String>();
		bBallCandSubmit = new Button("Submit");
		bBallCandSubmit.setFont(Font.font("Tahoma",16));
		bBallCandSubmit.setTextFill(Color.SALMON);
		cxCandParty = new ComboBox<String>();
		cxCandRank = new ComboBox<Integer>();
		for (int i = 1; i < 100; i++) {
			cxCandRank.getItems().add(i);
		}

		// create Show Ballot scenario props
		cxBallotTypeShow = new ComboBox<String>();
		cxBallotTypeShow.getItems().addAll("All", "Regular", "Military", "Quarantined", "Military Quarantined",
				"Candidate", "Candidate Quarantined");
		cxBallotTypeShow.setValue("All");
		
		// create Show Vote scenario props
		bVoteSubmit = new Button("Submit");
		bVoteSubmit.setFont(Font.font("Tahoma",16));
		bVoteSubmit.setTextFill(Color.RED);
		cxVoteOrNo = new ComboBox<String>();
		cxVoteOrNo.getItems().addAll("Yes", "No");
		cxVoteOrNo.setValue("No");
		
		cxVoteForParty = new ComboBox<String>();

		Pane drawPane = new Pane();
		drawPane.getChildren().add(root);

		bp.setTop(hb);
		bp.setLeft(vb1);
		bp.setCenter(vb2);
		bp.setStyle("-fx-background-color:AQUAMARINE");
		Scene scene = new Scene(bp, 800, 800);
		stage.setScene(scene);
		stage.show();
	}

	public void update(Model theModel) {
		root.getChildren().clear();
		theModel.draw(root);
	}

	public String getAction() {
		if (rbBallot.isSelected())
			return rbBallot.getText();
		else if (rbCitizen.isSelected())
			return rbCitizen.getText();
		else if (rbCandidate.isSelected())
			return rbCandidate.getText();
		else if (rbParty.isSelected())
			return rbParty.getText();
		else if (rbShowBallot.isSelected())
			return rbShowBallot.getText();
		else if (rbShowCitizen.isSelected())
			return rbShowCitizen.getText();
		else if (rbShowParty.isSelected())
			return rbShowParty.getText();
		else if (rbVote.isSelected())
			return rbVote.getText();
		else
			return rbShowResults.getText();

	}

	public String getAddress() {
		return tfAddress.getText();
	}

	public String getType() {
		return cxBallotType.getValue();
	}

	public String getName() {
		return tfName.getText();
	}

	public String getID() {
		return tfID.getText();
	}

	public int getYear() {
		return cxYearBirth.getValue();
	}

	public boolean getQurValue() {
		if (rbYesQur.isSelected())
			return true;
		else if (rbNoQur.isSelected())
			return false;
		return false;
	}

	public boolean getSuitValue() {
		return cxSuit.getValue();
	}

	public boolean getWeaponValue() {
		return cxWeapen.getValue();
	}

	public String getBallotCitizenValue() {
		return cxcitiBallot.getValue();
	}

	public String getNameParty() {
		return tfNameParty.getText();
	}

	public String getPartyWing() {
		return cxPartyWing.getValue();
	}

	public int getDayCreate() {
		return cxDayCreate.getValue();
	}

	public int getMonthCreate() {
		return cxMonthCreate.getValue();
	}

	public int getYearCreate() {
		return cxYearCreate.getValue();
	}

	public String getCandName() {
		return tfCandName.getText();
	}

	public String getCandID() {
		return tfCandID.getText();
	}

	public int getCandYear() {
		return cxCandYearBirth.getValue();
	}

	public boolean getCandQurValue() {
		if (rbCandYesQur.isSelected())
			return true;
		else if (rbCandNoQur.isSelected())
			return false;
		return false;
	}

	public boolean getCandSuitValue() {
		return cxCandSuit.getValue();
	}

	public String getBallotCandidateValue() {
		return cxCandBallot.getValue();
	}

	public String getPartyCandidateValue() {
		return cxCandParty.getValue();
	}

	public int getCandidateRank() {
		return cxCandRank.getValue();
	}

	public int getBallotNum() {
		return ballotNum;
	}

	public int getPersonNum() {
		return personNum;
	}

	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}

	public void setBallotNum(int ballotNum) {
		this.ballotNum = ballotNum;
	}

	public ComboBox<String> getCxBallotTypeShow() {
		return cxBallotTypeShow;
	}

	//Create Ballot Scenario Functions
	public void addCreateBallotData() {
		removeData();
		Text txt = new Text("ADD BALLOT");
		txt.setUnderline(true);
		txt.setFont(Font.font("Segoe Print", 20));
		txt.setFill(Color.BLUEVIOLET);
		Text txt1 = new Text("Ballot's Address: ");
		txt1.setFont(Font.font("Tahoma", 16));
		Text txt2 = new Text("Type of Ballot: ");
		txt2.setFont(Font.font("Tahoma", 16));
		tfAddress.clear();
		vb2.getChildren().addAll(txt,txt1, tfAddress, txt2, cxBallotType, bBallotSubmit);
		setMargin();


	}

	//Create Citizen/Candidate Scenarios Functions
	public void addCreateCitizenData(boolean isCandidate) {
		removeData();
		Text txt;
		if (isCandidate) {
			tfCandName.clear();
			tfCandID.clear();
			txt = new Text("ADD CANDIDATE");
			txt.setUnderline(true);
			txt.setFont(Font.font("Segoe Print", 20));
			txt.setFill(Color.SALMON);
			vb2.getChildren().addAll(txt,candName, tfCandName, candID, tfCandID, candBirth, cxCandYearBirth, candQur,
					rbCandYesQur, rbCandNoQur, bCandidateSubmit);
			cxCandYearBirth.setValue(cxCandYearBirth.getItems().get(0));
			setMargin();

		} else {
			tfName.clear();
			tfID.clear();
			txt = new Text("ADD CITIZEN");
			txt.setUnderline(true);
			txt.setFont(Font.font("Segoe Print", 20));
			txt.setFill(Color.CORAL);
			vb2.getChildren().addAll(txt,citiName, tfName, citiID, tfID, citiBirth, cxYearBirth, bCitizenSubmit);
			cxYearBirth.setValue(cxYearBirth.getItems().get(0));
			setMargin();
		}

	}
	
	public void addInQuarantine(boolean isCandidate) {
		if (isCandidate) {
			vb2.getChildren().remove(bCandidateSubmit);
			vb2.getChildren().addAll(candSuit, cxCandSuit, bCandidateSubmit);
			VBox.setMargin(candSuit, new Insets(10));
		} else {
			vb2.getChildren().remove(bCitizenSubmit);
			vb2.getChildren().addAll(citiSuit, cxSuit, bCitizenSubmit);
			VBox.setMargin(citiSuit, new Insets(10));
		}

	}

	public void removeInQuarantine(boolean isCandidate) {
		if (isCandidate)
			vb2.getChildren().removeAll(candSuit, cxCandSuit);
		else
			vb2.getChildren().removeAll(citiSuit, cxSuit);
	}
	
	public void addSoldierCheck() {
		if (!vb2.getChildren().contains(citiQur))// check if stage has quarantine data
			vb2.getChildren().removeAll(bCitizenSubmit);// remove only the submit button
		else {
			if (vb2.getChildren().contains(citiSuit)) {// check if check suit is in stage
				vb2.getChildren().removeAll(citiSuit, cxSuit);// remove it
			}
			vb2.getChildren().removeAll(citiQur, rbYesQur, rbNoQur, bCitizenSubmit);// remove quarantine data
		}
		// add check on soldier weapon and quarantine data
		vb2.getChildren().addAll(soldiWep, cxWeapen, citiQur, rbYesQur, rbNoQur, bCitizenSubmit);
		setMargin();

	}

	public void removeSoldierCheck() {

		vb2.getChildren().removeAll(soldiWep, cxWeapen, bCitizenSubmit);
		if (!vb2.getChildren().contains(citiQur))
			vb2.getChildren().addAll(citiQur, rbYesQur, rbNoQur, bCitizenSubmit);
		else
			vb2.getChildren().addAll(bCitizenSubmit);
	}

	public void createPartyButtons(List<Party> partyNames) {
		cxCandParty.getItems().clear();
		for (int i = 0; i < partyNames.size(); i++) {
			cxCandParty.getItems().add(partyNames.get(i).getName());
		}
	}
	
	public void createBallotButtons(ArrayList<String> ballotList, boolean isCandidate) {
		removeData();
		if (isCandidate) {
			cxCandBallot.getItems().clear();
			vb2.getChildren().addAll(candChoseParty, cxCandParty, candPartyRank, cxCandRank);
			cxCandParty.setValue(cxCandParty.getItems().get(0));
			cxCandRank.setValue(cxCandRank.getItems().get(0));
			for (int i = 0; i < ballotList.size(); i++) {
				String nameBallot = ballotList.get(i);
				cxCandBallot.getItems().add(nameBallot);
			}
			vb2.getChildren().addAll(candChoseBallot, cxCandBallot);
			cxCandBallot.setValue(cxCandBallot.getItems().get(0));
			vb2.getChildren().add(bBallCandSubmit);
			VBox.setMargin(candChoseParty, new Insets(10));
			VBox.setMargin(candPartyRank, new Insets(10));
			VBox.setMargin(bBallCandSubmit, new Insets(10));
		} else {
			cxcitiBallot.getItems().clear();
			for (int i = 0; i < ballotList.size(); i++) {
				String nameBallot = ballotList.get(i);
				cxcitiBallot.getItems().add(nameBallot);
			}
			vb2.getChildren().addAll(citiChoseBallot, cxcitiBallot);
			vb2.getChildren().add(bBallCitiSubmit);
			setMargin();
		}
	}

	//Create Party Scenario
	public void addCreatePartyData() {
		removeData();
		Text txt = new Text("ADD PARTY");
		txt.setUnderline(true);
		txt.setFont(Font.font("Segoe Print", 20));
		txt.setFill(Color.GOLDENROD);
		Text txt1 = new Text("Party Name: ");
		txt1.setFont(Font.font("Tahoma",16));
		Text txt2 = new Text("Party Wing: ");
		txt2.setFont(Font.font("Tahoma",16));
		Text txt3 = new Text("Day of Creation: ");
		txt3.setFont(Font.font("Tahoma",16));
		Text txt4 = new Text("Month of Creation: ");
		txt4.setFont(Font.font("Tahoma",16));
		Text txt5 = new Text("Year of Creation: ");
		txt5.setFont(Font.font("Tahoma",16));
		vb2.getChildren().addAll(txt,txt1, tfNameParty, txt2, cxPartyWing, txt3, cxDayCreate, txt4, cxMonthCreate, txt5,
				cxYearCreate, bPartySumbit);
		VBox.setMargin(txt, new Insets(10));
		VBox.setMargin(txt1, new Insets(10));
		VBox.setMargin(txt2, new Insets(10));
		VBox.setMargin(txt3, new Insets(10));
		VBox.setMargin(txt4, new Insets(10));
		VBox.setMargin(txt5, new Insets(10));
		VBox.setMargin(bPartySumbit, new Insets(10));
		tfNameParty.clear();
		setMargin();
	}

	//Show Ballot Scenario
	public void showBallot(ElectionRound election) {
		removeData();
		Text txt = new Text("SHOW ALL BALLOTS");
		txt.setUnderline(true);
		txt.setFont(Font.font("Segoe Print", 20));
		txt.setFill(Color.GREEN);
		Text txt1 = new Text("Choose ballot type");
		Text txt2 = new Text(election.showBallot(cxBallotTypeShow.getValue()));
		txt1.setFont(Font.font("Tahoma",16));
		txt2.setFont(Font.font(16));
		ScrollPane vbs = new ScrollPane(txt2);
		vb2.getChildren().addAll(txt,txt1, cxBallotTypeShow, txt2, vbs);
		setMargin();
		

	}

	//Show Citizen Scenario
	public void showCitizen(ElectionRound election) {
		removeData();
		Text txt = new Text("SHOW ALL CITIZENS");
		txt.setUnderline(true);
		txt.setFont(Font.font("Segoe Print", 20));
		txt.setFill(Color.PALEVIOLETRED);
		Text txt1 = new Text("The citizens are:");
		Text txt2 = new Text(election.showCitizen());
		txt1.setFont(Font.font("Tahoma",16));
		txt2.setFont(Font.font(16));
		ScrollPane vbs = new ScrollPane(txt2);
		vb2.getChildren().addAll(txt,txt1, vbs);
		setMargin();
	}

	//Show Party Scenario
	public void showParty(ElectionRound election) {
		removeData();
		Text txt = new Text("SHOW ALL PARTY");
		txt.setUnderline(true);
		txt.setFont(Font.font("Segoe Print", 20));
		txt.setFill(Color.MAGENTA);
		Text txt1 = new Text("The parties in the election are");
		Text txt2 = new Text(election.showParty());
		txt1.setFont(Font.font("Tahoma",16));
		txt2.setFont(Font.font(16));
		ScrollPane vbs = new ScrollPane(txt2);
		vb2.getChildren().addAll(txt,txt1, vbs);
		setMargin();
	}

	//Show Vote Scenario
	public void showVote(ElectionRound election2020) {
		removeData();
		Text txt = new Text("VOTE");
		txt.setUnderline(true);
		txt.setFont(Font.font("Segoe Print", 20));
		txt.setFill(Color.RED);
		cxVoteOrNo.setValue("No");
		Text txt1 = new Text("Welcome to Election " + election2020.getVoteYear());
		txt1.setFont(Font.font("Tahoma",16));
		Text txt3 = new Text(
				":\nNow each citizen will decide if they would like to vote and who would they like to vote to.\n"
						+ "In Ballot number " + election2020.getAllBallotInList().get(ballotNum).getIdBox() + " in "
						+ election2020.getAllBallotInList().get(ballotNum).getBoxAdress());
		txt3.setFont(Font.font("Tahoma",16));
		Text txt2 = new Text("Would the citizen "
				+ election2020.getAllBallotInList().get(ballotNum).getCanVoteInBallot().get(personNum).getName()
				+ " Like to vote?");
		txt2.setFont(Font.font("Tahoma",16));

		vb2.getChildren().addAll(txt,txt1, txt2, cxVoteOrNo, bVoteSubmit);
		setMargin();
	}

	public void addVote(ElectionRound election2020) {
		vb2.getChildren().removeAll(cxVoteOrNo,bVoteSubmit);
		cxVoteForParty.getItems().clear();
		Text txt1 = new Text("Choose a party");
		txt1.setFont(Font.font("Tahoma",16));
		for (int i = 0; i < election2020.getCandidateParties().size(); i++) {
			cxVoteForParty.getItems().add(election2020.getCandidateParties().get(i).getName());
		}
		cxVoteForParty.setValue(cxVoteForParty.getItems().get(0));
		vb2.getChildren().addAll(txt1, cxVoteForParty, bVoteSubmit);
		setMargin();
	}

	public void showEnd() {
		removeData();
		Text txt = new Text("VOTE");
		txt.setUnderline(true);
		txt.setFont(Font.font("Segoe Print", 20));
		txt.setFill(Color.RED);
		Text txt1 = new Text("Voting ended");
		txt1.setFont(Font.font("Tahoma",16));
		vb2.getChildren().addAll(txt,txt1);
		setMargin();
	}

	//Show Results Scenario
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void showResults(ElectionRound election2020, boolean tookPlace) {
		removeData();
		Text txt = new Text("SHOW RESULTS");
		txt.setUnderline(true);
		txt.setFont(Font.font("Segoe Print", 20));
		txt.setFill(Color.YELLOW);
		Text txt1 = new Text();
		txt1.setFont(Font.font("Tahoma",16));
		Text txt2 = new Text();
		txt2.setFont(Font.font(16));
		StringBuffer sb = new StringBuffer("");
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		if (tookPlace) {
			boolean thereIsAWinner = election2020.checkResult();
			for (int i = 0; i < election2020.getCandidateParties().size(); i++) {
				double num = ((double)election2020.getTotalVoteForParty().get(i) / (election2020.getNumberOfVoters())* 100);
				num = (double)Math.round(num*100)/100;
				sb.append("The party " + election2020.getCandidateParties().get(i).getName() + " got "
						+ election2020.getTotalVoteForParty().get(i) + "(" + num + "%)" + "\n");
			}
			bc.setTitle("Final Results");
			xAxis.setLabel("Party");
			yAxis.setLabel("Voting Percentage");
			yAxis.setAutoRanging(false);
			yAxis.setUpperBound(100);
			for (int i = 0; i < election2020.getCandidateParties().size(); i++) {
				XYChart.Series VotingPercentage = new XYChart.Series();
				VotingPercentage.getData()
				.add(new XYChart.Data("",
						((double) election2020.getTotalVoteForParty().get(i) / election2020.getNumberOfVoters())
						* 100));
				VotingPercentage.setName(election2020.getCandidateParties().get(i).getName());
				bc.getData().add(VotingPercentage);
			}
			txt2.setText(sb.toString());
			if (thereIsAWinner) {
				txt1.setText(election2020.toString());
			} else {
				txt1.setText("There is a TIE. Voting must take place again");
			}
			vb2.getChildren().addAll(txt,bc,txt2, txt1);
		} else {
			txt1.setText("Voting didnt take place yet");
			txt2.setText(sb.toString());
			vb2.getChildren().addAll(txt,txt2, txt1);
		}
		setMargin();


	}
	
	public void setMargin() {
		for (int i = 0; i < vb2.getChildren().size(); i++) {
			VBox.setMargin(vb2.getChildren().get(i),new Insets(10));
		}
	}
	
	public void removeData() {
		vb2.getChildren().clear();
	}

	public int getPartySelected() {
		int PartyNum = 0;
		for (int i = 0; i < cxVoteForParty.getItems().size(); i++) {
			if (cxVoteForParty.getItems().get(i).equalsIgnoreCase(cxVoteForParty.getValue()))
				PartyNum = i;
		}
		return PartyNum;
	}

	// button events
	public void addChangeListenerToYearBirth(ChangeListener<Integer> listener) {
		cxYearBirth.valueProperty().addListener(listener);
	}

	public void addEventHandlerToSubmitBallot(EventHandler<ActionEvent> event) {
		bBallotSubmit.setOnAction(event);
	}

	public void addEventHandlerToSubmitCitizen(EventHandler<ActionEvent> event) {
		bCitizenSubmit.setOnAction(event);
	}

	public void addEventHandlerToSubmitBallotCitizen(EventHandler<ActionEvent> event) {
		bBallCitiSubmit.setOnAction(event);
	}

	public void addEventHandlerToSubmitParty(EventHandler<ActionEvent> event) {
		bPartySumbit.setOnAction(event);
	}

	public void addEventHandlerToSubmitCandidate(EventHandler<ActionEvent> event) {
		bCandidateSubmit.setOnAction(event);
	}

	public void addEventHandlerToSubmitBallotCandidate(EventHandler<ActionEvent> event) {
		bBallCandSubmit.setOnAction(event);
	}

	public void addEventHandlerToShowBallot(EventHandler<ActionEvent> event) {
		cxBallotTypeShow.setOnAction(event);
	}

	public void addEventHandlerToSubmitVote(EventHandler<ActionEvent> event) {
		bVoteSubmit.setOnAction(event);
	}

	public String getYesOrNoVote() {
		return cxVoteOrNo.getValue();
	}

	// Change Listeners
	// main change listener
	public void addChangeListenerToToggleGroup(ChangeListener<Toggle> listener) {
		tg.selectedToggleProperty().addListener(listener);
	}

	public void addChangeListenerToToggleGroupYNCitizen(ChangeListener<Toggle> listener) {
		tg2.selectedToggleProperty().addListener(listener);
	}

	public void addChangeListenerToToggleGroupYNCandidate(ChangeListener<Toggle> listener) {
		tg3.selectedToggleProperty().addListener(listener);
	}

	public void addChangeListenerToVoteOrNo(ChangeListener<String> event) {
		cxVoteOrNo.valueProperty().addListener(event);

	}

	public void addChangeListenerToShowBallot(ChangeListener<String> clShowBallot) {
		cxBallotTypeShow.valueProperty().addListener(clShowBallot);
	}

}
