import React from 'react';
import {useHistory} from 'react-router-dom'
import "./WebsitePreview.css"

const WebsitePreview = props =>{
    const history = useHistory();

    const handleClick = () =>{
        
        var imageUrls =props.imageUrls;
        imageUrls = imageUrls.split(",");
        //Removes empty last element 
        imageUrls.pop()
        
        history.push({
            pathname:"/images",
            state: {
                imageUrls: imageUrls
            }
        })
    }



    return (
    <div>
        <button onClick={handleClick}>{props.websiteTitle}</button>
    </div>)
}

export default WebsitePreview;