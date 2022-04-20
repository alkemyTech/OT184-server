package com.alkemy.ong.data.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted = false")
@NoArgsConstructor(force = true)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;

    @Column(name= "facebook_url")
    private String facebookUrl;

    @Column(name= "instagram_url")
    private String instagramUrl;

    @Column(name= "linkedin_url")
    private String linkedinUrl;

    @NotNull
    @Column(name= "image")
    private String image;

    @Column(name= "description")
    private String description;

    @Column(name="is_deleted")
    private Boolean isDeleted;

    @Column(name="created_at",  updatable=false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
