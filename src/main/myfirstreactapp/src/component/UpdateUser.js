import React,{Component} from "react";
import UserConsumer from "../context"
import axios from "axios";

	class UpdateUser extends Component{

	state= {
		
		name:"",
		start:"",
		end:"",
		error:"",
		error2:false

	}
	
	changeInput =(e)=>{

		this.setState({
			
			[e.target.name] :e.target.value 
		})
	}
	
//	componentDidMount=async()=>{
//	
//		const {name}=this.props;
//		console.log(name,this.id);
//		const response=await axios.get(`http://localhost:8080/update/${name}`)
//		
//		const{start,end}=response.data;
//		this.setState({
//			name:response.data.name,start,end
//		})
//		
//	}
	
	 MyFunction() {
  let tempDate = new Date();
  let date = tempDate.getFullYear() + '-' + (tempDate.getMonth()+1) + '-' + tempDate.getDate() +' '+ tempDate.getHours()+':'+ tempDate.getMinutes()+':'+ tempDate.getSeconds();
  const currDate = "Current Date= "+date;
  return (
    <p>{currDate}</p>
  );
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
	validateForm=()=> {
		const {name,start,end}=this.state
		let yil1=parseInt(start.slice(6,10));
		let yil2=parseInt(end.slice(6,10));
		let ay1=parseInt(start.slice(3,5));
		
		let ay2=parseInt(end.slice(3,5));
		let gun1=parseInt(start.slice(0,2));
		
		let gun2=parseInt(end.slice(0,2));
		if( yil1>yil2||(yil1===yil2 && ay1>ay2 )|| (yil1===yil2 && ay1===ay2 && gun1>gun2) ){
			
			return false;
		}
		return true;
		
		
	}


updateUser=async (dispatch,e) => {
	e.preventDefault();
	const {name,start,end}=this.state;
	const {id}=this.props.match.params;

	const updatedUser={
		name,end,start
	};
	let date=new Date();
	if(date.getFullYear()>parseInt(start.slice(6,10)) ||(date.getFullYear()===parseInt(start.slice(6,10))&& date.getMonth()>parseInt(start.slice(3,5)))  ||(date.getFullYear()===parseInt(start.slice(6,10)) &&date.getMonth()===parseInt(start.slice(3,5))&&date.getMonth()>parseInt(start.slice(0,2)) )){
		this.setState({error2:true})
		return;
	}
	if(this.validateForm()===false){
		this.setState({error:true})
		return;
	}
	
	else this.setState({error:false})
	const response=await axios.put(`http://localhost:8080/update/${name}`,updatedUser);
	dispatch({type:"UPDATE_USER",payload:response.data});
	//this.props.history.push("/");
}

	render (){
		
		const {error2,error,name,start,end}=this.state;
		return <UserConsumer>{value=>{const {dispatch}=value;return(
			<div className="col-md-8 mb-4">
			
			    
                 <div className="card" style={{backgroundColor:"#968A8A"}}>
                 <div className="card-header"> 
                    <h4>Etkinlik Güncelleme Formu</h4>
                </div>
                <div className="card-body">
{
error===true?<div className="alert alert-danger">
	Başlangıç tarihi geçmiş etkinliği güncelleyemezsiniz.
	</div> :(error===false?<div className="alert alert-success alert-dismissible fade show">İşlem başarıyla tamamlandı </div> :null)

}
{
	error2?<div className="alert alert-danger">
	Başlangıç tarihi geçmiş etkinliği güncelleyemezsiniz.
	</div>:null
	
}
                <form onSubmit={this.updateUser.bind(this,dispatch)}>
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
                <button type="submit" className="btn btn-dark btn-block">Etkinligi Güncelle</button>
                </form>


                </div>
			    </div>
               
			</div>)}}</UserConsumer>}
	
	
}
export default UpdateUser;
	
	
