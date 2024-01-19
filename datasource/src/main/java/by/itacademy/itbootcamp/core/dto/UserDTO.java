package by.itacademy.itbootcamp.core.dto;

public class UserDTO {

    private String fio;

    private String email;

    private String role;

    public UserDTO() {
    }

    public UserDTO(String fio, String email, String role) {
        this.fio = fio;
        this.email = email;
        this.role = role;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
