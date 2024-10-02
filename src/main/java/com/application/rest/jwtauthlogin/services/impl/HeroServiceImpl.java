package com.application.rest.jwtauthlogin.services.impl;

import com.application.rest.jwtauthlogin.ExceptionHandler.HeroNotFoundException;
import com.application.rest.jwtauthlogin.dao.HeroRepository;
import com.application.rest.jwtauthlogin.dto.request.HeroRequestDto;
import com.application.rest.jwtauthlogin.dto.response.HeroResponseDto;
import com.application.rest.jwtauthlogin.model.SuperHero;
import com.application.rest.jwtauthlogin.services.HeroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HeroServiceImpl implements HeroService {

    private HeroRepository heroRepository;

    @Override
    public HeroResponseDto addHero(HeroRequestDto hero) {


        SuperHero superHero = SuperHero

                .builder()
                .idHero(hero.getIdHero())
                .superhero(hero.getSuperhero())
                .publisher(hero.getPublisher())
                .alterEgo(hero.getAlterEgo())
                .firstAppearance(hero.getFirstAppearance())
                .characters(hero.getCharacters())
                .altImg(hero.getAltImg())
                .build();

        heroRepository.save(superHero);
        return new HeroResponseDto("Hero creado con exito");
    }

    @Override
    public HeroResponseDto updateHero(Long id, HeroRequestDto heroRequestDto) {

        SuperHero existingHero = heroRepository.findById(id)
                .orElseThrow(() -> new HeroNotFoundException("El héroe con ID " + id + " no se encuentra."));

        try {

            SuperHero updatedHero = SuperHero.builder()
                    .id(existingHero.getId())
                    .idHero(heroRequestDto.getIdHero())
                    .superhero(heroRequestDto.getSuperhero())
                    .publisher(heroRequestDto.getPublisher())
                    .alterEgo(heroRequestDto.getAlterEgo())
                    .firstAppearance(heroRequestDto.getFirstAppearance())
                    .characters(heroRequestDto.getCharacters())
                    .altImg(heroRequestDto.getAltImg())
                    .build();

            heroRepository.save(updatedHero);
            return new HeroResponseDto("Héroe actualizado con éxito");

        } catch (Exception e) {
            throw new HeroNotFoundException("Error al actualizar al heroe: " + e.getMessage());
        }
    }

    @Override
    public HeroResponseDto deleteHero(Long id) {

        SuperHero superHero = heroRepository.findById(id).orElseThrow(() ->
                new HeroNotFoundException("El heroe con id " + id + "no se encuentra registrado"));
        try {
            if (superHero != null) {
                heroRepository.delete(superHero);
            }
            return new HeroResponseDto("Héroe eliminado con éxito");

        } catch (Exception e) {
            throw new HeroNotFoundException("Error al eliminar al heroe: " + e.getMessage());
        }
    }

    @Override
    public List<HeroRequestDto> getHeros() {
        try {

            List<SuperHero> superHeros = heroRepository.findAll();

            if (superHeros.isEmpty()) {
                throw new HeroNotFoundException("No se encontraron héroes.");
            }
            return superHeros.stream()
                    .map(hero -> HeroRequestDto.builder()
                            .id(hero.getId())
                            .idHero(hero.getIdHero())
                            .superhero(hero.getSuperhero())
                            .publisher(hero.getPublisher())
                            .alterEgo(hero.getAlterEgo())
                            .firstAppearance(hero.getFirstAppearance())
                            .characters(hero.getCharacters())
                            .altImg(hero.getAltImg())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new HeroNotFoundException("Error al obtener la lista de héroes: " + e.getMessage());
        }
    }

    @Override
    public HeroRequestDto getHeroById(Long id) {

        SuperHero hero = heroRepository.findById(id).orElseThrow(
                () -> new HeroNotFoundException("El héroe con ID " + id + " no se encuentra.")
        );

        if (hero.getId() != null) {

            return HeroRequestDto
                    .builder()
                    .id(hero.getId())
                    .idHero(hero.getIdHero())
                    .superhero(hero.getSuperhero())
                    .publisher(hero.getPublisher())
                    .alterEgo(hero.getAlterEgo())
                    .firstAppearance(hero.getFirstAppearance())
                    .characters(hero.getCharacters())
                    .altImg(hero.getAltImg())
                    .build();
        }
        return null;
    }

    @Override
    public List<HeroRequestDto> getSuggestions(String query) {

        List<SuperHero> superHeroes = heroRepository.findSuperheroBySuggestions(query);

        return superHeroes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private HeroRequestDto convertToDto(SuperHero superHero) {

        return new HeroRequestDto(
                superHero.getId(),
                superHero.getIdHero(),
                superHero.getSuperhero(),
                superHero.getPublisher(),
                superHero.getAlterEgo(),
                superHero.getFirstAppearance(),
                superHero.getCharacters(),
                superHero.getAltImg()
        );
    }


}
