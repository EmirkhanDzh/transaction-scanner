import React from "react";

class AddProduct extends React.Component {

    state = {
        name: "",
        url: ""
    }

    addProduct = (e) => {
        e.preventDefault();
        if (this.state.name === "" || this.state.url === "") {
            alert("All fields are mandotory!")
            return
        };

        console.log(this.state);
        this.props.addProductHandler(
            {
                id: "2",
                name: this.state.name,
                price: 90.99,
                url: "adidas.com"
            }
        );

        this.setState({ name: "", url: "" });
    }

    render() {
        return (
            <div className="main">
                <h2>Add a product</h2>
                <form className="ui form" onSubmit={this.addProduct}>
                    <div className="field">
                        <label>Name</label>
                        <input type="text" name="name" placeholder="input there the product's name"
                            value={this.state.name}
                            onChange={(e) => this.setState({ name: e.target.value })} />
                    </div>
                    <div className="field">
                        <label>URL</label>
                        <input type="text" name="link" placeholder="paste there a link to the product"
                            value={this.state.url}
                            onChange={(e) => this.setState({ url: e.target.value })} />
                    </div>
                    <button className="ui button blue">Add</button>
                </form>
            </div>
        );
    }
}

export default AddProduct;