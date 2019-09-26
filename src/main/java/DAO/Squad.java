package DAO;

public class Squad {
    //Classe para os esquadrões do exército

    private String name;
    private String specialty;
    private int quantityOfSoldiers;

    public Squad() {
    }

    public Squad(String name, String specialty, int quantityOfSoldiers) {
        this.name = name;
        this.specialty = specialty;
        this.quantityOfSoldiers = quantityOfSoldiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getQuantityOfSoldiers() {
        return quantityOfSoldiers;
    }

    public void setQuantityOfSoldiers(int quantityOfSoldiers) {
        this.quantityOfSoldiers = quantityOfSoldiers;
    }
}
