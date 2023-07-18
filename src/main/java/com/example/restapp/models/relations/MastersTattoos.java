package com.example.restapp.models.relations;

import com.example.restapp.models.Master;
import com.example.restapp.models.Tattoo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "master_tattoo")
@Getter
@Setter
@NoArgsConstructor
public class MastersTattoos {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "master_id", referencedColumnName = "id")
    @ManyToOne
    private Master master;

    @ManyToOne
    @JoinColumn(name = "tattoo_id", referencedColumnName = "id")
    private Tattoo tattoo;

    public MastersTattoos(Master master, Tattoo tattoo) {
        this.master = master;
        this.tattoo = tattoo;
    }
}
