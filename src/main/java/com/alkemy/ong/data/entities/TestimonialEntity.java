package com.alkemy.ong.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "testimonials")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "content")
    private String content;
    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;
}
