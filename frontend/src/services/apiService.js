const API_URL = "http://localhost:8080/api/users";

const apiService = {
    getUsers: async () => {
        try {
            const response = await fetch(`${API_URL}`);
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error("Error fetching users:", error);
            throw error;
        }
    },
    getUserByName: async (username) => {
        try{
            const response = await fetch(`${API_URL}/${username}`);
            if(!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            const data = await response.json();
            return data;
        } catch(error) {
            console.error('Error fetching user:', error);
            throw error;
        }
    },
    getDays: async (username) => {
        const user = getUserByName(username);
        return user.days;
    }
};
export default apiService;