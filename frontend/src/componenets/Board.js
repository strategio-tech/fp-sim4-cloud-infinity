import PokemonInfo from './PokemonInfo';
import Details from './Details';

// This may be unnecessary. I need to first map out how I want to pass my props through so that I will not have to use extra components
const Board = (props) => {
    return (
        <div id="board">
            <PokemonInfo />
            <Details />
        </div>
    );
};

export default Board;