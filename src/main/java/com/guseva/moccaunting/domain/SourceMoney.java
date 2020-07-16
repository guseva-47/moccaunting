package com.guseva.moccaunting.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="source_money")
@ToString
@EqualsAndHashCode(of = {"id"})
public class SourceMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Long expectedOutlay;
    private Long currentOutlay;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

//    @OneToMany
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

    public Long getExpectedOutlay() {
        return expectedOutlay;
    }

    public void setExpectedOutlay(Long expectedOutlay) {
        this.expectedOutlay = expectedOutlay;
    }

    public Long getCurrentOutlay() {
        return currentOutlay;
    }

    public void setCurrentOutlay(Long currentOutlay) {
        this.currentOutlay = currentOutlay;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

//    public Set<Operation> getOperations() {
//        return operations;
//    }
//
//    public void setOperations(Set<Operation> operations) {
//        this.operations = operations;
//    }

}
