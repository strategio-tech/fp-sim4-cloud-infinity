export const searchReducer = (state = '', action) => {
    switch(action.type) {
        case 'SET_SEARCH':
            return action.payload;
        
        default: 
            return state;
    }
}

export const searchBarReducer = (state = true, action) => {
    switch(action.type) {
        case 'SHOW_SEARCH':
            return action.payload;
            
        default:
            return state;
    }
}