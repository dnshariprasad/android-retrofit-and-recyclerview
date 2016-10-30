package com.retrofitandrecyclerview;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hari Prasad on 10/30/16.
 */

public class ContactModel {
    private static final String TAG = "ContactModel";

    class Phone {
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("home")
        @Expose
        private String home;
        @SerializedName("office")
        @Expose
        private String office;

        /**
         * @return The mobile
         */
        public String getMobile() {
            return mobile;
        }

        /**
         * @param mobile The mobile
         */
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        /**
         * @return The home
         */
        public String getHome() {
            return home;
        }

        /**
         * @param home The home
         */
        public void setHome(String home) {
            this.home = home;
        }

        /**
         * @return The office
         */
        public String getOffice() {
            return office;
        }

        /**
         * @param office The office
         */
        public void setOffice(String office) {
            this.office = office;
        }
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private Phone phone;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return The phone
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public static ContactModel fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, ContactModel.class);
    }

    public JSONObject toJson() {
        String jsonRepresentation = new Gson().toJson(this, ContactModel.class);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonRepresentation);
        } catch (JSONException e) {
            Log.e(TAG, "toJson: " + e.getLocalizedMessage());
        }
        return jsonObject;
    }
}


