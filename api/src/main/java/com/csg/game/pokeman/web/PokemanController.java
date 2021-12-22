package com.csg.game.pokeman.web;

import com.csg.game.pokeman.exception.PokemonNotFoundException;
import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.model.PokemonType;
import com.csg.game.pokeman.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/pokemons")
public class PokemanController extends BaseController{

    private final PokemonService pokemonService;

    public PokemanController(PokemonService service){
        this.pokemonService = service;
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable ("name") String name)  {
        Optional<Pokemon> pokemon = pokemonService.getPokemon(name, PokemonType.PLAIN);
        if(pokemon.isPresent()){
            return getResponse(pokemon.get());
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/translated/{name}")
    public ResponseEntity<Pokemon> getTranlatedPokemon(@PathVariable ("name") String name) {
        Optional<Pokemon> pokemon = pokemonService.getPokemon(name, PokemonType.TRANSLATED);
        if(pokemon.isPresent()){
            return getResponse(pokemon.get());
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
