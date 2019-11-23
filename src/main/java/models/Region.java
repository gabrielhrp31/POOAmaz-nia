package models;

public class Region {
    private int id;
    private String name;
    private boolean protectedArea;
    private int squadResponsable;


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
     */
    public Region(int id, String name, boolean protectedArea, int squadResponsable) {
        this.id = id;
        this.name = name;
        this.protectedArea = protectedArea;
        this.squadResponsable = squadResponsable;
    }

    /**
     * Getter da id
     *
     * @return
     */
    public int getId() {
        return id;
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
}
