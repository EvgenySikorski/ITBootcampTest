package by.itacademy.itbootcamp.core.enums;

public enum EUserRole {
    ADMINISTRATOR ("Administrator"),
    SALE_USER ("Sale User"),
    CUSTOMER_USER ("Customer User"),
    SECURE_API_USER ("Secure API User");

    private final String userRole;

    EUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

}
