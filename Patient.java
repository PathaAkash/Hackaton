import java.util.*;

class Patient {
    String id, name, condition;
    int age;

    Patient(String id, String name, int age, String condition) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.condition = condition;
    }

    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Condition: " + condition;
    }
}

class Therapist {
    String id, name;
    List<Patient> patients;

    Therapist(String id, String name) {
        this.id = id;
        this.name = name;
        this.patients = new ArrayList<>();
    }

    void assignPatient(Patient p) {
        patients.add(p);
    }

    public String toString() {
        return "Therapist ID: " + id + ", Name: " + name + ", Patients Assigned: " + patients.size();
    }
}

class Session {
    String sessionId, therapistId, patientId, notes;
    int score;

    Session(String sessionId, String therapistId, String patientId, String notes, int score) {
        this.sessionId = sessionId;
        this.therapistId = therapistId;
        this.patientId = patientId;
        this.notes = notes;
        this.score = score;
    }

    public String toString() {
        return "Session ID: " + sessionId + ", Therapist ID: " + therapistId + ", Patient ID: " + patientId +
                ", Notes: " + notes + ", Score: " + score;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Patient> patients = new ArrayList<>();
    static List<Therapist> therapists = new ArrayList<>();
    static List<Session> sessions = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Speech Therapy Management ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Therapist");
            System.out.println("3. Assign Patient to Therapist");
            System.out.println("4. Record Therapy Session");
            System.out.println("5. View All Sessions");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {
                case 1: addPatient(); break;
                case 2: addTherapist(); break;
                case 3: assignPatient(); break;
                case 4: recordSession(); break;
                case 5: viewSessions(); break;
                case 6: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addPatient() {
        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = readInt();
        System.out.print("Enter Condition: ");
        String condition = sc.nextLine();
        patients.add(new Patient(id, name, age, condition));
        System.out.println("Patient added successfully.");
    }

    static void addTherapist() {
        System.out.print("Enter Therapist ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        therapists.add(new Therapist(id, name));
        System.out.println("Therapist added successfully.");
    }

    static void assignPatient() {
        if (therapists.isEmpty() || patients.isEmpty()) {
            System.out.println("Add both therapists and patients first.");
            return;
        }
        System.out.println("Choose Therapist:");
        for (int i = 0; i < therapists.size(); i++)
            System.out.println((i + 1) + ". " + therapists.get(i).name);
        int tIndex = readInt() - 1;
        if (tIndex < 0 || tIndex >= therapists.size()) {
            System.out.println("Invalid therapist selection.");
            return;
        }
        System.out.println("Choose Patient:");
        for (int i = 0; i < patients.size(); i++)
            System.out.println((i + 1) + ". " + patients.get(i).name);
        int pIndex = readInt() - 1;
        if (pIndex < 0 || pIndex >= patients.size()) {
            System.out.println("Invalid patient selection.");
            return;
        }
        therapists.get(tIndex).assignPatient(patients.get(pIndex));
        System.out.println("Patient assigned to therapist successfully.");
    }

    static void recordSession() {
        System.out.print("Enter Session ID: ");
        String sessionId = sc.nextLine();
        System.out.print("Enter Therapist ID: ");
        String therapistId = sc.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = sc.nextLine();
        System.out.print("Enter Notes: ");
        String notes = sc.nextLine();
        System.out.print("Enter Score (1-10): ");
        int score = readInt();
        sessions.add(new Session(sessionId, therapistId, patientId, notes, score));
        System.out.println("Session recorded successfully.");
    }

    static void viewSessions() {
        if (sessions.isEmpty()) {
            System.out.println("No sessions recorded yet.");
            return;
        }
        System.out.println("\n=== Session Records ===");
        for (Session s : sessions)
            System.out.println(s);
    }

    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}