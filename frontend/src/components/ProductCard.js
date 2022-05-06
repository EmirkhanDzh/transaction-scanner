import React from "react";
import { Link } from "react-router-dom";
import product from "../images/product.png"
import { PRODUCT } from "./Constants";

const ProductCard = (props) => {
    return (
        <div className="item">
            <img className="ui avatar image rightMargin" src={product} alt="product" />
            <div className="content">
                <Link
                    to={`${PRODUCT}/${props.product.id}`}
                    state={{ product: props.product }}
                >
                    <div className="header noNewLine">{props.product.name}</div>
                    <div className="description">{props.product.priceStr}</div>
                </Link>
            </div>


        </div>
    );
};

export default ProductCard