package com.example.retro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Card extends DomainObject {
    private String text;
    private int votes;
    @Enumerated(EnumType.STRING)
    private Color color;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Set<Comment> comments;
}
