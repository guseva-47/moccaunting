package com.guseva.moccaunting.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="purse")
@ToString
@EqualsAndHashCode(of = {"id"})
public class Purse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Long expectedOutlay;
    private Long currentOutlay;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

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
}
