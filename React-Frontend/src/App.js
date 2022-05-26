import React from 'react';
import {BrowserRouter as Router, Route, Redirect, Switch} from 'react-router-dom';

import ImageFinder from './image_finder/pages/ImageFinder';
import UserHome from './user/pages/UserHome';

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

      <Redirect to="/" />

    </Switch>
  </Router>
  );
}

export default App;
