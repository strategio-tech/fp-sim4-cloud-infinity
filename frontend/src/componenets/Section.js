import { useDispatch } from 'react-redux';
import { useNavigate, useParams } from 'react-router';
import { showSearchBar } from '../actions/searchAction';

import right_arrow from '../assets/right_arrow.svg';
import { setHeight, setWeight } from '../utility';

const Section = ({ pokemonData }) => {

    const navigate = useNavigate();
    const { id } = useParams();
    const dispatch = useDispatch();

    const openDetails = () => {
        dispatch(showSearchBar(false));
        navigate(`/pokemon/${id}/stats/details`);
    }

    return (
        // TODO: Add some global styles to all of my sections. This is where I am going to put my spacing (container) logic so they line up correctly on mobile, tablet, and desktop.
        <div className="section">
            <h1 className="section-header">Stats</h1>
            <div className="stats-table">
                {/* The "in-table" class is to help add additional styling to these "stat" divs. This style is being reused in places of my app. */}
                <div className="stat in-table">
                    <p className="stat-title">Height</p>
                    <p className="stat-data">{`${setHeight(pokemonData.height)}`}</p>
                </div>
                <div className="stat in-table">
                    <p className="stat-title">Weight</p>
                    <p className="stat-data">{`${setWeight(pokemonData.weight)}`}</p>
                </div>
                <div id="more-info" onClick={openDetails}>
                    <img src={right_arrow} alt="right arrow"></img>
                </div>
            </div>
        </div>
    );
};

export default Section;