package com.marvelapi.web.model;

import java.util.Objects;

/**
 * Thumbnail
 *
 */
public class Thumbnail {

    private String path = null;

    private String extension = null;

    public Thumbnail path(String path) {

        this.path = path;
        return this;
    }

    /**
     * The directory path of to the Thumbnail.
     *
     * @return path
     **/
    public String getPath() {

        return path;
    }

    public void setPath(String path) {

        this.path = path;
    }

    public Thumbnail extension(String extension) {

        this.extension = extension;
        return this;
    }

    /**
     * The file extension for the Thumbnail.
     *
     * @return extension
     **/
    public String getExtension() {

        return extension;
    }

    public void setExtension(String extension) {

        this.extension = extension;
    }

    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Thumbnail Thumbnail = (Thumbnail) o;
        return Objects.equals(this.path, Thumbnail.path) &&
                Objects.equals(this.extension, Thumbnail.extension);
    }

    @Override
    public int hashCode() {

        return Objects.hash(path, extension);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class Thumbnail {\n");

        sb.append("    path: ").append(toIndentedString(path)).append("\n");
        sb.append("    extension: ").append(toIndentedString(extension)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {

        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
