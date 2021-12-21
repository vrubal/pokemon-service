package com.csg.game.pokeman.web;

import com.csg.game.pokeman.model.Pokemon;
import com.csg.game.pokeman.schema.response.PokemonSpeciesResponse;
import com.csg.game.pokeman.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemons")
public class PokemanController extends BaseController{

    @Autowired
    private PokemonService pokemonService;

    @GetMapping(path = "/{name}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable ("name") String name){
        return buildResponse(pokemonService.getPokemon(name));
    }

    /*@GetMapping(path = {"/translated/{name}", "/translated/{id}"})
    public PokemonResponse getTranlatedPokemon(@PathVariable ("name") String name, @PathVariable ("name") String id){
        return pokemonService.getPokemon(name);
    }
    */
    @GetMapping(path = "/species/{id}")
    public PokemonSpeciesResponse getPokemonSpecies(@PathVariable ("id") int id){
        return pokemonService.getPokemonSpecies(id);
    }
    /*
    @GetMapping(path = "/habitat/{id}")
    public PokemonHabitat getPokemonHabitat(@PathVariable ("id") int id){
        return pokemonService.getPokemonHabitat(id);
    }*/
}
