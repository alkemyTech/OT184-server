package com.alkemy.ong.data.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Members{

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
    private Boolean isDeleted = Boolean.FALSE;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;


    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updateAt = new Date();
    }
}
