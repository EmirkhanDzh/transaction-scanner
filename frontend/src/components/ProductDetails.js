import React from "react";
import newProduct from "../images/new-product.png"
import { useLocation, Link, useNavigate, } from "react-router-dom";
import { EDIT_PRODUCT, HOME } from "./Constants";

const ProductDetail = (props) => {
    const location = useLocation();
    const navigate = useNavigate();
    const { name, url, id } = location.state.product;

    const remove = async () => {
        await props.removeProductHandler(id)
        navigate(HOME)
    }
    return (
        <div className="main">
            <div className="ui card centered">
                <div className="image">
                    <img src={newProduct} alt="product" />
                </div>
                <div className="content">
                    <div className="header">{name}</div>
                    <div className="description"><a className = "linkToShop" href={url} target="_blank" rel="noreferrer">{url.substring(0, 33)}...</a></div>
                </div>
                <div className="commands bottomMargin">
                    <i className="trash alternate outline icon" onClick={remove} />

                    <Link to={`${EDIT_PRODUCT}/${location.state.product.id}`} state={{ product: location.state.product }}>
                        <i className="edit black alternate outline icon" />
                    </Link>
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