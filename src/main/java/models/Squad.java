package models;

public class Squad {
    private int id;
    private String name;
    private String regionResponsable;
    private int quantityOfSoldiers;

    /**
     * Construtor da classe
     *
     */
    public Squad() {
    }


    /**
     * Construtor com parâmetros
     *
     * @param id
     * @param name
     * @param regionResponsable
     * @param quantityOfSoldiers
     */
    public Squad(int id, String name, String regionResponsable, int quantityOfSoldiers) {
        this.id = id;
        this.name = name;
        this.regionResponsable = regionResponsable;
        this.quantityOfSoldiers = quantityOfSoldiers;
    }


    /**
     * Getter do Id
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Setter do Id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Getter do name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter do name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter do regionResponsable
     *
     * @return
     */
    public String getRegionResponsable() {
        return regionResponsable;
    }


    /**
     * Setter do RegionResponsable
     *
     * @param regionResponsable
     */
    public void setRegionResponsable(String regionResponsable) {
        this.regionResponsable = regionResponsable;
    }


    /**
     * Getter do QuantityOfSoldiers
     *
     * @return
     */
    public int getQuantityOfSoldiers() {
        return quantityOfSoldiers;
    }


    /**
     * Setter do QuantityOfSoldiers
     *
     * @param quantityOfSoldiers
     */
    public void setQuantityOfSoldiers(int quantityOfSoldiers) {
        this.quantityOfSoldiers = quantityOfSoldiers;
    }
}
