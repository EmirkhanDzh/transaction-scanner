import {BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SignIn from "./SignIn";
import SignUp from "./SignUp";

function Auth(props) {
    return(
        <Router>
            <Routes>
                <Route path="/sign-up" exact element={<SignUp login={props.login}/>}/>
                <Route path="/sign-in" exact element={<SignIn login={props.login}/>}/>
            </Routes>
        </Router>
    );
}


export default Auth;