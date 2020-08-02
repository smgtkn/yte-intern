import React from"react";
import PropTypes from "prop-types";
import {Link} from "react-router-dom";

function Navbar(props) {
	
	return (<div style={{backgroundColor:"#968A8A",height:"150px"}} >
		<h3 >Etkinlikleri Yönet </h3>
		<ul>
		<li> <Link to="/" style={{color:"black",fontSize:"25px"}} >Etkinlikler </Link></li>
		<li> <Link to ="/ekle" style={{color:"black",fontSize:"25px"}}> Etkinlik Ekle </Link></li>
		
		</ul></div>)}
Navbar.propTypes= {
	title: PropTypes.string.isRequired
	
}
Navbar.defaultProps= {
	title:"Defa:ult App"
}

export default Navbar;