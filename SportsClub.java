/* *****************************************************************************
 *  Name:    Chamith Wanniarachchi
 *  Student ID:   2019728 w1790180
 *
 *  Description:  SportClub subclass
 *
 **************************************************************************** */

import java.io.Serializable;

public abstract class SportsClub implements Serializable {

    //Initialize subclass variables
    private String name;
    private String location;

    //create constructor

    public SportsClub(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public SportsClub() {

    }

    //create getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //create toSting method
    @Override
    public String toString() {
        return " SportsClub {" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                "} ";
    }
}
