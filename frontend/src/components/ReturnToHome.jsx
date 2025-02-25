import React from "react";
import { useNavigate } from "react-router-dom";

const ReturnToHome = () => {
    const navigate = useNavigate();
    return (
        <button onClick={() => navigate("/")}>Return to Home</button>
    );
}
export default ReturnToHome;