import {BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SignIn from "./SignIn";
import SignUp from "./SignUp";

function Auth() {
    return(
        <Router>
            <Routes>
                <Route path="/sign-up" exact element={<SignUp/>}/>
                <Route path="/sign-in" exact element={<SignIn/>}/>
            </Routes>
        </Router>
    );
}


export default Auth;