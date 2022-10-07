import React from "react";

import { Link } from "react-router-dom";

import noPokemonImg from "../assets/no-image.svg";
import { checkIfImageExists } from "../utility";

const Evolution = (props) => {
  // FIXME: Change this to store the URLs in state so that I do not have to make unncessary calls (e.g. The second pokemon image is checked 2 different times unncessarily)
  return (
    <div className="evolution">
      {/* 2 pokemon with a level in-between them. The images (sprites) are going to be passed through props */}
      <Link to={`/pokemon/${props.firstId}`}>
        <img
          className="pokemon-current-form pokemon-img"
          src={
            checkIfImageExists(props.firstImg) ? props.firstImg : noPokemonImg
          }
          alt="First Pokemon"
        ></img>
      </Link>
      <div className="level-up">
        {props.levelUp === null ? "Level ???" : `Level ${props.levelUp}`}
      </div>
      <Link to={`/pokemon/${props.secondId}`}>
        <img
          className="pokemon_evolved-form pokemon-img"
          src={
            checkIfImageExists(props.firstImg) ? props.secondImg : noPokemonImg
          }
          alt="Second Pokemon"
        ></img>
      </Link>
    </div>
  );
};

export default Evolution;
