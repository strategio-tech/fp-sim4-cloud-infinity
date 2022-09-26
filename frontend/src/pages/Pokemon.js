import React, { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

import { useSelector } from 'react-redux';

import Board from '../componenets/Board';

const Pokemon = (props) => {

    const navigate = useNavigate();
    const { id } = useParams();

    const pokemonData = useSelector(state => state.pokemonData);

    // Handles loading pokemon if someone directly links to this page
    useEffect(() => {
        // Check to see if the state in App.js is default. If so, we fetch a new pokemon
        if(pokemonData.default) {
            // NOTE: Gets the pokemon based on the current id (we get this from React Router);
            props.submitSearch(null, id, false);
        } else {
            // In case we already have pokemon data loaded in our state,
            // We check to see if it is the same as the id (in the search bar).
            // If not, we are going to fetch the new data

            if(!Number.isNaN(id)) {
                if(pokemonData.id !== parseInt(id)) {
                    props.submitSearch(null, id, false);
                }
            } else if(typeof id === 'string') {
                if(pokemonData.name.toLowerCase() !== id.toLowerCase()) {
                    props.submitSearch(null, id.toLowerCase(), false);
                }
            } else {
                navigate('/');
            }
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [id]);
    
    return (
        <div className="pokemon page">
            <Board />
        </div>
    );
};

export default Pokemon;