package com.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COPIES")
public class Copy {

    private int id;
    private List<Hire> hires = new ArrayList<>();
    private Title title;
    private Status status;

    public Copy() {
    }

    @Builder
    public Copy(Title title, Status status, List<Hire> hires) {
        this.title = title;
        this.status = status;
        this.hires = hires;
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

    @OneToMany(
            targetEntity = Hire.class,
            mappedBy = "copy",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Hire> getHires() {
        return hires;
    }

    public void setHires(List<Hire> hires) {
        this.hires = hires;
    }

    @ManyToOne
    @JoinColumn(name = "TITLES_ID")
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @NotNull
    @Column(columnDefinition = "ENUM('BORROWED', 'AVAILABLE', 'DESTROYED', 'LOST')")
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
