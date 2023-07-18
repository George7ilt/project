package com.example.restapp.models;

import com.example.restapp.models.relations.ClientsTattoos;
import com.example.restapp.models.relations.MastersTattoos;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "tattoo")
@Getter
@Setter
@NoArgsConstructor
public class Tattoo {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @OneToMany(mappedBy = "tattoo")
    private List<MastersTattoos> masters;

    @OneToMany(mappedBy = "tattoo")
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<ClientsTattoos> clients;


}