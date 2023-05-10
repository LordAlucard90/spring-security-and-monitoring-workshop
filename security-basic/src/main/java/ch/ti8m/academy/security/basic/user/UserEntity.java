package ch.ti8m.academy.security.basic.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    private boolean disabled;

    @Transient
    public String describe() {
        return String.format(
                "User(username=%s, role=%s, isDisable=%s)",
                username,
                role,
                disabled
        );
    }
}
