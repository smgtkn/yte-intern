import React from 'react';


import './App.css';
import UpdateUser from "./component/UpdateUser";
import Users from "./component/Users";
import Navbar from "./component/Navbar";

import AddUser from "./component/AddUser";
import NotFound from "./component/NotFound";
import {BrowserRouter as Router,Route,Switch} from "react-router-dom";

const Home= ()=> {
	
	return <h3> HOME PAGE </h3>
}
const About= ()=> {
	
	return <h3> ABOUT PAGE </h3>
}
function App (){

	
	
 return (
	<Router>
    <div className="container">

   <Navbar title="Etkinlikleri Yönet"></Navbar>
    <hr/>
<Switch>
    <Route  exact path ="/" component={Users}/>
  <Route exact path="/guncelle" component={UpdateUser}/>
  <Route exact path ="/ekle" component={AddUser}/>
  <Route component={NotFound} />
</Switch>
    </div>
</Router>
)}

export default App;
