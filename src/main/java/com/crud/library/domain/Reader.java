package com.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    @Column(name = "EMAIL", unique = true)
    private String lastName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String email;

    @NotNull
    @Column(name = "CREATED")
    private Date created;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "reader",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    @Builder
    public Reader(String firstName, String lastName, String email, Date created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.created = created;
    }
}
