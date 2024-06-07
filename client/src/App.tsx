import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
import HomePage from "./pages/HomePage";
import {useApp} from "./App.hooks.ts";
import UserContext from "./contexts/userContext.ts";

export function App() {
    const {
        user
    } = useApp()

    return (
        <Router>
            <UserContext.Provider value={user}>
                <Routes>
                    <Route index path="/ui" element={<HomePage/>}/>
                </Routes>
            </UserContext.Provider>
        </Router>
    )
}

export default App
