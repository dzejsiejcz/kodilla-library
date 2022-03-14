package com.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RENTALS")
@Data
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)
    private int id;

    @NotNull
    @Column(name = "CREATED")
    private Date rented;

    @Column(name = "RETURNED")
    private Date returned;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "COPIES_ID")
    private Copy copy;

    @Builder
    public Rent(Date rented, Date returned, Reader reader, Copy copy) {
        this.rented = rented;
        this.returned = returned;
        this.reader = reader;
        this.copy = copy;
    }
}
