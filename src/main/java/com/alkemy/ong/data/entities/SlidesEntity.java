package com.alkemy.ong.data.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "slides")
@SQLDelete(sql = "UPDATE slides SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlidesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;
    private String text;
    private Integer slideOrder;

    @ManyToOne
    @JoinColumn(name="organization_id", nullable=false)
    private OrganizationEntity organization;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

}
