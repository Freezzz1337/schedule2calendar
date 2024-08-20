import {Route, Routes} from "react-router-dom";
import MainPage from "../main-page";

import "bootstrap/dist/css/bootstrap.min.css";
import ConvertPage from "../convert-page";

const App = () => {
    return (
        <Routes>
            <Route path="/" element={<MainPage/>}/>
            <Route path="/convert-page" element={<ConvertPage/>}/>
        </Routes>
    );
};

export default App;