package com.patientflow.model;

/**
 * ContactInfo class to hold contact details of a person.
 * Includes WhatsApp number, email, and city.
 * It is used by both Lead and Patient classes.
 *
 * We did this to avoid code duplication and to increase reusability.
 */
public class ContactInfo {
    private String whatsappNumber; //Whatsapp number with country code, we used String to accommodate different formats
    private String email;
    private String city;

    // Constructor to make a ContactInfo object
    public ContactInfo(String whatsappNumber, String email, String city) {
        this.whatsappNumber = whatsappNumber;
        this.email = email;
        this.city = city;
    }

    // Getters
    public String getWhatsappNumber() { return whatsappNumber; }
    public String getEmail() { return email; }
    public String getCity() { return city; }

    // Setters
    public void setWhatsappNumber(String whatsappNumber) { this.whatsappNumber = whatsappNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setCity(String city) { this.city = city; }
}
