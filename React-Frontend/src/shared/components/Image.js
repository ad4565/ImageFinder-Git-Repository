import React from 'react';

import './Image.css';

const Image = props =>{
    return(
        <li className = "image-item">
            <div className = "image-item_content">
                <div className = "user-item_image">
                    <img src={props.url} alt =" " />
                </div>
            </div>
        </li>
    );
};

export default Image;
