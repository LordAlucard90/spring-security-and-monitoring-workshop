package ch.ti8m.academy.monitoring.post;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "posts")
public class PostEntity {
    @Id
    @Generated
    private long id;

    private String title;

    private String content;
}
