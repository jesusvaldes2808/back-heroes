package com.application.rest.jwtauthlogin.services;

import com.application.rest.jwtauthlogin.dto.request.HeroRequestDto;
import com.application.rest.jwtauthlogin.dto.response.HeroResponseDto;

import java.util.List;

public interface HeroService {

    HeroResponseDto addHero (HeroRequestDto hero);

    HeroResponseDto updateHero (Long id, HeroRequestDto heroRequestDto);

    HeroResponseDto deleteHero (Long id);

    List<HeroRequestDto> getHeros ();

    HeroRequestDto getHeroById (Long id);

    List<HeroRequestDto> getSuggestions (String query);

}
