package com.guseva.moccaunting.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="category_outlay")
@ToString
@EqualsAndHashCode(of = {"id"})
public class CategoryOutlay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private Long expectedOutlay;
    private Long currentOutlay;
    @Column(nullable = false)
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
