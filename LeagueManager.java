/* *****************************************************************************
 *  Name:    Chamith Wanniarachchi
 *  Student ID:   2019728 w1790180
 *
 *  Description: LeagueManager interface class
 *
 **************************************************************************** */

import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Date;

public interface LeagueManager {

    //void method
    void addClub(FootballClub footballClub);
    void deleteClub(String name, String clubLocation);
    void printClubList();
    void addMatch();
    void displayStatistics(String name);
    void displayLeagueTable();
    void saveData();
    void loadData() throws IOException;
    void randomMatch() throws IOException;
    //observable lists
    ObservableList<FootballClub> scoreGoals();
    ObservableList<FootballClub> biggestWin();
    ObservableList<FootballClub> points();
    ObservableList<PlayMatch> dateOfMatch(Date date);
    ObservableList<PlayMatch> pastMatches();
    ObservableList<PlayMatch> accordingDate();

}