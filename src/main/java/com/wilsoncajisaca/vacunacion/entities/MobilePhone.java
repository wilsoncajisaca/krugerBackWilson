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
@Table(name = "mobile_phone")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MobilePhone implements Serializable {
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

    @Size(min = 7, max = 25)
    @Column(name = "number_phone", nullable = false, length = 25)
    @With
    private String numberPhone;

    @Size(min = 3, max = 150)
    @Column(name = "description", length = 150)
    @With
    private String description;

    @Column(name = "status", nullable = false, length = 1)
    @With
    private Boolean status;

    @Tolerate
    public MobilePhone() {
        super();
    }

    @PrePersist
    public void prePersist() {
        this.status = true;
    }
}
