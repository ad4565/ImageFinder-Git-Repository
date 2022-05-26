import React, { Component, useRef } from 'react';

import Image from '../../shared/components/Image';
import ImageGallery from 'react-image-gallery';
import './ImageList.css';





const ImageList = props =>{

const getCurrentIndex = () =>{console.log(props.indexRef.current.state.currentIndex)}
    
    var imageGalleryRef = useRef();    
    if(props.images.length === 0){
        return (<div className="center">
            <h2>No Images Found.</h2>
        </div>);
    }


    return (
    <ImageGallery items = {props.images} ref= {props.indexRef} onPlay={getCurrentIndex}   />
    );


}

export default ImageList;