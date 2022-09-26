import { useSelector } from 'react-redux';

import Tag from './Tag';

import '../css/PokemonInfo.css'

import noPokemonImg from '../assets/no-image.svg';
import { capitalize } from '../utility';
import Badge from './Badge';

const PokemonInfo = (props) => {

    const pokemonData = useSelector(state => state.pokemonData);

    const setId = (id) => {
        switch (id.toString().length) {
            case 1:
                return `#00${id}`
            case 2:
                return `#0${id}`
            case 3:
                return `#${id}`
            default:
                return `#${id}`
        }
    }

    return (
        <div className={`pokemon-info ${pokemonData.color}`}>
            <div className={`container`}>
                <div className='identity'>
                    <div className="identity_left-side">
                        <h1 className='pokemon-name'>{capitalize(pokemonData.name)}</h1>
                        <Badge isLegendary={pokemonData.is_legendary} />
                        <Badge isMythical={pokemonData.is_mythical} />
                    </div>
                    <div className="identity_right-side">
                        <p className='pokemon-id'>{setId(pokemonData.id)}</p>
                    </div>
                </div>
                <div className="tag-container">
                    {
                        // Gets only the first 2 tags out of the types
                        pokemonData.types.slice(0, 2).map((tag, index) => (
                            <Tag key={index} type={tag.type.name} />
                        ))
                    }
                </div>
                <img className="pokemon-image" src={pokemonData.image ? pokemonData.image : noPokemonImg} alt={pokemonData.name}></img>
            </div>
        </div>
    );
};

export default PokemonInfo;