import React from "react";
import { useNavigate, } from "react-router-dom";

const Header = (props) => {
  const navigate = useNavigate();
  return (
    <div className="ui fixed menu headerPadding">
      <div className="logout">
        <i className="upload alternate icon" onClick={() => navigate("/transaction/upload")} />
        Send
      </div>
      <div className="logout">
        <i className="tasks icon leftMargin" onClick={() => navigate("/transaction/result/view/all")} />
        Checked
      </div>
      <div className="logout">
        <i className="home icon leftMargin" onClick={() => navigate("/operator")} />
        Home
      </div>


      <div className="ui container center">
        <h2>TransactionScanner</h2>
      </div>
      <div className="logout" onClick={props.logout} >
        <i className="sign out alternate icon" />
        Logout
      </div>

    </div>
  );
};

export default Header;