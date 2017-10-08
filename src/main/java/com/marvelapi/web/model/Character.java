package com.marvelapi.web.model;


/**
 * Character domain model to replace by Character from APIs model
 *
 */
public class Character {

    /**
     * @param id
     * @param name
     * @param description
     * @param thumbnail
     */
    public Character(Integer id, String name, String description, Thumbnail thumbnail, Integer popularity) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.popularity = popularity;
    }

    /**
     * @return the popularity
     */
    public Integer getPopularity() {

        return popularity;
    }

    private Integer id;

    private String name;

    private String description;

    private Thumbnail thumbnail;

    private Integer popularity;

    /**
     * @return the id
     */
    public Integer getId() {

        return id;
    }

    /**
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {

        return description;
    }

    /**
     * @return the thumbnail
     */
    public Thumbnail getThumbnail() {

        return thumbnail;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (thumbnail == null ? 0 : thumbnail.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Character other = (Character) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        }
        else if (!description.equals(other.description)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        }
        else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!name.equals(other.name)) {
            return false;
        }
        if (thumbnail == null) {
            if (other.thumbnail != null) {
                return false;
            }
        }
        else if (!thumbnail.equals(other.thumbnail)) {
            return false;
        }
        return true;
    }

}
