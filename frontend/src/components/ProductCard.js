import React from "react";
import { Link } from "react-router-dom";
import product from "../images/product.png"
import { PRODUCT } from "./Constants";

const ProductCard = (props) => {
    return (
        <div className="item ">
            <img className="ui avatar image" src={product} alt="product" />
            <div className="content">
                <Link
                    to={`${PRODUCT}/${props.product.id}`}
                    state={{ product: props.product }}
                >
                    <div className="header">{props.product.name}</div>
                    <div className="description">{props.product.price}</div>
                </Link>

            </div>
            <i className="trash alternate outline icon" onClick={() => props.removeProductHandler(props.product.id)}></i>
        </div>
    );
};

export default ProductCard