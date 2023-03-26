import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SignIn from "./SignIn";
import SignUp from "./SignUp";

function Auth(props) {
    return (
        <SignIn login={props.login} />
        // <Router>
        //     <Routes>
        //         <Route path="/" exact element={<SignIn login={props.login} />} />
        //         <Route path="/sign-up/:id" exact element={<SignUp signUp={props.signUp} />} />
        //     </Routes>
        // </Router>
    );
}


export default Auth;