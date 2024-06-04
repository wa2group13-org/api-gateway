import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
import HomePage from "./pages/HomePage";

function App() {

    return (
        <Router>
            <Routes>
                <Route index path="/" element={<HomePage/>}/>
            </Routes>
        </Router>
    )
}

export default App
