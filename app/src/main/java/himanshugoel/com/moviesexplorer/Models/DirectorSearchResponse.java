package himanshugoel.com.moviesexplorer.Models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "unit",
        "show_id",
        "show_title",
        "release_year",
        "rating",
        "category",
        "show_cast",
        "director",
        "summary",
        "poster",
        "mediatype",
        "runtime"
})
public class DirectorSearchResponse implements Serializable {

    @JsonProperty("unit")
    private Integer unit;
    @JsonProperty("show_id")
    private Integer showId;
    @JsonProperty("show_title")
    private String showTitle;
    @JsonProperty("release_year")
    private String releaseYear;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("category")
    private String category;
    @JsonProperty("show_cast")
    private String showCast;
    @JsonProperty("director")
    private String director;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("poster")
    private String poster;
    @JsonProperty("mediatype")
    private Integer mediatype;
    @JsonProperty("runtime")
    private String runtime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("unit")
    public Integer getUnit() {
        return unit;
    }

    @JsonProperty("unit")
    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @JsonProperty("show_id")
    public Integer getShowId() {
        return showId;
    }

    @JsonProperty("show_id")
    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    @JsonProperty("show_title")
    public String getShowTitle() {
        return showTitle;
    }

    @JsonProperty("show_title")
    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    @JsonProperty("release_year")
    public String getReleaseYear() {
        return releaseYear;
    }

    @JsonProperty("release_year")
    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @JsonProperty("rating")
    public String getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(String rating) {
        this.rating = rating;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("show_cast")
    public String getShowCast() {
        return showCast;
    }

    @JsonProperty("show_cast")
    public void setShowCast(String showCast) {
        this.showCast = showCast;
    }

    @JsonProperty("director")
    public String getDirector() {
        return director;
    }

    @JsonProperty("director")
    public void setDirector(String director) {
        this.director = director;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("poster")
    public String getPoster() {
        return poster;
    }

    @JsonProperty("poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    @JsonProperty("mediatype")
    public Integer getMediatype() {
        return mediatype;
    }

    @JsonProperty("mediatype")
    public void setMediatype(Integer mediatype) {
        this.mediatype = mediatype;
    }

    @JsonProperty("runtime")
    public String getRuntime() {
        return runtime;
    }

    @JsonProperty("runtime")
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
