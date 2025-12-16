# PatientFlow OS – UML Class Diagram (Mermaid)

```mermaid
classDiagram

--------------------------
      APP LAYER
--------------------------
class PatientFlowApp {
  - LeadService leadService
  - PatientService patientService
  - Scanner scanner
  + main(String[]) void
  - run() void
  - printMenu() void
  - createLeadFlow() void
  - listAllLeads() void
  - listLeadsByStatus(status) void
  - dailySummary() void
  - listLeadsByCity() void
  - convertLeadToPatientFlow() void
  - listAllPatients() void
  - findPatientById() void
}
---------------------------
       MODEL LAYER
---------------------------
class Lead {
  - long id
  - String fullName
  - ContactInfo contactInfo
  - int age
  - String travelWillingness
  - String treatmentType
  - String treatmentTimeline
  - double estimatedBudget
  - int score
  - LeadStatus status
  + getId() long
  + getFullName() String
  + getContactInfo() ContactInfo
  + getAge() int
  + getTravelWillingness() String
  + getTreatmentType() String
  + getTreatmentTimeline() String
  + getEstimatedBudget() double
  + getScore() int
  + getStatus() LeadStatus
  + setScore(score) void
  + setStatus(status) void
  + toString() String
}

class Patient {
  - long id
  - String fullName
  - ContactInfo contactInfo
  - int age
  - String treatmentType
  - String notes
  + getId() long
  + getFullName() String
  + getContactInfo() ContactInfo
  + getAge() int
  + getTreatmentType() String
  + getNotes() String
  + toString() String
}

class ContactInfo {
  - String whatsappNumber
  - String email
  - String city
  + getWhatsappNumber() String
  + getEmail() String
  + getCity() String
}

class LeadStatus {
  <<enum>>
  HOT
  WARM
  COLD
}

---------------------------
    REPOSITORY LAYER
---------------------------
class CrudRepository~T,ID~ {
  <<interface>>
  + save(entity) T
  + findById(id) Optional~T~
  + findAll() List~T~
  + deleteById(id) void
}

class LeadRepository {
  <<interface>>
  + findByStatus(status) List~Lead~
  + findByCity(city) List~Lead~
}

class PatientRepository {
  <<interface>>
}

class InMemoryLeadRepository {
  - List~Lead~ storage
  + save(entity) Lead
  + findById(id) Optional~Lead~
  + findAll() List~Lead~
  + deleteById(id) void
  + findByStatus(status) List~Lead~
  + findByCity(city) List~Lead~
}

class InMemoryPatientRepository {
  - List~Patient~ storage
  + save(entity) Patient
  + findById(id) Optional~Patient~
  + findAll() List~Patient~
  + deleteById(id) void
}

%% =========================
%% SCORING LAYER (STRATEGY)
%% =========================
class LeadScoringStrategy {
  <<interface>>
  + calculateScore(lead) int
}

class DefaultLeadScoringStrategy {
  + calculateScore(lead) int
}

--------------------------
    SERVICE LAYER
--------------------------
class LeadService {
  - LeadRepository leadRepository
  - LeadScoringStrategy scoringStrategy
  + createLead(...) Lead
  + getAllLeads() List~Lead~
  + getLeadsByStatus(status) List~Lead~
  + getLeadsByCity(city) List~Lead~
  + getLeadById(id) Optional~Lead~
  + convertLeadToPatient(leadId, notes, patientService) Patient
}

class PatientService {
  - PatientRepository patientRepository
  + save(patient) Patient
  + getAllPatients() List~Patient~
  + getPatientById(id) Optional~Patient~
}

--------------------------
    RELATIONSHIPS
--------------------------

PatientFlowApp --> LeadService : uses
PatientFlowApp --> PatientService : uses

LeadService --> LeadRepository : depends on
LeadService --> LeadScoringStrategy : depends on
LeadService --> PatientService : uses (for conversion)

PatientService --> PatientRepository : depends on

LeadRepository --|> CrudRepository : extends
PatientRepository --|> CrudRepository : extends

InMemoryLeadRepository ..|> LeadRepository : implements
InMemoryPatientRepository ..|> PatientRepository : implements

DefaultLeadScoringStrategy ..|> LeadScoringStrategy : implements

Lead --> ContactInfo : has-a
Patient --> ContactInfo : has-a
Lead --> LeadStatus : status