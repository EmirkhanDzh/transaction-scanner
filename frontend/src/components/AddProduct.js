import React, { useState } from "react";
import {HOME} from "./Constants"
import { useNavigate } from "react-router-dom";

const AddProduct = (props) => {
    const navigate = useNavigate();

    const [name, setName] = useState("")
    const [url, setUrl] = useState("")
    const [xpath, setXpath] = useState("")
    const [currency, setCurrency] = useState("")

    const addProduct = (e) => {
        e.preventDefault();
        if (name === "" || url === "" || xpath === "" || currency === "") {
            alert("All fields are mandotory!")
            return
        };
        // Retrieve prize and currency by xpath it will be done in the next iter
        const price =  90.99

        props.addProductHandler(
            {
                name: name,
                price: price,
                url: url,
                xpath: xpath,
                currency: currency
            }
        );
        
        setName("")
        setUrl("")
        setXpath("")
        setCurrency("")
        navigate(HOME)
    }


    return (
        <div className="main">
            <h2>Add a product</h2>
            <form className="ui form" onSubmit={addProduct}>
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
                <button className="ui button blue">Add</button>
            </form>
        </div>
    );
}

export default AddProduct;