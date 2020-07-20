package com.guseva.moccaunting.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="tag")
@ToString
@EqualsAndHashCode(of = {"id"})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

//    @ManyToMany//(fetch = FetchType.LAZY)
//    private Set<Operation> operations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Set<Operation> getOperations() {
//        return operations;
//    }
//
//    public void setOperations(Set<Operation> operations) {
//        this.operations = operations;
//    }
}
