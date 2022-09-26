import React from 'react';

const Stat = (props) => {
    return (
        <div className="stat-1"><span style={{fontWeight: 700}}>{`${props.name}:`}</span> {props.value}</div>
    );
};

export default Stat;