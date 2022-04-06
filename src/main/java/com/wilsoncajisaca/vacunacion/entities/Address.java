package com.wilsoncajisaca.vacunacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author wilson.cajisaca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "address")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "one_street", length = 150)
    @With
    private String oneStreet;

    @Size(min = 3, max = 150)
    @Column(name = "two_street", length = 150)
    @With
    private String twoStreet;

    @Size(min = 3, max = 10)
    @Column(name = "houseNumber", length = 10)
    @With
    private String houseNumber;

    @Size(min = 3, max = 250)
    @Column(name = "reference", nullable = false, length = 250)
    @With
    private String reference;

    @Column(name = "status", nullable = false, length = 1)
    @With
    private Boolean status;

    @Tolerate
    public Address() {
        super();
    }

    @PrePersist
    public void prePersist() {
        this.status = true;
    }

}
