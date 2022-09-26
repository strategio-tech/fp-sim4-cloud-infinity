import pokeball from '../assets/pokeball.svg'
import pokemonLogo from '../assets/pokemon_logo.svg'

const Home = (props) => {

    return (
        <div className="home page start-screen start_color">
            <img className="pokemon_logo" src={pokemonLogo} alt="The official pokemon logo"></img>
            <img className="pokemon_ball" src={pokeball} alt="A pokeball"></img>
            <h1 className="pokedex">Pokedex</h1>
        </div>
    );
};

export default Home;