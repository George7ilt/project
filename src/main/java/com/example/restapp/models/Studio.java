package com.example.restapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    @Cascade(CascadeType.PERSIST)
    private List<Master> masters;

    @ManyToMany
    @JoinTable(name = "studio_client",
            joinColumns = @JoinColumn(name = "studio_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    @Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Client> clients;

}