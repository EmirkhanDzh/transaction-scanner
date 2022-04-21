import React from "react";
import product from "../images/product.png"

const ProductCard = (props) => {
    return (
        <div className="item ">
            <img className="ui avatar image" src={product} alt="product" />
            <div className="content">
                <div className="header">{props.product.name}</div>
                <div className="header">{props.product.price}</div>
            </div>
            <i className="trash alternate outline icon" onClick={()=>props.removeProductHandler(props.product.id)}></i>
        </div>
    );
};

export default ProductCard