package com.example.restapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "artists")
@Getter
@Setter
public class Artist {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column
    private String specialization;

    @Column(name = "experience_years")
    private int experienceYears;

    @ManyToMany(mappedBy = "artists")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Tattoo> tattoos;

}