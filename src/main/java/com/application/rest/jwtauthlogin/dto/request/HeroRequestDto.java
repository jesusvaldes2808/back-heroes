package com.application.rest.jwtauthlogin.dto.request;


import com.application.rest.jwtauthlogin.enums.PublisherHero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroRequestDto {

    Long           id;
    String         idHero;
    String         superhero;
    PublisherHero  publisher;
    String         alterEgo;
    String         firstAppearance;
    String         characters;
    String         altImg;
}
