import axios from 'axios';
import convert from 'convert-units';

export function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.substring(1).toLowerCase()
}

export const setHeight = (height) => {
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
export const setWeight = (weight) => {
    let toPounds = convert((weight * 0.1)).from('kg').to('lb');
    return `${toPounds.toFixed(0)} lbs`;
}

export const checkIfImageExists = async (url) => {
  return await axios.get(url).then((res) => {
    return res.status === 200;
  })
  .catch((error) => {
    return false;
  })
}