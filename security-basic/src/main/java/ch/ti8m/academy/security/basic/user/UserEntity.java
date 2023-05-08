package ch.ti8m.academy.security.basic.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
