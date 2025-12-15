package com.patientflow.app;

import com.patientflow.model.ContactInfo;
import com.patientflow.model.Lead;
import com.patientflow.model.LeadStatus;
import com.patientflow.repository.InMemoryLeadRepository;
import com.patientflow.scoring.DefaultLeadScoringStrategy;
import com.patientflow.service.LeadService;
import com.patientflow.repository.InMemoryPatientRepository;
import com.patientflow.service.PatientService;
import com.patientflow.model.Patient;


import java.util.Optional;
import java.util.Scanner;

public class PatientFlowApp {

    private final LeadService leadService;
    private final PatientService patientService;
    private final Scanner scanner;

    public PatientFlowApp() {
        InMemoryLeadRepository leadRepository = new InMemoryLeadRepository();
        InMemoryPatientRepository patientRepository = new InMemoryPatientRepository();
        DefaultLeadScoringStrategy scoringStrategy = new DefaultLeadScoringStrategy();
        this.leadService = new LeadService(leadRepository, scoringStrategy);
        this.scanner = new Scanner(System.in);
        this.patientService = new PatientService(patientRepository);

    }

    public static void main(String[] args) {
        new PatientFlowApp().run();
    }

    private void run() {
        System.out.println("=== PatientFlow OS – Dental Lead & Patient Management System ===");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createLeadFlow();
                case "2" -> listAllLeads();
                case "3" -> listLeadsByStatus(LeadStatus.HOT);
                case "4" -> listLeadsByStatus(LeadStatus.WARM);
                case "5" -> listLeadsByStatus(LeadStatus.COLD);
                case "6" -> dailySummary();
                case "7" -> listLeadsByCity();
                case "8" -> convertLeadToPatientFlow();
                case "9" -> listAllPatients();
                case "10" -> findPatientById();
                case "0" -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1) Create new lead");
        System.out.println("2) List all leads");
        System.out.println("3) Show HOT leads");
        System.out.println("4) Show WARM leads");
        System.out.println("5) Show COLD leads");
        System.out.println("6) Daily summary (counts)");
        System.out.println("7) List leads by city");
        System.out.println("8) Convert lead to patient");
        System.out.println("9) List all patients");
        System.out.println("10) Find patient by ID");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
    }

    private void createLeadFlow() {
        System.out.println("\n--- New Lead ---");

        String fullName = readNonEmpty("Full name: ");
        String whatsapp = readNonEmpty("WhatsApp number: ");
        System.out.print("Email (optional): ");
        String email = scanner.nextLine().trim();
        String city = readNonEmpty("City: ");

        int age = readInt("Age: ");

        System.out.print("Travel willingness (LOW / MEDIUM / HIGH): ");
        String travel = scanner.nextLine().trim();
        if (travel.isEmpty()) travel = "LOW";

        String treatmentType = readNonEmpty("Treatment type (e.g. Implants, Veneers): ");

        System.out.print("Treatment timeline (e.g. ASAP, 1-3 months): ");
        String timeline = scanner.nextLine().trim();
        if (timeline.isEmpty()) timeline = "UNKNOWN";

        double budget = readDouble("Estimated budget in EUR (e.g. 3000): ");

        ContactInfo contactInfo = new ContactInfo(whatsapp, email, city);

        Lead lead = leadService.createLead(
                fullName,
                contactInfo,
                age,
                travel,
                treatmentType,
                timeline,
                budget
        );

        System.out.println("\n✅ Lead created!");
        System.out.println("ID: " + lead.getId());
        System.out.println("Status: " + lead.getStatus());
        System.out.println("Score: " + lead.getScore());
    }

    private void listAllLeads() {
        System.out.println("\n--- All Leads ---");
        var leads = leadService.getAllLeads();

        if (leads.isEmpty()) {
            System.out.println("(no leads yet)");
            return;
        }

        leads.forEach(System.out::println);
    }

    private void listLeadsByStatus(LeadStatus status) {
        System.out.println("\n--- " + status + " Leads ---");
        var leads = leadService.getLeadsByStatus(status);

        if (leads.isEmpty()) {
            System.out.println("(none)");
            return;
        }

        leads.forEach(System.out::println);
    }

    // We keep this method helper, but it's not currently in the menu (replaced by WARM leads)
    private void findLeadById() {
        System.out.println("\n--- Find Lead by ID ---");
        long id = readLong("Enter lead ID: ");

        Optional<Lead> leadOpt = leadService.getLeadById(id);
        if (leadOpt.isPresent()) {
            System.out.println("✅ Found:");
            System.out.println(leadOpt.get());
        } else {
            System.out.println("❌ Lead not found.");
        }
    }

    // ---------- input helpers ----------

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Please enter a value.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private long readLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g. 3000).");
            }
        }
    }
    // --- NEW METHOD FOR DAILY SUMMARY ---
    private void dailySummary() {
        System.out.println("\n--- Daily Summary ---");
        int hot = leadService.getLeadsByStatus(LeadStatus.HOT).size();
        int warm = leadService.getLeadsByStatus(LeadStatus.WARM).size();
        int cold = leadService.getLeadsByStatus(LeadStatus.COLD).size();
        int total = leadService.getAllLeads().size();

        System.out.println("Total Leads: " + total);
        System.out.println("HOT: " + hot);
        System.out.println("WARM: " + warm);
        System.out.println("COLD: " + cold);
    }
    //Method to list leads by city
    private void listLeadsByCity() {
        System.out.println("\n--- Leads by City ---");
        String city = readNonEmpty("Enter city: ");

        var leads = leadService.getLeadsByCity(city);
        if (leads.isEmpty()) {
            System.out.println("(no leads found for city: " + city + ")");
            return;
        }

        leads.forEach(System.out::println);
    }
    //This right herre is the method to convert a lead to a patient!. VERY IMPORTANT!
    private void convertLeadToPatientFlow() {
        System.out.println("\n--- Convert Lead to Patient ---");
        long leadId = readLong("Enter lead ID to convert: ");

        System.out.print("Notes (optional): ");
        String notes = scanner.nextLine().trim();

        try {
            Patient patient = leadService.convertLeadToPatient(leadId, notes, patientService);
            System.out.println("✅ Converted successfully!");
            System.out.println("New Patient ID: " + patient.getId());
            System.out.println(patient);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    //Lists all the patients in the system
    private void listAllPatients() {
        System.out.println("\n--- All Patients ---");
        var patients = patientService.getAllPatients();

        if (patients.isEmpty()) {
            System.out.println("(no patients yet)");
            return;
        }

        patients.forEach(System.out::println);
    }
    //patient search by ID
    private void findPatientById() {
        System.out.println("\n--- Find Patient by ID ---");
        long id = readLong("Enter patient ID: ");

        var patientOpt = patientService.getPatientById(id);
        if (patientOpt.isPresent()) {
            System.out.println("✅ Found:");
            System.out.println(patientOpt.get());
        } else {
            System.out.println("❌ Patient not found.");
        }
    }

}

