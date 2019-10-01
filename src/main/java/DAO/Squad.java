package DAO;

public class Squad {
    //Classe para os esquadrões do exército

    private String name;
    private String regionResponsable;
    private int quantityOfSoldiers;

    public Squad() {
    }

    public Squad(String name, String specialty, int quantityOfSoldiers) {
        this.name = name;
        this.regionResponsable = specialty;
        this.quantityOfSoldiers = quantityOfSoldiers;
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
