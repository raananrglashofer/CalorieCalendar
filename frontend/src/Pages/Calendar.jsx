import React, { useEffect, useState } from "react";
import apiService from "../services/apiService";
import ReturnToHome from "../components/returnToHome";

const Calendar = () => {
    const [user, setUser] = useState(null); 
    useEffect(() => {
        fetchUser("Raanan"); // hardcoded for now
    }, []);

    const fetchUser = async (username) => {
        try{
            const userData = await apiService.getUserByName(username);
            setUser(userData);
            console.log("user recieved", user);
        } catch(error){
            console.error('Error fetching user:', error);
        }
    };
    return(
        <div>
            <h1>Calendar</h1>
            <p>This is the calendar page</p>
            <ReturnToHome />
            <pre>{JSON.stringify(user, null, 2)}</pre>
        </div>
    );
};

export default Calendar;