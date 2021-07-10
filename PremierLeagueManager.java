/* *****************************************************************************
 *  Name:    Chamith Wanniarachchi
 *  Student ID:   2019728 w1790180
 *
 *  Description:  PremierLeagueManager class
 *
 **************************************************************************** */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager{
    //create get data two array lists
    private final ArrayList<PlayMatch> matches = new ArrayList<>();
    private final ArrayList<FootballClub> league = new ArrayList<>();

    public PremierLeagueManager() {
    }

    @Override
    //football club add method
    public void addClub(FootballClub footballClub) {
        //check club already added or not
        if (league.contains(footballClub)){
            System.out.println("Club already added !!!");
        }else {
            //add club in to League
            league.add(footballClub);
            System.out.println(footballClub.getName() + " Club Added in League..!");
        }

    }

    @Override
    //football club delete method
    public void deleteClub(String name, String location) {
        //check club list already empty
        if (league.isEmpty()){
            System.out.println("Empty club list !!!");
            return;
        }

        boolean bool = false;
        //check club league list and delete
        for (FootballClub footballClub : league){
            //club name and location check before delete
            if (footballClub.getName().equalsIgnoreCase(name)){
                if (footballClub.getLocation().equalsIgnoreCase(location)){
                    //removing club in list
                    league.remove(footballClub);
                    System.out.println("Delete successfully "+ name);
                    bool=true;
                    break;
                }
            }
        }
        //if check name and location match
        if (!bool){
            System.out.println("You entered details are not Match !!!");
        }
    }

    @Override
    //print football club list method
    public void printClubList() {
        //checking list is empty
        if (league.isEmpty()){
            System.out.println("Empty Clubs in List !!!");
            return;
        }
        //print club list using loop
        System.out.println("++ Club List in League ++");
        for (FootballClub footballClub: league){
            System.out.println(footballClub);
        }
        System.out.println("");
    }

    @Override
    public void displayStatistics(String name) {
        //check if list empty or not
        if (league.isEmpty()){
            System.out.println("Empty Clubs to show Statics !!!");
            return;
        }

        boolean bool = false;
        //check club in league throughout list
        for (FootballClub footballClub : league){
            //check if details match or not
            if (footballClub.getName().equals(name)){
                //display statics
                System.out.println("Club name:                "
                        + footballClub.getName());
                System.out.println("Matches Played:           "
                        + footballClub.getNumberOfMatchesPlayed());
                System.out.println("Current number of Points: "
                        + footballClub.getNumberOfPoints());
                System.out.println("Matches Won:              "
                        + footballClub.getNumberOfWins());
                System.out.println("Matches Lost:             "
                        + footballClub.getNumberOfLoss());
                System.out.println("Matches Draw:             "
                        + footballClub.getNumberOfDraws());
                System.out.println("Goals Scored:             "
                        + footballClub.getNumberOfGoalsScored());
                System.out.println("Goals Received:           "
                        + footballClub.getNumberOfGoalsReceived());
                bool=true;
                break;
            }
        }
        //if details are not match
        if (!bool){
            System.out.println("No match club in League !!!");
        }
    }

    @Override
    //display league table method
    public void displayLeagueTable() {
        //check if list empty or not
        if (league.isEmpty()){
            System.out.println("Empty Clubs to show Statics !!!");
            return;
        }
        //set league statics list descending order
        league.sort(Collections.reverseOrder());
        for (FootballClub footballClub: league){
            //display league table
            System.out.println(footballClub.getName()+
                    "|| Matches played: " +footballClub.getNumberOfMatchesPlayed()+
                    "|| Matches won: " + footballClub.getNumberOfWins() +
                    "|| Matches lost: "+footballClub.getNumberOfLoss()+
                    "|| Matches draw: "+footballClub.getNumberOfDraws()+
                    "|| Points got: "+footballClub.getNumberOfPoints() +
                    "|| Total goals scored: "+footballClub.getNumberOfGoalsScored()+
                    "|| Total goals received: "+footballClub.getNumberOfGoalsReceived());
        }
        System.out.println("");
    }


    @Override
    //add match method
    public void addMatch() {
        //create scanner get user details
        Scanner sc=new Scanner(System.in);
        //check list is empty or not
        if(league.isEmpty()){
            System.out.println("Empty clubs to Add match details !!!");
            return;
        }
        //get match play date from user
        System.out.println("Enter Match Play Date(yyyy-mm-dd): ");
        String day = sc.next();
        Date date;
        //try catch to check date format
        try {
            //set date format include
            date = new SimpleDateFormat("yyyy-MM-dd").parse(day);

        //catch to check need to user input format date
        } catch (ParseException e) {
            System.out.println("Enter date format yyyy-MM-dd");
            return;
        }

        System.out.println("Enter the First team :");
        String firstTeam  = sc.next(); // get user data match

        //check user input clubs
        boolean bool = Boolean.FALSE;
        for (FootballClub footballClub : league) {
            //check if user input club in league or not
            if (footballClub.getName().equalsIgnoreCase(firstTeam)) {
                //add number of played match
                footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed()+1);
                bool = true;

            }
        }
        //if club is not in league
        if (!bool ){
            System.out.println("Your club not matching "+ firstTeam+ " in the league!!!...");
            return;
        }

        System.out.println("Enter the Second team :");
        String secondTeam =sc.next();// get user data match

        //check user input clubs
        boolean bool1 = Boolean.FALSE;
        for (FootballClub footballClub : league) {
            //check if user input club in league or not
            if (footballClub.getName().equalsIgnoreCase(secondTeam )) {
                //add number of played match
                footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed()+1);
                bool1 =true;
            }
        }
        //if club in not in league
        if (!bool1 ){
            System.out.println("Your club not matching "+ secondTeam  + " in the league!!!...");
            return;
        }

        int firstTeamGoals;//get user match details
        int secondTeamGoals;
        System.out.println("Enter First team Goals : ");
        try {
            //check user input integer or not
            firstTeamGoals = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Please enter number here !!!");
            return;
        }
        System.out.println("Enter Second team Goals : ");//get user match details
        //check user input integer or not
        try {
            secondTeamGoals = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Please enter number here !!!");
            return;
        }
        //set attribute for match
        PlayMatch playMatch = new PlayMatch(firstTeam,secondTeam ,firstTeamGoals,secondTeamGoals,date);
        //add match in the league
        matches.add(playMatch);

        //infinite loop
        for (FootballClub footballClub: league){
            if (footballClub.getName().equalsIgnoreCase(playMatch.getFirstClub())){
                //check match score one by one
                if (playMatch.getFirstClubScore()> playMatch.getSecondClubScore()){
                    //set numOfWins count plus one
                    footballClub.setNumberOfWins(footballClub.getNumberOfWins()+1);
                    //add points win team
                    footballClub.setNumberOfPoints(footballClub.getNumberOfPoints()+10);
                }else if (playMatch.getFirstClubScore() < playMatch.getSecondClubScore()){
                    //set numOfLoss count plus one and add
                    footballClub.setNumberOfLoss(footballClub.getNumberOfLoss()+1);
                }else {
                    //match is draw then count plus one
                    footballClub.setNumberOfDraws(footballClub.getNumberOfDraws()+1);
                    //add point draw
                    footballClub.setNumberOfPoints(footballClub.getNumberOfPoints()+5);
                }
                //set goals score count
                footballClub.setNumberOfGoalsScored(footballClub.getNumberOfGoalsScored()+firstTeamGoals);
                //set goals received count
                footballClub.setNumberOfGoalsReceived(footballClub.getNumberOfGoalsReceived()+secondTeamGoals);

            }else if (footballClub.getName().equalsIgnoreCase(playMatch.getSecondClub())){
                //check match score one by one
                if (playMatch.getFirstClubScore()< playMatch.getSecondClubScore()){
                    //add win points and counts
                    footballClub.setNumberOfWins(footballClub.getNumberOfWins()+1);
                    footballClub.setNumberOfPoints(footballClub.getNumberOfPoints()+10);
                }else if (playMatch.getFirstClubScore() > playMatch.getSecondClubScore()){
                    //add loss points and counts
                    footballClub.setNumberOfLoss(footballClub.getNumberOfLoss()+1);
                }else {
                    //add draw points and counts
                    footballClub.setNumberOfDraws(footballClub.getNumberOfDraws()+1);
                    footballClub.setNumberOfPoints(footballClub.getNumberOfPoints()+5);
                }
                //set score and goal received score
                footballClub.setNumberOfGoalsScored(footballClub.getNumberOfGoalsScored()+secondTeamGoals);
                footballClub.setNumberOfGoalsReceived(footballClub.getNumberOfGoalsReceived()+firstTeamGoals);
            }
        }
    }

    @Override
    //save user data method
    public void saveData() {
        try {
            //create fileOutPut stream to user save data in text file
            FileOutputStream fileOutputStream = new FileOutputStream("./DataClub.txt");
            //create fileOutPut stream to user save data in text file
            FileOutputStream fileOutputStream1 =new FileOutputStream("./DataMatch.txt");
            //create object in objectOutputStream
            ObjectOutputStream objectOutputStream =new ObjectOutputStream(fileOutputStream);
            //create object in objectOutputStream
            ObjectOutputStream objectOutputStream1 =new ObjectOutputStream(fileOutputStream1);
            //get objects in list
            for(FootballClub footballClub : league){
                if (footballClub!= null){
                    //add object to files
                    objectOutputStream.writeObject(footballClub);
                }
            }
            //get object from list
            for (PlayMatch playMatch : matches){
                if (playMatch !=null){
                    //adding object to files
                    objectOutputStream1.writeObject(playMatch);
                }
            }
            System.out.println("Successfully!! data saved in the System");

            //close objectOutputStreams
            objectOutputStream.close();
            objectOutputStream1.close();
            //close fileOutputStreams
            fileOutputStream.close();
            fileOutputStream1.close();
            //flush objectOutputStream
            objectOutputStream.flush();
            objectOutputStream1.flush();

        } catch (FileNotFoundException e) {
            //file is missing or not found
            System.out.println("File not Found");

        }catch(Exception e){
            //if any other mistakes
            System.out.println("Have some error");
        }
    }

    @Override
    //load user data method
    public void loadData() throws IOException {

        try{
            //create fileInputStream for league list
            FileInputStream fileInputStream =new FileInputStream("./DataClub.txt");
            //creating fileInputStream access text file
            FileInputStream fileInputStream1 =new FileInputStream("./DataMatch.txt");
            //creating objectInputStream for league list
            ObjectInputStream objectInputStream =new ObjectInputStream(fileInputStream);
            //creating objectInputStream for access text list
            ObjectInputStream objectInputStream1 =new ObjectInputStream(fileInputStream1);

            //infinite for loop
            for (;;){
                try {
                    //football club object read
                    FootballClub footballClub = (FootballClub) objectInputStream.readObject();
                    //add football club in list
                    league.add(footballClub);
                }catch (IOException ioException){
                    break;
                }
            }
            //infinite for loop
            for (; ;){
                try {
                    //play match object read
                    PlayMatch playMatch =(PlayMatch) objectInputStream1.readObject();
                    //adding playMatch in list
                    matches.add(playMatch);
                }catch (IOException ioException){
                    break;
                }
            }
            //closing objectInputStream
            objectInputStream.close();
            objectInputStream1.close();
            System.out.println("Successfully!!! data saved");
        } catch (IOException | ClassNotFoundException e) {
            //any other mistakes
            System.out.println("Have Some error");
        }
    }

    @Override
    //random match method
    public void randomMatch(){
        //check league list is empty or not
        if (league.isEmpty()){
            return;
        }
        //create football club
        FootballClub teamOne;
        FootballClub teamTwo;

        //date object create
        Date date=new Date();

        //create random number assign in to variables
        teamOne = league.get(new Random().nextInt(league.size()));
        teamTwo = league.get(new Random().nextInt(league.size()));

        //get team names
        String teamOneName = teamOne.getName();
        String teamTwoName = teamTwo.getName();

        //check teams are randomly get or not
        if(teamOneName.equals(teamTwoName)){
            System.out.println("TryAgain!!!");
            return;
        }

        //create random score
        Random scoreOne = new Random();
        int teamOneScore = scoreOne.nextInt(5);
        Random scoreTwo = new Random();
        int teamTwoScore = scoreTwo.nextInt(5);

        //add attribute for match
        PlayMatch playMatch = new PlayMatch(teamOneName,teamTwoName,teamOneScore,teamTwoScore,date);
        //add match in league
        matches.add(playMatch);

        for (FootballClub footballClub: league){
            if (footballClub.getName().equalsIgnoreCase(teamOneName)){
                //check match score and points
                if (teamOneScore> teamTwoScore){
                    //add win count plus one
                    footballClub.setNumberOfWins(footballClub.getNumberOfWins()+1);
                    //add point to win team
                    footballClub.setNumberOfPoints(footballClub.getNumberOfPoints()+10);
                }else if (teamOneScore< teamTwoScore){
                    //add loss count plus one and add point
                    footballClub.setNumberOfLoss(footballClub.getNumberOfLoss()+1);
                }else {
                    //match is draw then add draw point
                    footballClub.setNumberOfDraws(footballClub.getNumberOfDraws()+1);
                    footballClub.setNumberOfPoints(footballClub.getNumberOfPoints()+5);
                }
                //set goals score
                footballClub.setNumberOfGoalsScored(footballClub.getNumberOfGoalsScored()+teamOneScore);
                //set received goals score
                footballClub.setNumberOfGoalsReceived(footballClub.getNumberOfGoalsReceived()+teamTwoScore);

            }else if (footballClub.getName().equalsIgnoreCase(teamTwoName)){
                //check match win ,loss and draw points
                if (teamOneScore< teamTwoScore){
                    //add win count and points
                    footballClub.setNumberOfWins(footballClub.getNumberOfWins()+1);
                    footballClub.setNumberOfPoints(footballClub.getNumberOfPoints()+10);
                }else if (teamOneScore> teamTwoScore){
                    //add loss count and points
                    footballClub.setNumberOfLoss(footballClub.getNumberOfLoss()+1);
                }else {
                    //add draw count and points
                    footballClub.setNumberOfDraws(footballClub.getNumberOfDraws()+1);
                    footballClub.setNumberOfPoints(footballClub.getNumberOfPoints()+5);
                }
                //add goals score and received goals score
                footballClub.setNumberOfGoalsScored(footballClub.getNumberOfGoalsScored()+teamTwoScore);
                footballClub.setNumberOfGoalsReceived(footballClub.getNumberOfGoalsReceived()+teamOneScore);
            }
        }
        //print match
        System.out.println("Team one : "+teamOneName+
                " Team one score : "+teamOneScore);
        System.out.println("Team two : "+teamTwoName+
                " Team two score : "+teamTwoScore);

        StackPane stackPane =new StackPane();
        //print random match using label
        Label label=new Label("Date: " +date+
                "\nTeam one: "+teamOneName +
                " scored "+teamOneScore+
                " goals."+
                "\nTeam two: " +teamTwoName+
                " scored "+teamTwoScore+
                " goals." );
        label.setStyle("-fx-text-fill: blue;");
        label.setFont(Font.font(20));

        stackPane.getChildren().addAll(label);//add label
        Scene scene=new Scene(stackPane);
        Stage stage=new Stage();//create stage
        stage.setTitle("Random Match");
        stage.setScene(scene);//set scene
        stage.setWidth(550);//stage location
        stage.setHeight(337);
        stage.showAndWait();//show stage
        }


    public ObservableList<FootballClub> points(){
        //sort list descending order
        league.sort(Collections.reverseOrder());
        //create observable list
        ObservableList<FootballClub>club= FXCollections.observableArrayList();
        for (FootballClub footballClub: league){
            //add element to observable list
            club.addAll(footballClub);
        }
        //return list
        return club;
    }


    public ObservableList<FootballClub> scoreGoals() {
        //create  observable list
        ObservableList <FootballClub> goalsScoredList=FXCollections.observableArrayList();
        //sort list descending order
        league.sort(Collections.reverseOrder(Comparator.comparing(FootballClub::getNumberOfGoalsScored)));
        for (FootballClub club:league){
            //add element to observable list
            goalsScoredList.addAll(club);
        }
        //return list
        return goalsScoredList;
    }

    public ObservableList<FootballClub> biggestWin() {
        //create observable list
        ObservableList <FootballClub> largestWinsList=FXCollections.observableArrayList();
        //sort list descending order
        league.sort(Collections.reverseOrder(Comparator.comparing(FootballClub::getNumberOfWins)));
        for (FootballClub club:league){
            //add element to observable list
            largestWinsList.addAll(club);
        }
        //return list
        return largestWinsList;
    }

    @Override
    //according date in observable list method
    public ObservableList<PlayMatch> accordingDate() {
        //create observable list
        ObservableList <PlayMatch> accordingToDateList=FXCollections.observableArrayList();
        //sort list descending
        Collections.sort(matches);
        for (PlayMatch playMatch : matches){
            //add elements to observable list
            accordingToDateList.addAll(playMatch);
        }
        //return list
        return accordingToDateList;
    }


    public ObservableList<PlayMatch> pastMatches(){
        //sort the list descending
        Collections.sort(matches);
        //create observable list
        ObservableList<PlayMatch> playMatchPlay =FXCollections.observableArrayList();
        for (PlayMatch playMatch : matches){
            //add elements to observable list
            playMatchPlay.addAll(playMatch);
        }
        //return list
        return playMatchPlay;
    }

    public ObservableList<PlayMatch> dateOfMatch(Date date){
        //create observable list
        ObservableList<PlayMatch> search = FXCollections.observableArrayList();
        for (PlayMatch playMatch : matches){
            if (playMatch.getDate().equals(date)){
                //add elements to observable list
                search.addAll(playMatch);
            }
        }
        //return search date
        return search;
    }


}
