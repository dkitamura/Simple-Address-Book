package ca.kitamura.simpleaddressbook.models.randomuser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Darren on 2016-12-13.
 */


public class Result implements Serializable{

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("cell")
    @Expose
    private String cell;
    @SerializedName("id")
    @Expose
    private Id id;
    @SerializedName("picture")
    @Expose
    private Picture picture;

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The name
     */
    public Name getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     * The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }


    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The cell
     */
    public String getCell() {
        return cell;
    }

    /**
     *
     * @param cell
     * The cell
     */
    public void setCell(String cell) {
        this.cell = cell;
    }

    /**
     *
     * @return
     * The id
     */
    public Id getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Id id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     * The picture
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

}