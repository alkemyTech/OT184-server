package com.alkemy.ong.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="news")
@Getter
@Setter
@SQLDelete(sql = "UPDATE news set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition="TEXT")
    private String content;
    @Column(nullable = false)
    private String image;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name="is_deleted")
    private boolean isDeleted = Boolean.FALSE;

    @Column(name="created_at", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime createdAt;

    @Column(name="updated_at", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime updatedAt;

}
