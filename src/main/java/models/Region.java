package models;

public class Region {
    private int id;
    private String name;
    private boolean protectedArea;
    private int squadResponsable;


    public Region() {
    }

    public Region(int id, String name, boolean protectedArea, int squadResponsable) {
        this.id = id;
        this.name = name;
        this.protectedArea = protectedArea;
        this.squadResponsable = squadResponsable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getProtectedArea() {
        return protectedArea;
    }


    public void setProtectedArea(boolean protectedArea) {
        this.protectedArea = protectedArea;
    }

    public int getSquadResponsable() {
        return squadResponsable;
    }



    public void setSquadResponsable(int squadResponsable) {
        this.squadResponsable = squadResponsable;
    }
}
