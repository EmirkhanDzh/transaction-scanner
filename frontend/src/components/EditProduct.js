import React, { useState } from "react";
import { PRODUCT } from "./Constants"
import { useLocation, useNavigate } from "react-router-dom";

const EditProduct = (props) => {
    const navigate = useNavigate();
    const location = useLocation();

    const [name, setName] = useState(location.state.product.name);
    const [url, setUrl] = useState(location.state.product.url);
    const [xpath, setXpath] = useState(location.state.product.xpath)
    const [currency, setCurrency] = useState(location.state.product.currency)


    const updateProduct = (e) => {
        e.preventDefault();
        if (name === "" || url === "" || xpath === "" || currency === "") {
            alert("All fields are mandotory!")
            return
        };

        // Retrieve prize and currency by xpath it will be done in the next iter
        const price = 90.99

        const isUpdated = props.updateProductHandler(
            {
                id: location.state.product.id,
                name: name,
                price: price,
                url: url,
                xpath: xpath,
                currency: currency
            }
        );

        if (isUpdated) {
            location.state.product.name = name
            location.state.product.price = price
            location.state.product.url = url
            location.state.product.xpath = xpath
            location.state.product.currency = currency
        } else {
            alert("Couldn't update")
        }

        setName("")
        setUrl("")
        setXpath("")
        setCurrency("")

        navigate(`${PRODUCT}/${location.state.product.id}`, { state:{ product: location.state.product } })
    }


    return (
        <div className="main">
            <h2>Edit the product</h2>
            <form className="ui form" onSubmit={updateProduct}>
                <div className="field">
                    <label>Name</label>
                    <input type="text" name="name" placeholder="input there the product's name"
                        value={name}
                        onChange={(e) => setName(e.target.value)} />
                </div>
                <div className="field">
                    <label>URL</label>
                    <input type="text" name="link" placeholder="paste there a link to the product"
                        value={url}
                        onChange={(e) => setUrl(e.target.value)} />
                </div>
                <div className="field">
                    <label>Xpath</label>
                    <input type="text" name="link" placeholder="paste there a xpath to the product's price"
                        value={xpath}
                        onChange={(e) => setXpath(e.target.value)} />
                </div>
                <div className="field">
                    <label>Currency</label>
                    <input type="text" name="link" placeholder="paste there the currency"
                        value={currency}
                        onChange={(e) => setCurrency(e.target.value)} />
                </div>
                <button className="ui button blue">Save</button>
            </form>
        </div>
    );
}

export default EditProduct;