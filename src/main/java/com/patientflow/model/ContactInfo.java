package com.patientflow.model;

public class ContactInfo {
    private String whatsappNumber;
    private String email;
    private String city;

    public ContactInfo(String whatsappNumber, String email, String city) {
        this.whatsappNumber = whatsappNumber;
        this.email = email;
        this.city = city;
    }

    public String getWhatsappNumber() { return whatsappNumber; }
    public String getEmail() { return email; }
    public String getCity() { return city; }

    public void setWhatsappNumber(String whatsappNumber) { this.whatsappNumber = whatsappNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setCity(String city) { this.city = city; }
}
