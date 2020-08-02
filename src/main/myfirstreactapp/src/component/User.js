import React, {Component} from 'react';
import PropTypes from "prop-types";
import UserConsumer from "../context";
import axios from "axios";
import {Link} from "react-router-dom"
class User extends Component {
	static defaultProps={
		name:"bilgi yok",
		start: "bilgi yok",
		end:"bilgi yok",
		id:""
		
}	
state= {isVisible:false,
error3:false}
	/*constructor (props) {
		super(props);
		this.state= {
		isVisible:false
			
		}
		
	}*/
	onDeleteUser =async (dispatch,e)=>{
	const{id,name,start,end}=this.props;
	const{error3}=this.state;
	

	
	//DElete request 
	
	if (this.checkError3()){
	alert("Başlangıç tarihi geçmiş etkinliği silemezsiniz!");
		return;
	}
	await axios.delete(`http://localhost:8080/delete/${name}`);
//consumer Dispatch
   
	dispatch({type: "DELETE_USER",payload:name});
	}
	
	onClickEvent=(e)=>{
   this.setState({
	isVisible:!this.state.isVisible
	
})
	}
	checkError3 =(e)=> {
		const{start}=this.props;
		const{error3}=this.state;
	let date=new Date();
	if(date.getFullYear()>parseInt(start.slice(6,10)) ||(date.getFullYear()===parseInt(start.slice(6,10))&& date.getMonth()>parseInt(start.slice(3,5)))  ||(date.getFullYear()===parseInt(start.slice(6,10)) &&date.getMonth()===parseInt(start.slice(3,5))&&date.getMonth()>parseInt(start.slice(0,2)) )){
		this.setState({error3:true})
		return true;
	}
	return false;
	}
	render() {
		//destructing javascriptin özelliğidir
		//this.props.özellik yerine sadece özelliği yazmamızı sağlar
	
        	
		const {name,start,end,id}=this.props;
		const {isVisible,error3}=this.state;
		return (
			<UserConsumer >{
				value => {
					const {dispatch}=value;
						return (
			<div>
			<div className="col-md-8 mb-4">
			<div className="card"  style={isVisible?{backgroundColor:"#968A8A"}:null}>
			<div className="card-header d-flex justify-content-between">
			<h4 className="d-inline" onClick={this.onClickEvent}> {name}</h4>
			<i onClick={this.onDeleteUser.bind(this,dispatch)}className="	fa fa-trash-o" aria-hidden="true" style={{cursor :"pointer"}}>
	
			
			</i>
			
		
			</div>
		   {isVisible?<div className="card-body">
			
			<p className="card-text" > Etkinlik Adı: {name}</p><p className="card-text" > Başlangıç Tarihi: {start}</p><p className="card-text" > Bitiş Tarihi: {end}</p>
		<Link to={`/guncelle`} className="btn btn-dark btn-block" type="submit">GÜNCELLE</Link>
			</div>:null }
			
			
			</div>
			</div>
			
			</div>
			
			
		)}}</UserConsumer>
		)
	
		
		
		
	}
	
	
}
//User.propTypes  = {
//	
//	name:PropTypes.string.isRequired,
//	start: PropTypes.string.isRequired,
//	end:PropTypes.string.isRequired,
//
//	
//}

export default User;