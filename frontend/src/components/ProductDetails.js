import React from "react";
import newProduct from "../images/new-product.png"
import { useLocation, Link } from "react-router-dom";
import { HOME } from "./Constants";

const ProductDetail = () => {
    const location = useLocation();
    const { name, url } = location.state.product;
    return (
        <div className="main">
            <div className="ui card centered">
                <div className="image">
                    <img src={newProduct} alt="product" />
                </div>
                <div className="content">
                    <div className="header">{name}</div>
                    <div className="description">{url}</div>
                </div>

            </div>
            <div className="center-div">
                    <Link to={HOME}>
                        <button className="ui button blue center">Back to Products</button>
                    </Link>
                </div>
        </div>
    );
};

export default ProductDetail