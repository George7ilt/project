package com.example.restapp.models.relations;

import com.example.restapp.models.Client;
import com.example.restapp.models.Studio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client_studio")
@Getter
@Setter
public class ClietnsStrudios {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name = "studio_id", referencedColumnName = "id")
    private Studio studio;

}
