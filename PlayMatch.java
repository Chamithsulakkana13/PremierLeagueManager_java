/* *****************************************************************************
 *  Name:    Chamith Wanniarachchi
 *  Student ID:   2019728 w1790180
 *
 *  Description:  PlayMatch add match details
 *
 **************************************************************************** */

import java.io.Serializable;
import java.util.Date;

public class PlayMatch implements Serializable,Comparable<PlayMatch> {

    //Initialize variables
    private String firstClub;
    private String secondClub;
    private int firstClubScore;
    private int secondClubScore;
    private Date date;

    //create match constructor
    public PlayMatch(String firstClub, String secondClub, int firstClubScore, int secondClubScore, Date date) {
        this.firstClub = firstClub;
        this.secondClub = secondClub;
        this.firstClubScore = firstClubScore;
        this.secondClubScore = secondClubScore;
        this.date=date;
    }

    public PlayMatch() {

    }

    //create getters and setters
    public String getFirstClub() {
        return firstClub;
    }

    public void setFirstClub(String firstClub) {
        this.firstClub = firstClub;
    }

    public String getSecondClub() {
        return secondClub;
    }

    public void setSecondClub(String secondClub) {
        this.secondClub = secondClub;
    }

    public int getFirstClubScore() {
        return firstClubScore;
    }

    public void setFirstClubScore(int firstClubScore) {
        this.firstClubScore = firstClubScore;
    }

    public int getSecondClubScore() {
        return secondClubScore;
    }

    public void setSecondClubScore(int secondClubScore) {
        this.secondClubScore = secondClubScore;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date=date;
    }

    //create compareTo method compare dates
    @Override
    public int compareTo(PlayMatch clubOne) {
        return this.date.compareTo(clubOne.getDate());
    }

}
