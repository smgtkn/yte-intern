import React,{Component} from "react";
import axios from "axios";
const UserContext=React.createContext();

//provider,consumer verir
const reducer = (state,action) => {
	switch(action.type) {
		
		case "DELETE_USER": 
		   return { 
			...state,
			users: state.users.filter(user => action.payload!== user.name)
		}
		case "ADD_USER":
		return {...state,
		users:[...state.users,action.payload] }
		case "UPDATE_USER":
		return {
			...state,
			users:state.users.map(user=>user.name===action.payload.name?action.payload:user)
		}
		default :
		return state;
	}
	
	
	
}
export class UserProvider extends Component {
	
	state = {
		users : [

			
		],
		dispatch: action=>{
			this.setState(state => reducer(state,action))}
		
	}
	
	componentDidMount=async () => {
		
	const response =await axios.get("http://localhost:8080/users")
		this.setState({
			
			users: response.data
			
		});
	}
	render() {
		
		return (<UserContext.Provider value={this.state}>{this.props.children}</UserContext.Provider>)}}

const UserConsumer=UserContext.Consumer;

export default UserConsumer;