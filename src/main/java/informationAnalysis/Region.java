package informationAnalysis;

public class Region {
    private String name;
    private boolean protectedArea;
    private String squadResponsable;

    public Region(String name, boolean protectedArea, String squadResponsable) {
        this.name = name;
        this.protectedArea = protectedArea;
        this.squadResponsable = squadResponsable;
    }


    public Region() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProtectedArea() {
        return protectedArea;
    }



    public void setProtectedArea(boolean protectedArea) {
        this.protectedArea = protectedArea;
    }

    public String getSquadResponsable() {
        return squadResponsable;
    }

    public void setSquadResponsable(String squadResponsable) {
        this.squadResponsable = squadResponsable;
    }
}
