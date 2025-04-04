package com.filo.posts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String content;
    @CreationTimestamp
    private LocalDateTime creationDate = LocalDateTime.now();

    @NotNull
    private Integer claps = 0;

    @Column(name = "user_id")
    @NotBlank
    private String userId;

    public PostModel(String content, String userId){
        this.content = content;
        this.userId = userId;
    }
    public PostModel() {}

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getClaps() {
        return claps;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", claps=" + claps +
                ", userId='" + userId + '\'' +
                '}';
    }
}
