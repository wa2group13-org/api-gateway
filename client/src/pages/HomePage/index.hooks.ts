export default function useHomePage() {
    const performLogin = () => {
        window.location.href = import.meta.env.VITE_API_URL + "/login";
    }
    return {performLogin}
}