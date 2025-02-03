import React, { useEffect, useState } from "react";
import apiService from "../services/apiService";

const Users = () => {
    const [message, setMessage] = useState(); 

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try{
            const response = await apiService.getUsers();
            console.log(response.data);
        } catch(error){
            console.error('Error fetching users:', error);
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
