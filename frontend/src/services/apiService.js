const API_URL = "http://localhost:8080/api";

const apiService = {
    getUsers: async () => {
        try {
            const response = await fetch(`${API_URL}/users`);
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error("Error fetching users:", error);
            throw error;
        }
    }
};

export default apiService;