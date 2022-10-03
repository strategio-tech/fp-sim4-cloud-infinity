import React, { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

import convert from 'convert-units';

import Stat from '../componenets/stats/Stat';

import '../css/Stats.css';
import { useDispatch, useSelector } from 'react-redux';
import { showSearchBar } from '../actions/searchAction';
import { capitalize } from '../utility';

const Stats = (props) => {

    const navigate = useNavigate();
    const params = useParams();
    const dispatch = useDispatch();

    const pokemonData = useSelector(state => state.pokemonData);

    const paths = ['details'];

    const setHeight = (height) => {
        let final = '';
        let feet = 0;
        let toInches = convert((height * 10)).from('cm').to('in'); 
        if(toInches => 12) {
          feet = (toInches / 12);
          final = `${feet.toFixed(1)} ft`;
          return final;
        }
        return `${toInches.toFixed(0)} inches`;
      }

    // Converting weight from hectograms to kilograms to pounds.
    const setWeight = (weight) => {
        let toPounds = convert((weight * 0.1)).from('kg').to('lb');
        return `${toPounds.toFixed(0)} lbs`;
    }

    const getAbilities = () => {
        let final = '';
        pokemonData.abilities.map((item, index) => {
            final += `${capitalize(item.name)}${item.is_hidden ? " (Hidden Ability), " : ", "}`;
            if(index === pokemonData.abilities.length - 1) {
                final = final.substring(0, final.length - 2);
            }
            return final
        })
        return final;
    }

    const fixName = word => {
        let final = word.replace('-', ' ');
        return final.split(' ').map(str => capitalize(str)).join(' ');
    }

    useEffect(()=> {
        dispatch(showSearchBar(false));
        // NOTE: Redo this routing because I am not even sure what I was doing lol
        // TODO: Make it so that these pages are not accessible unless you have actually search for OR opened a link directly to a pokemon (state has non-default data in it)
        if(!Number.isNaN(parseInt(params.stat))) {
            if(pokemonData.default) {
                navigate(`/`);
            } else {
                navigate(`/pokemon/${params.id}`);
            }
        }
        if(!paths.includes(params.stat.toLowerCase())) {
            dispatch(showSearchBar(true));
            navigate('/');
        }
       // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <div id="stats" className='page'>
            <div className="stat-section">
                <h1 className={`stat-section__header ${pokemonData.color}_header`}>Pokemon Data</h1>
                <Stat name="Height" value={setHeight(pokemonData.height)}/>
                <Stat name="Weight" value={setWeight(pokemonData.weight)}/>
                <Stat name="Species" value={pokemonData.species}/>
                {
                    pokemonData.abilities.length > 0 ? <Stat name="Abilities" value={getAbilities()}/> : ''
                }
            </div>
            <div className="stat-section">
                <h1 className={`stat-section__header ${pokemonData.color}_header`}>Stats</h1>    
                {
                    pokemonData.stats.map((item, index) => (
                        <Stat key={index} name={fixName(item.name)} value={`${item.base_stat} (base)`} />
                    ))
                }
            </div>

            {/* {
                item.name === "" ? "" : <img src={item.image.url} alt={item.name} />
            } */}
        </div>
    );
};

export default Stats