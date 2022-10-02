import pokemon from "pokemon";
import axios from "axios";

import NavBar from "./componenets/NavBar";

import Home from "./pages/Home.js";
import Pokemon from "./pages/Pokemon.js";
import Stats from "./pages/Stats.js";

import "./css/App.css";
import { Route, Routes, useNavigate, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { setPokemonData } from "./actions/pokemonAction";
import { setSearch } from "./actions/searchAction";
import { capitalize } from "./utility";

const App = () => {
  
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();

  const search = useSelector((state) => state.search);
  const searchBar = useSelector((state) => state.searchBar);

  const baseNormalURL = "https://pokeapi.co/api/v2/pokemon/";
  const baseSpeciesURL = "https://pokeapi.co/api/v2/pokemon-species/";

  const fetchPokemonData = (name) => {
    let pokemonID = pokemon.getId(name);
    let pokeData = {};
    axios
      .all([
        axios.get(`${baseNormalURL}${pokemonID}`),
        axios.get(`${baseSpeciesURL}${pokemonID}`),
      ])
      // TODO: Change this to just store all of the data in state (e.g. pokeData.data = [...data1, ...data2])
      .then(
        axios.spread((data1, data2) => {
          
          pokeData.name = data1.data.name;
          pokeData.names = data1.data.names;
          pokeData.id = data1.data.id;
          pokeData.types = data1.data.types;
          pokeData.height = data1.data.height;
          pokeData.weight = data1.data.weight;
          pokeData.stats = data1.data.stats;
          pokeData.base_experience = data1.data.base_experience;
          pokeData.sprites = data1.data.sprites;
          pokeData.image = data1.data.sprites.other.dream_world.front_default;
          pokeData.abilities = data1.data.abilities;
          pokeData.stats = data1.data.stats;

          // Gets the description in English. Some pokemon have descriptions that are not
          // English first. This goes through the array and finds the first description set that is
          // in English.
          pokeData.description = data2.data.flavor_text_entries.find((set) => {
            return set.language.name === "en";
          }).flavor_text; // description of our pokemon
          pokeData.descriptions = data2.data.flavor_text_entries;
          pokeData.species = data2.data.genera.find((set) => {
            return set.language.name === "en";
          }).genus;
          pokeData.speciesSet = data2.data.genera;
          pokeData.is_legendary = data2.data.is_legendary;
          pokeData.is_mythical = data2.data.is_mythical;
          pokeData.evolution_chain_URL = data2.data.evolution_chain.url;

          pokeData.habitat = data2.data.habitat;
          pokeData.generation = data2.data.generation;
          pokeData.color = data2.data.color.name;

          return axios.get(pokeData.evolution_chain_URL);
        })
      )
      .then((response) => {
        pokeData.chain = response.data.chain;
        pokeData.default = false;
        dispatch(setPokemonData(pokeData));
        dispatch(setSearch(""));
      });
  };

  const onSubmitSearch = (event) => {
    event.preventDefault();
    event.stopPropagation();
    if (search) {
      submitSearch(null, search, true);
    }
  };

  // Sets 'search' to be the default value for text
  const submitSearch = (event, text = search, input = true) => {
    if (text === "") {
      return;
    }
    if (!isNaN(text)) {
      let id = parseInt(text);
      if (id > 0 && id < 906) {
        let name = pokemon.getName(parseInt(text));
        fetchPokemonData(name);
        navigate(`/pokemon/${id}`);
      } else {
        if (input) {
          alert("This pokemon does not exist");
          dispatch(setSearch(""));
        } else {
          // Takes us to the homepage if the pokemon does not exist
          navigate("/");
        }
      }
    } else {
      text = capitalize(text);
      // Checks to see if the pokemon actually exist
      if (pokemon.all().includes(text)) {
        fetchPokemonData(text);
        navigate(`/pokemon/${text.toLowerCase()}`);
      } else {
        if (input) {
          alert("This pokemon does not exist");
          dispatch(setSearch(""));
        } else {
          // Takes us to the homepage if the pokemon does not exist
          navigate("/");
        }
      }
    }
  };

  return (
    <div
      id="app"
      className={`${location.pathname === "/" ? "start_color" : ""}`}
    >
      <NavBar />
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route
          exact
          path="/pokemon/:id"
          element={<Pokemon submitSearch={submitSearch} />}
        />
        <Route exact path="/pokemon/:id/stats/:stat" element={<Stats />} />
      </Routes>

      {/* Search bar section */}
      <div
        style={{ display: searchBar ? "flex" : "none" }}
        className={`end ${location.pathname === "/" ? "start_color" : ""}`}
      >
        {/* NOTE: When you click the submit button, what triggers a search for the pokemon is the submit search function right here. */}
        <form id="searchBar" onSubmit={onSubmitSearch}>
          <input
            id="searchText"
            type="text"
            placeholder='Try typing "235" or "Entei"'
            value={search}
            onChange={(event) => dispatch(setSearch(event.target.value))}
          />
          <button type="submit" id="searchBtn">
            Search
          </button>
        </form>
      </div>
    </div>
  );
};

export default App;
