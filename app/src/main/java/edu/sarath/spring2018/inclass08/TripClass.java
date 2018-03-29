package edu.sarath.spring2018.inclass08;

import java.util.List;

/**
 * Created by sarathreznov on 3/26/2018.
 */

public class TripClass {

    private String title;
    List<points> points;

    public TripClass(String title, List<edu.sarath.spring2018.inclass08.points> points) {
        this.title = title;
        this.points = points;
    }

    @Override
    public String toString() {
        return "TripClass{" +
                "title='" + title + '\'' +
                ", points=" + points +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<edu.sarath.spring2018.inclass08.points> getPoints() {
        return points;
    }

    public void setPoints(List<edu.sarath.spring2018.inclass08.points> points) {
        this.points = points;
    }
}
