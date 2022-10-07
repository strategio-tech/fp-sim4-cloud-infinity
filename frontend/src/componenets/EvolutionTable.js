import Evolution from './Evolution';

import '../css/EvolutionTable.css'

// FIXME: Stop this component from rendering 4 or 8 times. Something is going on where it renders two.
const EvolutionTable = ({ pokemonData }) => {

    let baseImageUrl = `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/`

    const getPokemonId = (url) => {
        let slashes = (findIndices(url, '/'));
        let first = slashes[slashes.length - 2];
        let last = slashes[slashes.length - 1];
        return url.substring(first + 1, last);
    }

    const findIndices = (str, char) =>
        str.split('').reduce((indices, letter, index) => {
            letter === char && indices.push(index);
            return indices;
        }, [])


    // FIXME: Refractor this code
    const evolutionTable = () => {
        let chains = [];
        if (pokemonData.chain !== undefined && pokemonData.chain.evolves_to.length !== 0) {
            let firstChain = pokemonData.chain;
            let firstPokemonId = getPokemonId(firstChain.species.url);
            let firstPokemonImg = `${baseImageUrl}${firstPokemonId}.svg`

            chains.push({
                firstImg: firstPokemonImg,
                firstId: firstPokemonId,
                secondImg: null,
                secondId: null,
                levelUp: null
            })

            let secondChain = firstChain.evolves_to;
            if (secondChain.length !== 0) {
                secondChain = firstChain.evolves_to[0];
                let secondPokemonId = getPokemonId(secondChain.species.url);
                let secondLevelUp = secondChain.evolution_details[0].min_level;
                let secondPokemonImg = `${baseImageUrl}${secondPokemonId}.svg`

                chains[0].secondImg = secondPokemonImg;
                chains[0].secondId = secondPokemonId;
                chains[0].levelUp = secondLevelUp;

                chains.push({
                    firstImg: secondPokemonImg,
                    firstId: secondPokemonId,
                    secondImg: null,
                    secondId: null,
                    levelUp: null
                })
            } else {
                chains.pop();
            }

            let thirdChain = secondChain.evolves_to;
            if (thirdChain.length !== 0) {
                let thirdChain = secondChain.evolves_to[0];
                let thirdPokemonId = getPokemonId(thirdChain.species.url);
                let thirdLevelUp = thirdChain.evolution_details[0].min_level;
                let thirdPokemonImg = `${baseImageUrl}${thirdPokemonId}.svg`

                chains[1].secondImg = thirdPokemonImg;
                chains[1].secondId = thirdPokemonId;
                chains[1].levelUp = thirdLevelUp;

            } else {
                chains.pop()
            }
            
            // Checks to see if an evolution for the pokemon exist
            if (firstChain !== undefined) {
                return (
                    <div className="evolution_table">
                        {
                            chains.map((chain, index) => (
                                <Evolution key={index} firstImg={chain.firstImg} firstId={chain.firstId} secondImg={chain.secondImg} secondId={chain.secondId} levelUp={chain.levelUp} />
                            ))
                        }
                    </div>
                )
            } else {
                return ''
            }
        }
        return ''
    }

    return evolutionTable();
};

export default EvolutionTable;