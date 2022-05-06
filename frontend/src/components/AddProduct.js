import React, { useState } from "react";
import { BASE_URL, HOME } from "./Constants"
import { useNavigate } from "react-router-dom";
import api from "../api/products"


const AddProduct = (props) => {
    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [url, setUrl] = useState("");
    const [xpath, setXpath] = useState("");
    const [step, setStep] = useState(0);

    const onNameAndUrl = async () => {
        if (name === "" || url === "") {
            alert("All fields are mandotory!")
            return
        };

        console.log(name, url);

        let serverXpath = "";

        try {
            const response = await api.get(`/products/xpath?url=${url}`)
            if (response.status === 200) {
                serverXpath = response.data;
            }
        } catch (error) {
            console.error(error)
        }

        setXpath(serverXpath)

        setStep(step + 1);
    }

    const onXpath = () => {
        if (name === "" || url === "" || xpath === "") {
            alert("All fields are mandotory!");
            return;
        };

        console.log(xpath);

        setStep(step + 1);

        //navigate(HOME);
    }

    const onConfirm = (e) => {
        e.preventDefault();
        if (name === "" || url === "" || xpath === "") {
            alert("All fields are mandotory!")
            return
        };
        // Retrieve prize and currency by xpath it will be done in the next iter
        // const price = 0.00

        props.addProductHandler(
            {
                name: name,
                url: url,
                xpath: xpath
            }
        );

        setName("")
        setUrl("")
        setXpath("")
        navigate(HOME)
    }

    const onBack = () => {
        setStep(step - 1);
    }

    return (
        <div className="main">
            <h2>Add a product</h2>
            <form className="ui form" onSubmit={(e) => e.preventDefault()}>
                {step === 0 &&
                    <section>

                        <div className="field">
                            <label>Name</label>
                            <input type="text" name="name" placeholder="input there the product's name"
                                value={name}
                                onChange={(e) => setName(e.target.value.match(/[\p{L}\p{N}\s]/gu).join(''))} />
                        </div>
                        <div className="field">
                            <label>URL</label>
                            <input type="text" name="link" placeholder="paste there a link to the product"
                                value={url}
                                onChange={(e) => setUrl(e.target.value)} />
                        </div>

                        <button className="ui button blue" onClick={onNameAndUrl}>Next</button>
                    </section>
                }

                {step === 1 &&
                    <section>
                        <div className="field">
                            <label>Xpath</label>
                            <input type="text" name="link" placeholder="paste there a xpath to the product's price"
                                value={xpath}
                                onChange={(e) => setXpath(e.target.value)} />
                        </div>

                        <div className="backAndNext bottomMargin">
                            <i className="angle double left icon grey rightMargin" onClick={onBack}></i>
                            <button className="ui button blue" onClick={onXpath}>Next</button>
                        </div>
                    </section>
                }

                {step === 2 &&
                    <section>
                        <p>Please check if the selection valid and confirm adding of the product</p>
                        <div className="backAndNext bottomMargin">
                            <i className="angle double left icon grey rightMargin" onClick={onBack}></i>
                            <button className="ui button blue" onClick={onConfirm}>Confirm</button>
                        </div>

                        <p>
                            <br />1) Click <a href={`${BASE_URL}/products/html?url=${url}&xpath=${encodeURIComponent(xpath)}`} target="_blank" rel="noreferrer">here</a>
                            <br />2) Check if the price of the product is highlighted
                            <br />3) Press "Confirm" button
                        </p>
                    </section>
                }
            </form>
        </div>
    );
}

export default AddProduct;