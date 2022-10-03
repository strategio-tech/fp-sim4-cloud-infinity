package com.cloudinfinity.pokemonapi.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cloudinfinity.pokemonapi.model.Ability;
import com.cloudinfinity.pokemonapi.model.Pokemon;
import com.cloudinfinity.pokemonapi.model.Stat;
import com.cloudinfinity.pokemonapi.model.Type;

public class ThirdParty {

    public static void main(String[] args) {
        getPokemon(244);
    }

    private static String baseURL = "https://pokeapi.co/api/v2/pokemon/";
    private static String baseSpeciesURL = "https://pokeapi.co/api/v2/pokemon-species/";

    public static Pokemon getPokemon(long id) {

        Pokemon pokemon = new Pokemon();

        try {
            JSONObject basePokemon = getData(baseURL + id);

            String name = (String) basePokemon.get("name");
            long weight = (long) basePokemon.get("weight");
            long height = (long) basePokemon.get("height");

            JSONArray stats = (JSONArray) basePokemon.get("stats");
            JSONArray types = (JSONArray) basePokemon.get("types");
            JSONArray abilities = (JSONArray) basePokemon.get("abilities");

            pokemon.setPokemonID(id);
            pokemon.setName(name);
            pokemon.setWeight(weight);
            pokemon.setHeight(height);

            for (int i = 0; i < stats.size(); i++) {

                JSONObject stat_obj = (JSONObject) stats.get(i);
                JSONObject stat = (JSONObject) stat_obj.get("stat");
                String stat_name = (String) stat.get("name");
                long base_stat = (long) stat_obj.get("base_stat");
                long effort = (long) stat_obj.get("effort");

                Stat newStat = new Stat();

                newStat.setName(stat_name);
                newStat.setBaseStat(base_stat);
                newStat.setEffort(effort);

                pokemon.getStats().add(newStat);
            }

            for (int i = 0; i < types.size(); i++) {
                JSONObject type_obj = (JSONObject) types.get(i);
                JSONObject type = (JSONObject) type_obj.get("type");
                String type_name = (String) type.get("name");
                long slot = (long) type_obj.get("slot");

                Type newType = new Type();

                newType.setName(type_name);
                newType.setSlot(slot);

                pokemon.getTypes().add(newType);
            }

            for (int i = 0; i < abilities.size(); i++) {
                JSONObject ability_obj = (JSONObject) abilities.get(i);
                JSONObject ability = (JSONObject) ability_obj.get("ability");
                String ability_name = (String) ability.get("name");
                boolean isHidden = (boolean) ability_obj.get("is_hidden");
                long slot = (long) ability_obj.get("slot");

                Ability newAbility = new Ability();

                newAbility.setName(ability_name);
                newAbility.setIsHidden(isHidden);
                newAbility.setSlot(slot);

                pokemon.getAbilities().add(newAbility);
            }

            JSONObject sprites = (JSONObject) basePokemon.get("sprites");
            JSONObject other = (JSONObject) sprites.get("other");
            JSONObject dream_world = (JSONObject) other.get("dream_world");
            String image = (String) dream_world.get("front_default");

            pokemon.setImage(image);


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        try {
            JSONObject basePokemon = getData(baseSpeciesURL + id);

            JSONObject color_obj = (JSONObject) basePokemon.get("color");
            String color = (String) color_obj.get("name");

            pokemon.setColor(color);

            JSONObject evolution_obj = (JSONObject) basePokemon.get("evolution_chain");
            String evolution_chain_URL = (String) evolution_obj.get("url");

            pokemon.setEvolutionChainURL(evolution_chain_URL);

            boolean is_legendary = (boolean) basePokemon.get("is_legendary");
            boolean is_mythical = (boolean) basePokemon.get("is_mythical");

            pokemon.setIsLegendary(is_legendary);
            pokemon.setIsMythical(is_mythical);

            JSONArray descriptions = (JSONArray) basePokemon.get("flavor_text_entries");

            JSONObject description_obj = (JSONObject) descriptions.stream().filter(des -> {
                JSONObject des_obj = (JSONObject) des;
                JSONObject language = (JSONObject) des_obj.get("language");
                String language_name = (String) language.get("name");
                if (language_name.equals("en")) {
                    return true;
                }
                return false;
            }).findFirst().orElse(null);

            String description = (String) description_obj.get("flavor_text");

            pokemon.setDescription(description);

            JSONArray species = (JSONArray) basePokemon.get("genera");

            JSONObject species_obj = (JSONObject) species.stream().filter(spec -> {
                JSONObject spec_obj = (JSONObject) spec;
                JSONObject language = (JSONObject) spec_obj.get("language");
                String language_name = (String) language.get("name");
                if (language_name.equals("en")) {
                    return true;
                }
                return false;
            }).findFirst().orElse(null);

            String species_name = (String) species_obj.get("genus");

            pokemon.setSpecies(species_name);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    public static JSONObject getData(String URL) throws IOException, ParseException {

        URL url = new URL(URL);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // Getting the response code
        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            scanner.close();

            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            return data_obj;
        }
    }
}