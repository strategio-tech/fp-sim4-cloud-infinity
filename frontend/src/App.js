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

  const baseURL = "http://localhost:8080/api/pokemon/";

  const fetchPokemonData = (name) => {
    let pokemonID = pokemon.getId(name);
    let pokeData = {};
    axios.get(baseURL + pokemonID).then((res) => {
      pokeData.id = res.data.pokemonID
      pokeData.name = res.data.name;
      pokeData.height = res.data.height;
      pokeData.weight = res.data.weight;
      pokeData.types = res.data.types;
      pokeData.stats = res.data.stats;
      pokeData.abilities = res.data.abilities;
      pokeData.image = res.data.image;
      pokeData.description = res.data.description;
      pokeData.species = res.data.species;
      pokeData.is_legendary = res.data.isLegendary;
      pokeData.is_mythical = res.data.isMythical;
      pokeData.evolution_chain_URL = res.data.evolutionChainURL;
      pokeData.color = res.data.color;
      pokeData.default = false;
      console.log(pokeData);

      // FIXME: ADD CHAINS
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
