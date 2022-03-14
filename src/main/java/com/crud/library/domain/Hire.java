package com.crud.library.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HIRES")
public class Hire {


    private int id;
    private Date hired;
    private Date returned;
    private Reader reader;
    private Copy copy;


    public Hire(Date hired, Date returned) {
        this.hired = hired;
        this.returned = returned;
    }

    public Hire() {
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
    @Column(name = "CREATED")
    public Date getHired() {
        return hired;
    }

    public void setHired(Date hired) {
        this.hired = hired;
    }

    @Column(name = "RETURNED")
    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date returned) {
        this.returned = returned;
    }

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "READER_ID")
    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "COPIES_ID")
    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }
}
