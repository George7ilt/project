package com.example.restapp.models;

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

    @ManyToMany
    @JoinTable(name = "master_tattoo", joinColumns = @JoinColumn(name = "master_id"), inverseJoinColumns = @JoinColumn(name = "tattoo_id"))
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<Master> masters;

    @ManyToMany(mappedBy = "tattoos")
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<Client> clients;
}