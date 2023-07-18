package com.example.restapp.models.relations;

import com.example.restapp.models.Client;
import com.example.restapp.models.Tattoo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

@Entity
@Table(name = "client_tattoo")
@Getter
@Setter
public class ClientsTattoos {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name = "tattoo_id", referencedColumnName = "id")
    private Tattoo tattoo;
}
