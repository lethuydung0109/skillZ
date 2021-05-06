package miage.skillz.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class PublicContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String date;


    public PublicContent() {
    }

    public PublicContent(String content, String date) {
        this.content = content;
        this.date = date;
    }
}
