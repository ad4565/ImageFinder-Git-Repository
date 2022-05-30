import React from 'react'
import { useHistory } from "react-router-dom";

import ImageGallery from 'react-image-gallery'

const Images = () =>{
    const history = useHistory();

    const imageUrls = history.location.state.imageUrls

    var imageGalleryItems = []

    imageUrls.forEach(e => {
        imageGalleryItems.push({original: e})
        
    });

    return (<ImageGallery items = {imageGalleryItems} />)
}

export default Images;