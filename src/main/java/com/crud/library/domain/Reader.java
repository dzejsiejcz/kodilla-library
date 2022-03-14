package com.crud.library.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "READERS")
public class Reader {


    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Date created;
    private List<Hire> hires = new ArrayList<>();

    public Reader(String firstName, String lastName, String email, Date created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.created = created;
    }

    public Reader() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "EMAIL", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Column(name = "CREATED")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(
            targetEntity = Hire.class,
            mappedBy = "reader",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    public List<Hire> getHires() {
        return hires;
    }

    public void setHires(List<Hire> hires) {
        this.hires = hires;
    }
}
