import React from 'react';

// NOTE: I am probably
const Ability = (props) => {
    return (
        <div className="stat-1"><span style={{fontWeight: 700}}>{`${props.name}:`}</span> {props.value}</div>
    );
};

export default Ability;