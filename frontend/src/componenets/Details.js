import { useSelector } from 'react-redux';

import Section from './Section';

import EvolutionTable from './EvolutionTable'

const Details = (props) => {

    const pokemonData = useSelector(state => state.pokemonData);

    return (
        <div id="info">
            <p className="pokemon-description">
                {pokemonData.description}
            </p>
            <Section pokemonData={pokemonData}/>
            <EvolutionTable pokemonData={pokemonData} />
            {/* TODO: Add more sections here */}
        </div>
    );
};

export default Details;