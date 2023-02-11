package com.example.jpa.domain.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "foo")
public class Foo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bar1;
    private String bar2;
    private String bar3;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
