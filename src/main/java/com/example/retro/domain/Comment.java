package com.example.retro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment extends DomainObject {
    private String text;
}
