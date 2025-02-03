import axios from "axios";

const API_URL = "http://localhost:8080/api";

const apiService = {
    getUsers: async () => {
        try {
            const response = await axios.get(`${API_URL}/users`);
            return response.data;
        } catch (error) {
            console.error("Error fetching users:", error);
            throw error;
        }
    }
};

export default apiService;