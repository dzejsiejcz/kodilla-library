package com.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COPIES")
@Data
@NoArgsConstructor
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)
    private int id;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "copy",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Rent> rents = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "TITLES_ID")
    private Title title;

    @NotNull
    @Column(columnDefinition = "ENUM('BORROWED', 'AVAILABLE', 'DESTROYED', 'LOST')")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Copy(Title title, Status status) {
        this.title = title;
        this.status = status;
    }
}
