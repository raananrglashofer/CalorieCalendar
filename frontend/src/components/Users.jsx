import React, { useEffect, useState } from "react";
import apiService from "../services/apiService";

const Users = () => {
    const [message, setMessage] = useState("let's see if it ever changes"); 

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try{
            const users = await apiService.getUsers();
            setMessage(JSON.stringify(users, null, 2));
            console.log("users recieved", response.data);
        } catch(error){
            console.error('Error fetching users:', error);
        }
    };
    return(
        <div>
            <h1>Users</h1>
            <pre>{message}</pre>
        </div>
    );
};

export default Users;
