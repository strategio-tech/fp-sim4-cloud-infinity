import { combineReducers } from "redux";

import pokemonReducer from "./pokemon";
import { searchReducer, searchBarReducer } from "./search";

const allReducers = combineReducers({
    pokemonData: pokemonReducer, 
    search: searchReducer, 
    searchBar: searchBarReducer
});

export default allReducers;