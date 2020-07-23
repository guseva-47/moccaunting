package com.guseva.moccaunting.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@ToString
@EqualsAndHashCode
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long expectedMoney;
    private Long currentMoney;

    private Date startPeriod;
    private Date endPeriod;

    @ManyToOne
    SourceMoney sourceMoney;

    @ManyToOne
    CategoryOutlay categoryOutlay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpectedMoney() {
        return expectedMoney;
    }

    public void setExpectedMoney(Long expectedMoney) {
        this.expectedMoney = expectedMoney;
    }

    public Long getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Long currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }

    public SourceMoney getSourceMoney() {
        return sourceMoney;
    }

    public void setSourceMoney(SourceMoney sourceMoney) {
        this.sourceMoney = sourceMoney;
    }

    public CategoryOutlay getCategoryOutlay() {
        return categoryOutlay;
    }

    public void setCategoryOutlay(CategoryOutlay categoryOutlay) {
        this.categoryOutlay = categoryOutlay;
    }
}
