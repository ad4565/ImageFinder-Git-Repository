import React from 'react';
import { useHistory } from "react-router-dom";


import WebsitePreview from '../components/WebsitePreview'






const Gallery = () =>{
    const history = useHistory();

    const savedWebsiteData = history.location.state.savedWebsiteData;

    var websitePreviews = []
    for(var i = 0; i < savedWebsiteData.length; i ++){
        websitePreviews.push(<WebsitePreview websiteTitle = {savedWebsiteData[i].websiteTitle} imageUrls = {savedWebsiteData[i].imagesUrls}/>)
    }


    return (
        <div>
               {websitePreviews}
        </div>
        )
    
  
    


}
export default Gallery;
