package com.alkemy.ong.data.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "slides")
@SQLDelete(sql = "UPDATE organizations SET is_deleted = true WHERE id=?")
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
    private Integer order;

    @ManyToOne(cascade = CascadeType.ALL)
    private OrganizationEntity organization;



}
