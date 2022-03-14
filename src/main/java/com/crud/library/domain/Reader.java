package com.crud.library.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "READERS")
@Data
@NoArgsConstructor
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)
    private int id;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotNull
    @Column(name = "CREATED")
    private Instant created;

    @OneToMany(
            targetEntity = Hire.class,
            mappedBy = "reader",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Hire> hires = new ArrayList<>();

    @Builder
    public Reader(String firstName, String lastName, String email, Instant created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.created = created;
    }
}
