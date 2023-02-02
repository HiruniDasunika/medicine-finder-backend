package hiruni.project.medicinefinderbackend.entity;

public enum Role {
    CUSTOMER("CUSTOMER"),
    AGENCY("AGENCY");

    private String role;
    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
