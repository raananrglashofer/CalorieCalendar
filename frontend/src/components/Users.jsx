import React, {useEffect, UseState} from "react";
import apiService from "../services/apiService";

const Users = () => {
    const [message, setMessage] = useState('Loading...'); 

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try{
            const response = await apiService.getUsers();
            console.log(response.data);
            setMessage('correct');
        } catch(error){
            console.error('Error fetching users:', error);
            setMessage('incorrecct');
        }
    };
    return(
        <div>
            <h1>Users</h1>
            <p>{message}</p>
        </div>
    );
};

export default Users;
