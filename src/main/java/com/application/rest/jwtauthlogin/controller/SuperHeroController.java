package com.application.rest.jwtauthlogin.controller;

import com.application.rest.jwtauthlogin.dto.request.HeroRequestDto;
import com.application.rest.jwtauthlogin.dto.response.HeroResponseDto;
import com.application.rest.jwtauthlogin.services.HeroService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/superhero")
@CrossOrigin(origins = "*")
public class SuperHeroController {

    private HeroService heroService;

    @PostMapping(value = "/register")
    public HeroResponseDto createSuperHero(@RequestBody HeroRequestDto superHero) {

        this.heroService.addHero(superHero);
        return new HeroResponseDto("Super Hero created");
    }

    @GetMapping(value = "/hero/{id}")
    public HeroRequestDto getSuperHeroById(@PathVariable("id") Long id) {

      return  heroService.getHeroById(id);
    }

    @GetMapping(value = "/heros")
    public List<HeroRequestDto> getSuperHeros() {

        return  heroService.getHeros();
    }

    @PutMapping(value = "/update/{id}")
    public HeroResponseDto updateSuperHero(@PathVariable Long id, @RequestBody HeroRequestDto superHero) {
       return  this.heroService.updateHero(id, superHero);
    }

    @DeleteMapping(value = "/delete/{id}")
    public HeroResponseDto deleteSuperHero(@PathVariable Long id) {

        return this.heroService.deleteHero(id);
    }

    @GetMapping(value = "/suggestions")
    public List<HeroRequestDto> getSuggestions(@RequestParam(value = "q", required = false) String query) {

        return  heroService.getSuggestions(query);
    }

}
