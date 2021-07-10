/* *****************************************************************************
 *  Name:    Chamith Wanniarachchi
 *  Student ID:   2019728 w1790180
 *
 *  Description:  FootballClub class
 *
 **************************************************************************** */

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Serializable, Comparable<FootballClub> {

    //Initialize variables
    private int numberOfWins;
    private int numberOfLoss;
    private int numberOfDraws;
    private int numberOfMatchesPlayed;
    private int numberOfGoalsScored;
    private int numberOfGoalsReceived;
    private int NumberOfPoints;

    //create constructor

    public FootballClub(int numberOfWins, int numberOfLoss, int numberOfDraws, int numberOfMatchesPlayed,
                        int numberOfGoalsScored, int numberOfGoalsReceived, int NumberOfPoints) {
        super();
        this.numberOfWins = numberOfWins;
        this.numberOfLoss = numberOfLoss;
        this.numberOfDraws = numberOfDraws;
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
        this.numberOfGoalsScored = numberOfGoalsScored;
        this.numberOfGoalsReceived = numberOfGoalsReceived;
        this.NumberOfPoints = NumberOfPoints;
    }

    public FootballClub() {
    }

    //create getters and setters
    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfLoss() {
        return numberOfLoss;
    }

    public void setNumberOfLoss(int numberOfLoss) {
        this.numberOfLoss = numberOfLoss;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public int getNumberOfMatchesPlayed() {
        return numberOfMatchesPlayed;
    }

    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed) {
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
    }

    public int getNumberOfGoalsScored() {
        return numberOfGoalsScored;
    }

    public void setNumberOfGoalsScored(int numberOfGoalsScored) {
        this.numberOfGoalsScored = numberOfGoalsScored;
    }

    public int getNumberOfGoalsReceived() {
        return numberOfGoalsReceived;
    }

    public void setNumberOfGoalsReceived(int numberOfGoalsReceived) {
        this.numberOfGoalsReceived = numberOfGoalsReceived;
    }

    public int getNumberOfPoints() {
        return NumberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.NumberOfPoints = numberOfPoints;
    }

    //create toString method
    @Override
    public String toString() {
        return  "FootballClub { " + super.toString()+
                "Number Of Wins=" + numberOfWins +
                ", Number Of Losses=" + numberOfLoss +
                ", Number Of Draws=" + numberOfDraws +
                ", Number Of Matches Played=" + numberOfMatchesPlayed +
                ", Number Of Goals Scored=" + numberOfGoalsScored +
                ", Number Of Goals Received=" + numberOfGoalsReceived +
                ", Number Of Points=" + NumberOfPoints +
                " } ";
    }
    //create hashcode method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), NumberOfPoints);
    }

    @Override
    public int compareTo(FootballClub o) {
        //comparing with points
        if (o.NumberOfPoints >this.getNumberOfPoints()){//check points
            return -1;
        }else if (o.NumberOfPoints <this.getNumberOfPoints()){
            return 1;
        }else{
            //compare in goals
            if ((o.getNumberOfGoalsScored()-o.getNumberOfGoalsReceived())>(this.getNumberOfGoalsScored()-this.getNumberOfGoalsReceived())){
                return -1;
            }else if ((o.getNumberOfGoalsScored())-o.getNumberOfGoalsReceived()<(this.getNumberOfGoalsScored()-this.getNumberOfGoalsReceived())){
                return 1;
            }else {
                return 0;
            }
        }
    }
}
