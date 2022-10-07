import logo from '../assets/logo.svg'
import back_arrow from '../assets/back_arrow.svg';
import { useNavigate, useLocation } from 'react-router';
import { useDispatch, useSelector } from 'react-redux';
import { showSearchBar } from '../actions/searchAction';
import { setPokemonData } from '../actions/pokemonAction';

const NavBar = (props) => {

    const location = useLocation();
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const pokemonData = useSelector(state => state.pokemonData);

    // Handles my routing system to go back to the previous page
    const goBack = () => {
        let currPath = location.pathname;
        switch (currPath.toLowerCase()) {
            case `/`:
                break;

            case `/pokemon/${pokemonData.id}`:
                navigate('/');
                resetData();
                break;

            case `/pokemon/${pokemonData.name}`:
                navigate('/');
                resetData();
                break;

            case `/pokemon/${pokemonData.id}/stats/details`:
                dispatch(showSearchBar(true));
                navigate(`/pokemon/${pokemonData.id}`);
                break;

            case `/pokemon/${pokemonData.name}/stats/details`:
                dispatch(showSearchBar(true));
                navigate(`/pokemon/${pokemonData.name}`);
                break;

            // By default, it sends you the page you just came from to handle edge cases
            default:
                navigate.goBack();
                break;
        }
    }

    // TODO: Remove 'props.setPokemonData' once Redux is implemented
    const resetData = () => {
        dispatch(setPokemonData({
            default: true,
            name: 'Loading...',
            id: 900,
            types: [],
            height: 0,
            weight: 0,
            sprites: [],
            image: null,
            description: 'This is the ilusive pokemon that never appears.',
            evolution_chain_URL: null
          }));
      }

    return (
        <nav>
            {
                location.pathname === '/' ? <img className="logoImg" src={logo} alt='pokeball logo'></img>
                    :
                    <div id="back" onClick={() => goBack()}>
                        <img id="back_arrow" src={back_arrow} alt="back button"></img>
                        <img className="logoImg logoImg_diff" src={logo} alt='pokeball logo'></img>
                    </div>
            }
            <p id="logoText">Pokedex</p>
        </nav>
    );
};

export default NavBar;