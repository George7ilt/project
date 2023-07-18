package com.example.restapp.models;

import com.example.restapp.models.relations.Appointment;
import com.example.restapp.models.relations.MastersTattoos;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Table(name = "master")
@Getter
@Setter
public class Master {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "studio_id", referencedColumnName = "id")
    private Studio studio;

    @Column
    private String specialization;

    @Column(name = "experience_years")
    private int experienceYears;

    @OneToMany(mappedBy = "master")
    @Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MastersTattoos> availableTattoos;

    @OneToMany(mappedBy = "master")
    @Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Appointment> appointments;

}