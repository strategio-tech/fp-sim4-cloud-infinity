const SET_POKEMON = 'SET_POKEMON';

export const setPokemonData = (name) => {
    return {
        type: SET_POKEMON,
        payload: name
    }
}