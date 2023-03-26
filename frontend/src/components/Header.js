import React from "react";

const Header = (props) => {
  return (
    <div className="ui fixed menu">
      <div className="ui container center">
        <h2>TransactionScanner</h2>
      </div>
      <div className="logout">
      <i className="big sign out alternate icon" onClick={props.logout}/>
      </div>
      
    </div>
  );
};

export default Header;