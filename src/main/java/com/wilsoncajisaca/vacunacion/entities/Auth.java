package com.wilsoncajisaca.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wilsoncajisaca.vacunacion.entities.audit.Audit;
import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wilson.cajisaca
 */
@Entity
@Table(name = "auth_employee", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}) })
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Auth extends Audit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "id_employee")
    @With
    private Long idEmployee;
    @JsonIgnore
    @JoinColumn(name = "id_employee",
            referencedColumnName = "id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_employee"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    @Size(min = 3, max = 150)
    @Column(name = "username", length = 150, unique = true)
    @With
    private String username;

    @Size(min = 3, max = 150)
    @Column(name = "password", length = 150)
    @With
    private String password;

    @NotNull
    @Column(name = "status", length = 1, nullable = false)
    @With
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "auth_role",
            joinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id"))
    @With
    private Set<Role> roles = new HashSet<>();

    @Tolerate
    public Auth() {
        super();
    }
}
