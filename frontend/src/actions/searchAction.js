const SET_SEARCH = "SET_SEARCH";
const SHOW_SEARCH = "SHOW_SEARCH";

export const setSearch = (name) => {
    return {
        type: SET_SEARCH,
        payload: name
    }
}

export const showSearchBar = (show) => {
    return {
        type: SHOW_SEARCH,
        payload: show
    }
}