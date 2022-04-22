import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import api from "../api/products"
import './App.css';
import Header from './Header';
import AddProduct from './AddProduct';
import ProductList from './ProductList';
import * as myConstants from './Constants'
import ProductDetail from "./ProductDetails";
import EditProduct from "./EditProduct";


function App(props) {
  const PRODUCTS_LS_KEY = "PRODUCTS_LS_KEY";
  const [products, setProducts] = useState([]);



  useEffect(() => {
    const getAllProducts = async () => {
      const response = await api.get("/products")

      if (response.status !== 200) {
        alert("Couldn't retrieve products")
        return
      }

      if (response.data) {
        setProducts(response.data);
      }

    };
    getAllProducts();
  }, []);

  useEffect(() => {
    localStorage.setItem(PRODUCTS_LS_KEY, JSON.stringify(products))
  }, [products]);

  const addProductHandler = async (product) => {
    console.log(`going to add the product ${JSON.stringify(product)}`)
    const request = {
      id: (products.length + 10),
      ...product
    }
    const response = await api.post("/products", request)
    if (response.status !== 201) {
      alert("Couldn't add the product")
      return
    }
    setProducts([...products, response.data])
  };
  
  const updateProductHandler = async (product) => {
    console.log(`going to edit the product ${JSON.stringify(product)}`)

    const response = await api.put(`/products/${product.id}`, product)
    if (response.status !== 200) {
      alert("Couldn't update the product")
      return
    }
    setProducts(
      products.map((product) => {
        return product.id === response.data.id ? { ...response.data } : product;
      })
    );
  };

  const removeProductHandler = async (id) => {
    console.log(`going to remove the product with id = ${id}`)
    const response = await api.delete(`products/${id}`)
    if (response.status !== 200) {
      alert("Couldn't delete the product")
      return
    }
    const newProducts = products.filter((product) => { return product.id !== id })
    setProducts(newProducts)
  };

  return (
    <div className='ui container'>
      <Router>
        <Header />
        <Routes>
          <Route path={myConstants.HOME} exact
            element={<ProductList products={products}
              removeProductHandler={removeProductHandler} />}
          />
          <Route
            path={myConstants.ADD_PRODUCT}
            element={<AddProduct
              addProductHandler={addProductHandler} />}
          />
          <Route
            path="/product/:id"
            element={<ProductDetail {...props} />}
          />
          <Route
            path={`${myConstants.EDIT_PRODUCT}/:id`}
            element={<EditProduct updateProductHandler={updateProductHandler} />}
          />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
