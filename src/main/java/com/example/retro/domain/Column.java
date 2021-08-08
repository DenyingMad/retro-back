package com.example.retro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "column")
public class Column extends DomainObject {
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "column_id")
    private Set<Card> cards;
}
