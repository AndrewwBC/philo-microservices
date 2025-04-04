package com.filo.posts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Posts")
@Table(name = "posts")
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@AllArgsConstructor
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotEmpty
    @NotNull
    private String content;
    @CreationTimestamp
    private LocalDateTime creationDate = LocalDateTime.now();
    @Column(name = "user_id")
    private String userId;

    public PostModel(String content, String userId){
        this.content = content;
        this.userId = userId;
    }
    public PostModel() {}

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
