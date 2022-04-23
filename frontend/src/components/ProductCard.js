import React from "react";
import { Link } from "react-router-dom";
import product from "../images/product.png"
import { EDIT_PRODUCT, PRODUCT } from "./Constants";

const ProductCard = (props) => {
    return (
        <div className="item">
            <div className="hor likeColumn">
                <div className="hor">
                    <img className="ui avatar image rightMargin" src={product} alt="product" />
                    <div className="content">
                        <Link
                            to={`${PRODUCT}/${props.product.id}`}
                            state={{ product: props.product }}
                        >
                            <div className="header">{props.product.name}</div>
                            <div className="description">{props.product.url}</div>
                        </Link>

                    </div>
                </div>



                <div>
                    <span className="rightMargin">{props.product.price + " " + props.product.currency}</span>
                    <i className="trash alternate outline icon" onClick={() => props.removeProductHandler(props.product.id)} />

                    <Link to={`${EDIT_PRODUCT}/${props.product.id}`} state={{ product: props.product }}>
                        <i className="edit black alternate outline icon" />
                    </Link>
                </div>
            </div>


        </div>
    );
};

export default ProductCard