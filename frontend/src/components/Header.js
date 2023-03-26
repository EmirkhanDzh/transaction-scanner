import React from "react";
import { useNavigate, } from "react-router-dom";

const Header = (props) => {
  const navigate = useNavigate();
  return (
    <div className="ui fixed menu headerPadding">
      <div className="logout">
        <i className="upload alternate icon" onClick={() => navigate("/transaction/upload")} />
      </div>
      <div className="ui container center">
        <h2>TransactionScanner</h2>
      </div>
      <div className="logout">
        <i className="sign out alternate icon" onClick={props.logout} />
      </div>

    </div>
  );
};

export default Header;