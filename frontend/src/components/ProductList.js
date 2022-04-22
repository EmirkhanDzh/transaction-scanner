import React from "react";
import { Link } from "react-router-dom";
import ProductCard from "./ProductCard";
import * as myConstants from './Constants'


const ProductList = (props) => {
    const renderProducts = props.products.map((product) => {
        return (
            <ProductCard product={product} key={product.id} removeProductHandler={props.removeProductHandler} />
        );
    });

    const getSearchTerm = (e) => {
        props.searchHandler(e.target.value)
    }

    return (
        <div className="main">
            <h3 className="bottomMargin">
                Your products
                <Link to={myConstants.ADD_PRODUCT}>
                    <button className="ui button blue right">Add Product</button>
                </Link>

            </h3>
            <div className="ui search">
                <div className="ui icon input">
                    <input
                        type="text"
                        placeholder="Search Product"
                        className="prompt"
                        value={props.term}
                        onChange={getSearchTerm} />
                    <i className="search icon" />

                </div>
            </div>
            <div className="ui celled list">
                {renderProducts.length > 0 ? renderProducts : "No products available"}
            </div>
        </div>

    );
};

export default ProductList