package models;

public class Squad {
    private int id;
    private String name;
    private String regionResponsable;
    private int quantityOfSoldiers;

    public Squad() {
    }

    public Squad(int id, String name, String regionResponsable, int quantityOfSoldiers) {
        this.id = id;
        this.name = name;
        this.regionResponsable = regionResponsable;
        this.quantityOfSoldiers = quantityOfSoldiers;
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

    public String getRegionResponsable() {
        return regionResponsable;
    }

    public void setRegionResponsable(String regionResponsable) {
        this.regionResponsable = regionResponsable;
    }

    public int getQuantityOfSoldiers() {
        return quantityOfSoldiers;
    }

    public void setQuantityOfSoldiers(int quantityOfSoldiers) {
        this.quantityOfSoldiers = quantityOfSoldiers;
    }
}
