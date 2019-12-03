package models;

public class Region {
    private int id;
    private String name;
    private boolean protectedArea;
    private int squadResponsable;
    private String environmentalProtection;
    private String urbanRegion;


    /**
     * Construtor da Classe
     *
     */
    public Region() {
    }


    /**
     * Construtor da Classe sem parâmetros
     *
     * @param id id da reigao
     * @param name nome da regiao
     * @param protectedArea se a área é protegida ou nao
     * @param squadResponsable o esquadrão responsável pela região
     * @param environmentalProtection se é uma area de protecaoAmbiental
     * @param urbanRegion se é uma região urbana
     */
    public Region(int id, String name, boolean protectedArea, int squadResponsable, String environmentalProtection, String urbanRegion) {
        this.id = id;
        this.name = name;
        this.protectedArea = protectedArea;
        this.squadResponsable = squadResponsable;
        this.environmentalProtection = environmentalProtection;
        this.urbanRegion = urbanRegion;
    }

    /**
     * Getter da id
     *
     * @return retorna o id da região
     */
    public int getId() {
        return this.id;
    }

    /**
     * Setter da id
     *
     * @param id o id da regiao
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
     * @param name o nome da região
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter do protectedArea
     *
     * @return
     */
    public boolean getProtectedArea() {
        return protectedArea;
    }


    /**
     * Setter do protectedArea
     *
     * @param protectedArea se a área é protegida ou não
     */
    public void setProtectedArea(boolean protectedArea) {
        this.protectedArea = protectedArea;
    }

    /**
     * Getter do squadResponsable
     *
     * @return
     */
    public int getSquadResponsable() {
        return squadResponsable;
    }


    /**
     * Setter do squadResponsable
     *
     * @param squadResponsable o esquadrão responsável pela região
     */
    public void setSquadResponsable(int squadResponsable) {
        this.squadResponsable = squadResponsable;
    }

    /**
     * Getter protecaoAmbiente
     *
     * @return
     */
    public String getEnvironmentalProtection() {
        return environmentalProtection;
    }

    /**
     * Setter protecaoAmbiente
     *
     * @param environmentalProtection
     */
    public void setEnvironmentalProtection(String environmentalProtection) {
        this.environmentalProtection = environmentalProtection;
    }

    /**
     * Getter regioesUrbana
     *
     * @return
     */
    public String getUrbanRegion() {
        return urbanRegion;
    }

    /**
     * Setter regioesUrbana
     *
     * @param urbanRegion
     */
    public void setUrbanRegion(String urbanRegion) {
        this.urbanRegion = urbanRegion;
    }
}
