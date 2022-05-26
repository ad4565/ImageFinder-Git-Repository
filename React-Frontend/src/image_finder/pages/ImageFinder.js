import React, {useState, useRef} from 'react';

import SearchBar from '../components/SearchBar'
import ImageList from '../components/ImageList'
import './ImageFinder.css'

const axios = require('axios').default;

const ImageFinder = () => {

   
    var imageIndexRef = useRef();
    const [input, setInput] = useState('');
    const [images, setImages] = useState([]);
    const IMAGES = [{original: "https://metsmerizedonline.com/wp-content/uploads/2022/05/0P9A4430-scaled-e1652458490746.jpg"}, {original: "https://metsmerizedonline.com/wp-content/uploads/2022/05/0P9A4430-scaled-e1652458490746.jpg"}];

    const saveImage = async() =>{

        //const currentImageIndex = imageIndexRef.current.state.currentIndex;

        //var response = await axios.post('http://localhost:8080/api/save-image', {"url": IMAGES[currentImageIndex].original})

        await axios.post('http://localhost:8080/api/save-image', {url: "https://metsmerizedonline.com/wp-content/uploads/2022/05/0P9A4430-scaled-e1652458490746.jpg", titleOfHome: "mets"});
        //console.log(currentImageIndex);
    }

    const getImages = async() =>{
        var response = await axios.post('http://localhost:8080/api/images',  {"url": input} );

        console.log(response);
        var imageUrls = []

        response.data.forEach(e => {
            imageUrls.push({original: e.url});
        });

        setImages(imageUrls);

        
    }

    return (
    <div>
        <div className = "search-bar">
            <SearchBar input = {input} setInput = {setInput}/>
            <button type= "button" onClick={getImages}>Search</button>
            <button type= "button" onClick={saveImage}>Save Current Image</button>
        </div>
        <div className = "images">
            <ImageList images={images} indexRef = {imageIndexRef} />
         </div>

    </div>);
}

export default ImageFinder;