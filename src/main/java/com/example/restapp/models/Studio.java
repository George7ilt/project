package com.example.restapp.models;

import com.example.restapp.models.relations.ClietnsStrudios;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "studio")
@Getter
@Setter
public class Studio {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @OneToMany(mappedBy = "studio")
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<Master> masters;

    @OneToMany(mappedBy = "studio")
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<ClietnsStrudios> clients;

}