import React,{Component} from "react";
import posed from "react-pose";
import UserConsumer from "../context"
import axios from "axios";

	const Animation= posed.div(
		{
			visible:{
				opacity:1,
				applyAtStart :{ display:"block"}
			},
			hidden: {
				opacity:0,
				applyAtEnd: {display:"none"}
			}
			
			
		}
		
	);

class AddUser extends Component {
	state= {
		visible: true,
		name:"",
		start:"",
		end:"",
		error:""}
	changeVisibility =(e) => {
		this.setState({
			visible : !this.state.visible
			
		})
		
	}
	validateForm=()=> {
		const {name,end,start}=this.state
		let yil1=parseInt(start.slice(6,10));
		let yil2=parseInt(end.slice(6,10));
		let ay1=parseInt(start.slice(3,5));
		//console.log(parseInt(start.slice(3,5)));
		let ay2=parseInt(end.slice(3,5));
		let gun1=parseInt(start.slice(0,2));
		
		let gun2=parseInt(end.slice(0,2));
		if( yil1> yil2||(yil1===yil2 && ay1>ay2 )|| (yil1===yil2 && ay1===ay2 && gun1>gun2) ){
			
			return false;
		}
		
		return true;
		
		
	}
	changeInput =(e)=>{

		this.setState({
			
			[e.target.name] :e.target.value 
		})
	}
	
//		changeSalary =(e) =>{
//		
//		
//		this.setState({salary:e.target.value})
//	}	
//	changeDepartment =(e) =>{
//		
//		this.setState({department:e.target.value})
//		
//	}
//	changeName =(e) =>{
//		
//		this.setState({name:e.target.value})
//		
//	}
addUser=async (dispatch,e) => {
	e.preventDefault();
	const {name,end,start}=this.state;
	const newUser={
		name:name,
		end:end,
		start: start
	
	}
	if(this.validateForm()===false){
		this.setState({error:true})
		return;
	}
	else this.setState({error:false})
	const response =await axios.post("http://localhost:8080/addEtkinlik",newUser);
	dispatch({type:"ADD_USER",payload:response.data})
  //  this.props.history.push("/");	
}
	render (){
		
		const {error,name,end,start,visible}=this.state;
		return <UserConsumer>{value=>{const {dispatch}=value;return(
			<div className="col-md-8 mb-4">
			<button onClick={this.changeVisibility} className="btn btn-dark btn-block mb-2">{visible ?"Formu Gizle":"Formu Göster"} </button>
			    <Animation pose={visible?"visible":"hidden"}>
                 <div className="card" style={{backgroundColor:"#968A8A"}}>
                 <div className="card-header"> 
                    <h4>Etkinlik Ekleme Formu</h4>
                </div>

                <div className="card-body">
{
	error===true?<div className="alert alert-danger">
	Lütfen tarihleri kontrol ediniz.
	</div>:(error===false?<div className="alert alert-success alert-dismissible fade show">İşlem başarıyla tamamlandı </div> :null)
	
}
                <form onSubmit={this.addUser.bind(this,dispatch)}>
                <div className="from-group" >
                <label htmlFor="name">Etkinlik Adı</label>
                <input id="name" type="text" onChange={this.changeInput} value ={name} name="name" placeholder="Etkinlik Adı Giriniz" className="form-control"/>
                </div>    
                    <div className="from-group" >
                <label htmlFor="start">Başlangıç Tarihi</label>
                <input id="start" onChange={this.changeInput} type="text" value={start} name="start" placeholder="gg/aa/yyyy" className="form-control"/>
                </div>  
                   <div className="from-group" >
                <label htmlFor="end">Bitiş Tarihi</label>
                <input id="end" onChange={this.changeInput} type="text" name="end" value={end} placeholder="gg/aa/yyyy" className="form-control"/>
                </div>  
                <button type="submit" className="btn btn-dark btn-block">Etkinlik Ekle</button>
                </form>


                </div>
			    </div>
                </Animation>
			</div>)}}</UserConsumer>}
	
	
}
export default AddUser;