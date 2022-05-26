import React from 'react';

import './SearchBar.css'
const SearchBar = ({input, setInput}) =>{

    return (
        <input
        value = {input}
        placeholder = {"Enter a URL to search."}
        onChange = {(e) => setInput(e.target.value)}/>
    );

}

export default SearchBar;