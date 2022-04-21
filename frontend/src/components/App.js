import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';
import Header from './Header';
import AddProduct from './AddProduct';
import ProductList from './ProductList';
import * as myConstants from './Constants'


function App() {
  const PRODUCTS_LS_KEY = "PRODUCTS_LS_KEY";
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const retrievedProducts = JSON.parse(localStorage.getItem(PRODUCTS_LS_KEY))
    console.log(retrievedProducts)
    if (retrievedProducts) {
      setProducts(retrievedProducts)
    }
  }, []);

  useEffect(() => {
    localStorage.setItem(PRODUCTS_LS_KEY, JSON.stringify(products))
  }, [products]);

  const addProductHandler = (product) => {
    console.log(product)
    setProducts([...products, product])
  };

  const removeProductHandler = (id) => {
    console.log(id)
    const newProducts = products.filter((product) => { return product.id !== id })
    setProducts(newProducts)
  };

  return (
    <div className='ui container'>
      <Router>
        <Header />
        <Routes>
          <Route path={myConstants.HOME} exact element={<ProductList products={products} removeProductHandler={removeProductHandler} />} />
          <Route path={myConstants.ADD_PRODUCT} element={<AddProduct addProductHandler={addProductHandler} />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
