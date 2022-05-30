import React, { Component, useRef } from 'react';

import ImageGallery from 'react-image-gallery';
import './ImageList.css';





const ImageList = props =>{

const getCurrentIndex = () =>{console.log(props.indexRef.current.state.currentIndex)}
    
    var imageGalleryRef = useRef();    
    if(props.images.length === 0){
        return (<div className="center">
            <h2>{props.statusMessage}.</h2>
        </div>);
    }

    var items = []
    props.images.forEach(element => {
        items.push({original: element.url});
    });
    return (
    <ImageGallery items = {items} ref= {props.indexRef} onPlay={getCurrentIndex}   />
    );


}

export default ImageList;