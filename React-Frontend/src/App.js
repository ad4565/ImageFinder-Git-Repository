import React from 'react';
import {BrowserRouter as Router, Route, Redirect, Switch} from 'react-router-dom';

import Gallery from './image_finder/pages/Gallery';
import ImageFinder from './image_finder/pages/ImageFinder';
import UserHome from './user/pages/UserHome';
import Images from './image_finder/pages/Images';


function App() {
  return (
  <Router>
    <Switch>
      
      <Route path="/" exact>
        <ImageFinder />
      </Route>

      <Route path="/home" exact>
        <UserHome />
      </Route>

      <Route path="/gallery" exact>
        <Gallery/>
      </Route>
      <Route path="/images" exact>
      <Images/>
      </Route>

      <Redirect to="/" />

    </Switch>
  </Router>
  );
}

export default App;
