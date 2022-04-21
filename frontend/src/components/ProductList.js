import React from "react";
import { Link } from "react-router-dom";
import ProductCard from "./ProductCard";
import { ROUTES } from "./AddProduct"
import * as myConstants from './Constants'


const ProductList = (props) => {
    console.log(props);
    const renderProducts = props.products.map((product) => {
        return (
            <ProductCard product={product} key={product.id} removeProductHandler={props.removeProductHandler} />
        );
    });

    return (
        <div className="main">
            <h3 className="bottomMargin">
                Your products
                <Link to={myConstants.ADD_PRODUCT}>
                    <button className="ui button blue right">Add Product</button>
                </Link>

            </h3>
            <div className="ui celled list">
                {renderProducts}
            </div>
        </div>

    );
};

export default ProductList