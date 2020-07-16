package com.guseva.moccaunting.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="operation")
@ToString
@EqualsAndHashCode(of = {"id"})
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    Set<Tag> tags;

    @ManyToOne
    CategoryOutlay categoryOutlay;
    @ManyToOne
    Purse purse;
    @ManyToOne
    Purse purseSponsor; //используется при операции из_кошелька_в_другой_кошелек
    @ManyToOne
    SourceMoney sourceMoney;

    Long moneyAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date operationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tag) {
        this.tags = tag;
    }

    public CategoryOutlay getCategoryOutlay() {
        return categoryOutlay;
    }

    public void setCategoryOutlay(CategoryOutlay categoryOutlay) {
        this.categoryOutlay = categoryOutlay;
    }

    public Purse getPurse() {
        return purse;
    }

    public void setPurse(Purse purse) {
        this.purse = purse;
    }

    public Purse getPurseSponsor() {
        return purseSponsor;
    }

    public void setPurseSponsor(Purse purseSponsor) {
        this.purseSponsor = purseSponsor;
    }

    public SourceMoney getSourceMoney() {
        return sourceMoney;
    }

    public void setSourceMoney(SourceMoney sourceMoney) {
        this.sourceMoney = sourceMoney;
    }

    public Long getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}
