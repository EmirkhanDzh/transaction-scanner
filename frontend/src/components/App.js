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
  const [searchTerm, setSearchTerm] = useState("")
  const [searchResult, setSearchResult] = useState([])

  useEffect(() => {
    const getAllProducts = async () => {
      const response = await api.get("/products")
      // const response1 = await api.get("/products/10")
      // console.log(response1)

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
    if (response.status !== 200 && response.status !== 201) {
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
      return false
    }
    setProducts(
      products.map((product) => {
        return product.id === response.data.id ? { ...response.data } : product;
      })
    );
    return true
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

  const searchHandler = (searchT) => {
    setSearchTerm(searchT);
    if (searchT !== "") {
      const resultProducts = products.filter((product) => {
        return Object
          .values(product)
          .join(" ")
          .toLowerCase()
          .includes(searchT.toLowerCase())
      });

      setSearchResult(resultProducts);
    } else {
      setSearchResult(products);
    }
  }

  return (
    <div className='ui container'>
      <Router>
        <Header />
        <Routes>
          <Route path={myConstants.HOME} exact
            element={
              <ProductList products={searchTerm.length < 1 ? products : searchResult}
                term={searchTerm}
                searchHandler={searchHandler} />}
          />
          <Route
            path={myConstants.ADD_PRODUCT}
            element={<AddProduct
              addProductHandler={addProductHandler} />}
          />
          <Route
            path="/product/:id"
            element={<ProductDetail {...props} removeProductHandler={removeProductHandler} />}
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
