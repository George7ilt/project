package com.example.restapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "tattoos")
@Getter
@Setter
@NoArgsConstructor
public class Tattoo {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @ManyToMany
    @JoinTable(name = "artist_tattoo",
               joinColumns = @JoinColumn(name = "artist_id"),
               inverseJoinColumns = @JoinColumn(name = "tattoo_id"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Artist> artists;
}