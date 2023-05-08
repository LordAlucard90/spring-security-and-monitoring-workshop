package ch.ti8m.academy.security.basic.user;

public enum UserRole {
    ADMIN, STAFF, USER;

    public String asSpringRole() {
        return this.name() + "_ROLE";
    }
}
