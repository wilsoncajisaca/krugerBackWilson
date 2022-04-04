package com.wilsoncajisaca.vacunacion.entities;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author wilson.cajisaca
 */
@Entity
@Table(name = "roles")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 3, max = 25)
    @Column(name = "name", length = 25)
    @With
    private String name;

    @Size(min = 3, max = 150)
    @Column(name = "description", length = 150)
    @With
    private String description;

    @Column(name = "status", length = 1)
    @With
    private Boolean status;

    @Tolerate
    public Role() {
        super();
    }
}
