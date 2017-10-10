package com.marvelapi.web.model;


/**
 * Character on asteroids to replace by Character from Marvel APIs model
 *
 */
public class CharacterPower {

    private Integer id;

    private String name;

    private String description;

    private String Power;

    /**
     * @param id
     * @param name
     * @param description
     * @param power
     */
    public CharacterPower(Integer id, String name, String description, String power) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        Power = power;
    }

    /**
     * @return the id
     */
    public Integer getId() {

        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {

        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {

        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * @return the power
     */
    public String getPower() {

        return Power;
    }

    /**
     * @param power
     *            the power to set
     */
    public void setPower(String power) {

        Power = power;
    }

}
