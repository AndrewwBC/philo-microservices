package com.filo.posts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity(name = "Posts")
@Table(name = "posts")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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

    public String getId() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setClaps(Integer claps) {
        this.claps = claps;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
