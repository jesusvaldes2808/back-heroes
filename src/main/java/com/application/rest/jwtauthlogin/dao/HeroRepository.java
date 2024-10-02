package com.application.rest.jwtauthlogin.dao;

import com.application.rest.jwtauthlogin.dto.request.HeroRequestDto;
import com.application.rest.jwtauthlogin.enums.PublisherHero;
import com.application.rest.jwtauthlogin.model.SuperHero;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroRepository extends JpaRepository<SuperHero, Long> {

    @Modifying()
    @Transactional
    @Query("UPDATE SuperHero u set u.superhero=:superhero, u.publisher=:publisher, u.alterEgo=:alterEgo, u.firstAppearance=:firstAppearance, u.characters=:characters, u.altImg=:altImg WHERE u.idHero = :idHero")
    void updateUser(
            @Param("idHero") String idHero,
            @Param("superhero") String superhero,
            @Param("publisher") PublisherHero publisher,
            @Param("alterEgo") String alterEgo,
            @Param("firstAppearance") String firstAppearance,
            @Param("characters") String characters,
            @Param("altImg") String altImg
    );

    @Query("SELECT u FROM SuperHero u WHERE u.superhero LIKE %:superhero%")
    List<SuperHero> findSuperheroBySuggestions(@Param("superhero") String superhero);
}
