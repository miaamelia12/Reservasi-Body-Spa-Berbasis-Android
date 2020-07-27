package pnj.uas.fahmia_amelia.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Requests implements Serializable {
    private String name;
    private String treatment;
    private String date_book;
    private String address;
    private String tlp;
    private String email;

    private String key;

    public Requests() {

    }

    public Requests(String name, String treatment, String date_book, String address, String tlp, String email) {
        this.name = name;
        this.treatment = treatment;
        this.date_book = date_book;
        this.address = address;
        this.tlp = tlp;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDate_book() {
        return date_book;
    }

    public void setDate_book(String date_book) {
        this.date_book = date_book;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NonNull
    @Override
    public String toString() {
        return " "+name+"\n" +
                " "+treatment+"\n" +
                " "+date_book+"\n" +
                " "+address+"\n" +
                " "+tlp+"\n" +
                " "+email;
    }
}
