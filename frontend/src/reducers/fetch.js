export const fetchReducer = (state = false, action) => {
    switch (action.type) {
        case 'FETCH_SUCCESS':
            return action.payload;

        case 'FETCH_FAILURE':
            return action.payload;

        default:
            return state;
    }
}
