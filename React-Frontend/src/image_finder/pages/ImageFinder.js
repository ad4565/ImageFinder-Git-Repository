import React, {useState, useRef} from 'react';
import { useHistory } from "react-router-dom";

import SearchBar from '../components/SearchBar'
import ImageList from '../components/ImageList'
import './ImageFinder.css'

const axios = require('axios').default;

const ImageFinder = () => {

    const history = useHistory();
    var imageIndexRef = useRef();
    const [input, setInput] = useState('');
    const [images, setImages] = useState([]);
    const [statusMessage, setStatusMessage] = useState('Enter Link to Search for Photos');
    const IMAGES = [{original: "https://metsmerizedonline.com/wp-content/uploads/2022/05/0P9A4430-scaled-e1652458490746.jpg"}, {original: "https://metsmerizedonline.com/wp-content/uploads/2022/05/0P9A4430-scaled-e1652458490746.jpg"}];

    const saveImage = async() =>{

        const currentImageIndex = imageIndexRef.current.state.currentIndex;

         await axios.post('http://localhost:8080/api/save-image', {url: images[currentImageIndex].url, titleOfHome: images[currentImageIndex].titleOfHome})

       // await axios.post('http://localhost:8080/api/save-image', {url: "https://metsmerizedonline.com/wp-content/uploads/2022/05/0P9A4430-scaled-e1652458490746.jpg", titleOfHome: "mets"});
        //console.log(currentImageIndex);
    }

    const getImages = async() =>{
        setStatusMessage("Searching for Photos...")

        if(!input.includes("https") && !input.includes("http"))
        {
            setStatusMessage("Invalid URL! Please enter the full URL. EX: https://mlb.com")
            return;

        }
        var response = await axios.post('http://localhost:8080/api/images',  {"url": input} );

        console.log(response);
        var imageData = []

        response.data.forEach(e => {
            imageData.push({url: e.url, titleOfHome: e.titleOfHome});
        });
    
        setImages(imageData);
    }

    const redirectToGallery = async() =>{

        var response = await axios.get("http://localhost:8080/api/get-images");
        const array = response.data;
        history.push({
            pathname: "/gallery",
            state :{
                savedWebsiteData: array
            }
        })
    }
  
   



    return (
    <div>
        <div className = "search-bar">
            <SearchBar input = {input} setInput = {setInput}/>
            <button type= "button" onClick={getImages}>Search</button>
            <button type= "button" onClick={saveImage}>Save Current Image</button>
            <button type= "button" onClick={redirectToGallery}>Go to Gallery</button>
        </div>
        <div className = "images">
            <ImageList images={images} indexRef = {imageIndexRef} statusMessage = {statusMessage} ></ImageList>
         </div>

    </div>);
}

export default ImageFinder;