import legendaryBadge from '../assets/legendary.svg';
import mythicalBadge from '../assets/myth.svg';


const Badge = ({ isLegendary, isMythical }) => {

    if(isLegendary) {
        return (<img className="legendary" src={legendaryBadge} alt="legendary badge"></img>)
    }

    if(isMythical) {
        return (<img className="mythical" src={mythicalBadge} alt="mythical badge"></img>)
    }

    return null;
};

export default Badge;