package ch.ti8m.academy.security.basic.user;

public enum UserRoles {
    ADMIN,
    STAFF,
    USER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }

    public static String getHierarchyDefinition() {
        return ADMIN.getAuthority()
                + " > " + STAFF.getAuthority()
                + " > " + USER.getAuthority();
    }
}
