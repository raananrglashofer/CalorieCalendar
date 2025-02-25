import react from "react";
import { Link } from "react-router-dom";

const Home = () => {
    return (
        <div>
            <h1> Welcome to CalorieCalendar!</h1>
            <p>This is the home page</p>

            <nav>
                <ul>
                    <li>
                    <Link to ="/calendar">Go to Calendar</Link>
                    </li>
                </ul>
            </nav>
        </div>
    );
}
export default Home;