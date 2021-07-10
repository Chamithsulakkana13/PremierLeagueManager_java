/* *****************************************************************************
 *  Name:    Chamith Wanniarachchi
 *  Student ID:   2019728 w1790180
 *
 *  Description:  Main class console & GUI
 *
 **************************************************************************** */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main extends Application {

    private static Scanner sc =new Scanner(System.in); //create a scanner in Main
    static LeagueManager Manager = new PremierLeagueManager(); //get premierLeague data

    public static void main(String[] args) throws ParseException, IOException {

        Manager.loadData();//load file save data

        System.out.println("*******************************************************************");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("@@ Welcome to Premier league Management System @@");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("*******************************************************************");

        System.out.println("");

        Application.launch();//lunch application in Main

    }

    private static void consoleUI(){

        mainMenu:
        //loop infinity
        while (true){
            System.out.println("Press 1 to Add a club to League");
            System.out.println("Press 2 to Delete a club from League");
            System.out.println("Press 3 to Print club Statistics");
            System.out.println("Press 4 to Display club List");
            System.out.println("Press 5 to Display league Table");
            System.out.println("Press 6 to Add match details");
            System.out.println("Press 7 to open GUI");
            System.out.println("Quit Application to Press 0");
            System.out.println("Enter your selection : ");
            int selection = 0;
            boolean flag =Boolean.TRUE;
            while (flag){
                //check selection choice using try catch
                try {
                    selection= sc.nextInt();
                    //select user choice in consoleUI
                    switch (selection){
                        case 1:
                            addClub();
                            break;
                        case 2:
                            deleteClub();
                            break;
                        case 3:
                            displayStatistics();
                            break;
                        case 4:
                            Manager.printClubList();
                            break;
                        case 5:
                            Manager.displayLeagueTable();
                            break;
                        case 6:

                            Manager.addMatch();
                            break;
                        case 7:
                            openGUI();
                            break ;
                        case 0:
                            Manager.saveData(); //save user data in to file
                            System.out.println("Have a Nice Day..!");
                            break mainMenu;
                        default:
                            System.out.println("Wrong input! try again !!!"); //validation to user given wrong input
                            consoleUI();
                    }
                }catch (InputMismatchException | IOException e){
                    System.out.println("Enter your valid selection!!!...");//validation to user given wrong next
                    System.out.println("Enter again (Y/N)");
                    String next = sc.next();
                    if (next.equalsIgnoreCase("y")){


                    }else {
                        break ;// close application
                    }
                }
                flag=false;
            }
        }
    }

    private static void addClub(){
        //Football club create object
        FootballClub footballClub=new FootballClub();
        //get user data
        System.out.println("Enter Club Name : ");
        String name= sc.next();
        //set user data
        footballClub.setName(name);
        //get user data
        System.out.println("Enter Club Location : ");
        String location= sc.next();
        //set user data
        footballClub.setLocation(location);

        Manager.addClub(footballClub);

    }

    private static void deleteClub(){
        //get user data
        System.out.println("Enter Club name to Delete : ");
        String name= sc.next();
        //get user data
        System.out.println("Enter Club location to Delete : ");
        String location= sc.next();

        Manager.deleteClub(name,location);
    }

    private static void displayStatistics(){
        System.out.println("Enter Club name to Display Statics : ");
        String name= sc.next();
        Manager.displayStatistics(name);
        System.out.println("");
    }

    private static void openGUI()throws IOException{
        //Graphical user interface crate
        Stage stage = new Stage();
        Label label1 = new Label("Premier League Management System");//main title
        label1.setStyle("-fx-font-weight: bold");//label style
        TextField textField = new TextField();//crate textField to get user data
        Label dateSE=new Label("Enter date in to Search");//label to user set details


        Button search = new Button("Search Match");//search button

        search.setOnAction(e -> {
            String str=textField.getText();
            if (str==null){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter date to search");//alert to user non input data
                alert.show();
                return;
            }
            textField.setText(null);
            Date date;
            //create try catch to date format
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
                System.out.println(date);
            } catch (ParseException parseException) {
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please Enter date format yyyy-MM-dd");//alert to user non input data
                alert.show();
                return;
            }
            //table create in gui
            TableView<PlayMatch> tableView = new TableView<>();
            tableView.setMinWidth(600);
            TableColumn<PlayMatch, Integer> dateColumn = new TableColumn<>("Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            //table columns
            TableColumn<PlayMatch, String> teamOne = new TableColumn<>("Team A");
            teamOne.setMinWidth(120);
            teamOne.setStyle("-fx-alignment:CENTER;");
            teamOne.setCellValueFactory(new PropertyValueFactory<>("firstClub"));

            //table columns
            TableColumn<PlayMatch, String> teamTwo = new TableColumn<>("Team B");
            teamTwo.setMinWidth(120);
            teamOne.setStyle("-fx-alignment:CENTER;");
            teamTwo.setCellValueFactory(new PropertyValueFactory<>("secondClub"));

            //table columns
            TableColumn<PlayMatch, String> teamOneScore = new TableColumn<>("Team A Score");
            teamOneScore.setCellValueFactory(new PropertyValueFactory<>("firstClubScore"));
            teamOneScore.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<PlayMatch, String> teamTwoScore = new TableColumn<>(" Team B Score ");
            teamTwoScore.setCellValueFactory(new PropertyValueFactory<>("secondClubScore"));
            teamTwoScore.setStyle("-fx-alignment:CENTER;");

            tableView.setItems(Manager.dateOfMatch(date));//set items in League manager

            tableView.getColumns().addAll(dateColumn,teamOne,teamTwo,teamOneScore,teamTwoScore);//add columns in table

            Button close=new Button("Back to Menu");//back button
            Stage stage1=new Stage();
            close.setOnAction(e1 -> {
                //closing the stage
                stage1.close();
                stage.show();
            });
            VBox vBox=new VBox();
            Label label=new Label("Search Match Date");

            label.setLayoutY(20);//set locations
            label.setLayoutX(190);
            label.setFont(Font.font(20));

            close.setLayoutY(540);//set button location
            close.setLayoutX(470);
            vBox.setLayoutY(92);//set vBox location
            vBox.setLayoutX(40);
            vBox.setSpacing(5);
            vBox.setMaxWidth(450);
            vBox.getChildren().addAll(tableView);//set Tableview location
            Scene scene=new Scene(new Group());
            ((Group) scene.getRoot()).getChildren().addAll(label,vBox,close);
            stage1.setTitle("Search Match Date");//set Title
            stage1.setScene(scene);
            stage1.setWidth(700);//set stage location
            stage1.setHeight(650);
            stage1.showAndWait();//show stage


        });
        Button random = new Button("Random match ");//create random Match button

        random.setOnAction(e -> {
            try {
                Manager.randomMatch();
            } catch (IOException ioException) {
                System.out.println("something went wrong!");//try catch to get random match
            }
        });
        Button matchPlayed = new Button("Display Matches Play");//played matches button

        matchPlayed.setOnAction(e -> {
            Stage stage1 = new Stage();
            stage1.setTitle("League Points Table");

            //create tableView in gui
            TableView<PlayMatch> matchTableView=new TableView<>();
            matchTableView.setMinWidth(670);

            //table columns
            TableColumn<PlayMatch, Integer> dateColumn = new TableColumn<>("Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            //table columns
            TableColumn<PlayMatch, Integer> team1 = new TableColumn<>("Team One");
            team1.setCellValueFactory(new PropertyValueFactory<>("firstClub"));
            team1.setMinWidth(140);
            team1.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<PlayMatch, Integer> team2 = new TableColumn<>("Team Two");
            team2.setCellValueFactory(new PropertyValueFactory<>("secondClub"));
            team2.setMinWidth(140);
            team2.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<PlayMatch, Integer> team1Score = new TableColumn<>("Team One Score");
            team1Score.setCellValueFactory(new PropertyValueFactory<>("firstClubScore"));
            team1Score.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<PlayMatch, Integer> team2Score = new TableColumn<>("Team Two Score");
            team2Score.setCellValueFactory(new PropertyValueFactory<>("secondClubScore"));
            team2Score.setStyle("-fx-alignment:CENTER;");

            matchTableView.setItems(Manager.pastMatches());//set items in league manager

            matchTableView.getColumns().addAll(dateColumn,team1,team2,team1Score,team2Score);//add columns in Table

            Button close = new Button("Back to Menu");//back button
            close.setOnAction(e1 -> {
                stage1.close();
                stage.show();
            });
            Label label=new Label("Past Matches");//past matches show label

            label.setLayoutY(20);//label location
            label.setLayoutX(280);
            label.setFont(Font.font(20));

            close.setLayoutY(540);//button location
            close.setLayoutX(470);

            VBox vbox = new VBox();
            vbox.setLayoutY(92);//vBox location
            vbox.setLayoutX(40);
            vbox.setSpacing(5);
            vbox.setMaxWidth(450);

            vbox.getChildren().addAll(matchTableView);//add Table in to vBox

            stage1.setWidth(780);//stage location
            stage1.setHeight(650);
            Scene scene = new Scene(new Group());

            ((Group) scene.getRoot()).getChildren().addAll(label,vbox,close);
            stage1.setScene(scene);//set scene in to Stage
            stage1.showAndWait();//show stage
        });
        Button pointsButton=new Button("League Points Table");//point table show button

        pointsButton.setOnAction(e -> {
            Stage goalsStage=new Stage();
            goalsStage.setTitle("League Points Table");

            //create tableView in gui
            TableView<FootballClub> tableMatch =new TableView<>();
            tableMatch.setMinWidth(892);

            //table columns
            TableColumn<FootballClub, String> name = new TableColumn<>("Club name");
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            name.setMinWidth(140);
            name.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> location = new TableColumn<>("Location");
            location.setCellValueFactory(new PropertyValueFactory<>("location"));
            location.setMinWidth(140);
            location.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> matchNo = new TableColumn<>("Match NO:");
            matchNo.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
            matchNo.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> wins = new TableColumn<>("Wins");
            wins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
            wins.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> loss = new TableColumn<>("Losses");
            loss.setCellValueFactory(new PropertyValueFactory<>("numberOfLoss"));
            loss.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> draws = new TableColumn<>("Draws");
            draws.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));
            draws.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> goalsScore = new TableColumn<>("Score Goals");
            goalsScore.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScored"));
            goalsScore.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> goalsReceived = new TableColumn<>("Received Goals");
            goalsReceived.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsReceived"));
            goalsReceived.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> points = new TableColumn<>("Points ");
            points.setCellValueFactory(new PropertyValueFactory<>("numberOfPoints"));
            points.setStyle("-fx-alignment:CENTER;");

            tableMatch.setItems(Manager.points()); //set items in to League Manager
            tableMatch.getColumns().addAll(name,location,matchNo,wins,loss,draws,goalsScore,goalsReceived,points);//add columns in Table

            Button close=new Button("Back to Menu");//back button
            close.setOnAction(e1 -> {
                goalsStage.close();
                stage.show();
            });
            Label label=new Label("League Points Table");

            label.setLayoutY(20);//label location
            label.setLayoutX(400);
            label.setFont(Font.font(20));

            close.setLayoutY(540);//button location
            close.setLayoutX(670);

            final VBox vbox = new VBox();
            vbox.setLayoutY(92);//vBox location
            vbox.setLayoutX(40);
            vbox.setSpacing(5);
            vbox.setMaxWidth(892);

            vbox.getChildren().addAll(tableMatch);//add table in to vBox

            goalsStage.setWidth(990);
            goalsStage.setHeight(650);
            Scene scene = new Scene(new Group());
            ((Group) scene.getRoot()).getChildren().addAll(label,vbox,close);
            goalsStage.setScene(scene);//set scene in Stage
            goalsStage.showAndWait();//show Stage
        });

        SplitMenuButton splitMenuButton=new SplitMenuButton();//create SplitMenu
        splitMenuButton.setText("Point table Sort");//splitMenu Text

        MenuItem scoreGoals=new MenuItem("Score Goals                    ");//menuItems

        scoreGoals.setOnAction(e -> {
            Stage stage1 =new Stage();
            stage1.setTitle("Sort Goals Scored");

            //create Tableview in gui
            TableView<FootballClub> matchTableView=new TableView<>();
            matchTableView.setMinWidth(892);
            //table columns
            TableColumn<FootballClub, String> name = new TableColumn<>("Club name");
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            name.setMinWidth(140);
            name.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> location = new TableColumn<>("Location");
            location.setCellValueFactory(new PropertyValueFactory<>("location"));
            location.setMinWidth(140);
            location.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> matchNo = new TableColumn<>("Match NO:");
            matchNo.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
            matchNo.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> wins = new TableColumn<>("Wins");
            wins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
            wins.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> loss = new TableColumn<>("Losses");
            loss.setCellValueFactory(new PropertyValueFactory<>("numberOfLoss"));
            loss.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> draws = new TableColumn<>("Draws");
            draws.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));
            draws.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> goalsScore = new TableColumn<>("Score Goals");
            goalsScore.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScored"));
            goalsScore.setStyle("-fx-alignment:CENTER;");
            goalsScore.setStyle("-fx-background-color: yellow;");

            //table columns
            TableColumn<FootballClub, Integer> goalsReceived = new TableColumn<>("Received Goals");
            goalsReceived.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsReceived"));
            goalsReceived.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> points = new TableColumn<>("Points ");
            points.setCellValueFactory(new PropertyValueFactory<>("numberOfPoints"));
            points.setStyle("-fx-alignment:CENTER;");

            matchTableView.setItems(Manager.scoreGoals());//set Items  in to League Manager
            matchTableView.getColumns().addAll(name,location,matchNo,wins,loss,draws,goalsScore,goalsReceived,points);//add columns in table

            Button close=new Button("Back to Menu");//back button
            close.setOnAction(e1 -> {
                stage1.close();
                stage.show();
            });
            Label label=new Label("League Points Table");

            label.setLayoutY(20);//label location
            label.setLayoutX(400);
            label.setFont(Font.font(20));
            close.setLayoutY(540);//button location
            close.setLayoutX(670);
            close.setPrefHeight(35);

            final VBox vbox = new VBox();
            vbox.setLayoutY(92);//vBox location
            vbox.setLayoutX(40);
            vbox.setSpacing(5);
            vbox.setMaxWidth(892);

            vbox.getChildren().addAll(matchTableView);

            stage1.setWidth(990);
            stage1.setHeight(650);
            Scene scene = new Scene(new Group());

            ((Group) scene.getRoot()).getChildren().addAll(label,vbox,close);
            stage1.setScene(scene);//set scene in Stage
            stage1.showAndWait();//show Stage

        });

        MenuItem biggestWin=new MenuItem("Biggest Wins");//second menu Item

        biggestWin.setOnAction(e -> {
            Stage stage1 = new Stage();

            stage1.setTitle("Sort Biggest Wins");
            //create TableView in gui
            TableView<FootballClub> tableView =new TableView<>();
            tableView.setMinWidth(892);
            //table columns
            TableColumn<FootballClub, String> name = new TableColumn<>("Club name");
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            name.setMinWidth(140);
            name.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> location = new TableColumn<>("Location");
            location.setCellValueFactory(new PropertyValueFactory<>("location"));
            location.setMinWidth(140);
            location.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> matchNo = new TableColumn<>("Match NO:");
            matchNo.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
            matchNo.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> wins = new TableColumn<>("Wins");
            wins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
            wins.setStyle("-fx-alignment:CENTER;");
            wins.setStyle("-fx-background-color: yellow;");

            //table columns
            TableColumn<FootballClub, Integer> loss = new TableColumn<>("Losses");
            loss.setCellValueFactory(new PropertyValueFactory<>("numberOfLoss"));
            loss.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> draws = new TableColumn<>("Draws");
            draws.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));
            draws.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> goalsScore = new TableColumn<>("Score Goals");
            goalsScore.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScored"));
            goalsScore.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> goalsReceived = new TableColumn<>("Received Goals");
            goalsReceived.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsReceived"));
            goalsReceived.setStyle("-fx-alignment:CENTER;");

            //table columns
            TableColumn<FootballClub, Integer> points = new TableColumn<>("Points ");
            points.setCellValueFactory(new PropertyValueFactory<>("numberOfPoints"));
            points.setStyle("-fx-alignment:CENTER;");

            tableView.setItems(Manager.biggestWin());//set  items in League Manager

            tableView.getColumns().addAll(name,location,matchNo,wins,loss,draws,goalsScore,goalsReceived,points);//add columns in Table

            Button close =new Button("Back to main GUI");//back button
            close.setOnAction(e1 -> {
                stage1.close();
                stage.show();
            });

            Label label =new Label("League Points Table");

            label.setLayoutY(20);//label location
            label.setLayoutX(400);
            label.setFont(Font.font(20));
            close.setLayoutY(540);//button location
            close.setLayoutX(670);

            close.setPrefHeight(35);
            final VBox vbox = new VBox();
            vbox.setLayoutY(92);//vBox location
            vbox.setLayoutX(40);
            vbox.setSpacing(5);
            vbox.setMaxWidth(892);

            vbox.getChildren().addAll(tableView);

            stage1.setWidth(990);//stage location
            stage1.setHeight(650);
            Scene scene = new Scene(new Group());
            ((Group) scene.getRoot()).getChildren().addAll(label,vbox,close);
            stage1.setScene(scene);//set scene in Stage
            stage1.showAndWait();//show stage
        });
        splitMenuButton.getItems().addAll(scoreGoals,biggestWin);//add menuItems in splitMenu

        Button backToMenu = new Button("Back to Menu");//back button
        dateSE.setLayoutY(217);//label location
        dateSE.setLayoutX(161);

        label1.setLayoutX(180);//label location
        label1.setLayoutY(38);
        label1.setFont(Font.font(25));
        label1.setStyle("-fx-text-fill: red;");

        search.setLayoutX(161);//button location
        search.setLayoutY(309);
        search.setPrefSize(155,34);//button size

        random.setLayoutX(432);//random button location
        random.setLayoutY(138);
        random.setPrefSize(155,34);//random button size

        matchPlayed.setLayoutX(161);//played match button location
        matchPlayed.setLayoutY(138);
        matchPlayed.setPrefSize(165,34);//played match button size

        pointsButton.setLayoutX(432);//point button location
        pointsButton.setLayoutY(217);
        pointsButton.setPrefSize(155,34);//point button size

        backToMenu.setLayoutX(550);//back button location
        backToMenu.setLayoutY(462);
        backToMenu.setPrefSize(120,35);//back button size

        textField.setLayoutX(161);//textField location
        textField.setLayoutY(240);
        textField.setPromptText("yyyy-MM-DD");//prompt Text set

        splitMenuButton.setLayoutX(432);//split Menu location
        splitMenuButton.setLayoutY(309);
        splitMenuButton.setPrefSize(155,34);//splitMenu size

        AnchorPane anchorPane = new AnchorPane();//create anchorPane to show items

        anchorPane.getChildren().addAll(label1,backToMenu,search,random,matchPlayed,dateSE,pointsButton,textField,splitMenuButton);//add items

        Scene scene = new Scene(anchorPane, 737, 550);//new scene
        stage.setScene(scene);//set scene
        backToMenu.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> stage.close());
        stage.setTitle("Premier league management system");

        stage.setResizable(false);//resizable false

        stage.showAndWait();//show stage


    }

    @Override
    public void start(Stage primaryStage) throws Exception {//console UI show in start
        consoleUI();
    }
}
