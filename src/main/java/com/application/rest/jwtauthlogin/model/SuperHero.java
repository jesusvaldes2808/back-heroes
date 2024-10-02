package com.application.rest.jwtauthlogin.model;


import com.application.rest.jwtauthlogin.enums.PublisherHero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "T_HERO")
public class SuperHero implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_HERO" , nullable = false)
    private String idHero;


    @Column(name = "SUPERHERO" , nullable = false)
    private String superhero;

    @Column(name = "PUBLISHER" , nullable = false)
    @Enumerated(EnumType.STRING)
    private PublisherHero publisher;

    @Column(name = "ALTER_EGO" , nullable = false)
    private String alterEgo;

    @Column(name = "FIRTS_APPEARANCE" , nullable = false)
    private String firstAppearance;

    @Column(name = "CHARACTERS" , nullable = false)
    private String characters;

    @Column(name = "ALT_IMG")
    private String altImg;






}
