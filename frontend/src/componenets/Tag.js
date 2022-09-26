import '../css/Tag.css'
import { capitalize } from '../utility';

const Tag = (props) => {
    return (
        // An example: grass-tag, poison-tag, electric-tag
        <div className={`tag ${props.type}-tag`}>
            {/* This right here capitalizes the types since they are passed in lowercase */}
            <p className="tag-text">{capitalize(props.type)}</p>
        </div>
    );
};

export default Tag;