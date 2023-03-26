import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import api from "../api/products"
import './App.css';
import Header from './Header';
import AddProduct from './AddProduct';
import TransactionsMainPage from './TransactionsMainPage';
import * as myConstants from './Constants'
import ProductDetail from "./ProductDetails";
import ValidateTransaction from "./ValidateTransaction";
import Auth from "./auth/Auth";
import UploadTransactions from "./UploadTransactions";


function App(props) {

  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [searchResult, setSearchResult] = useState([]);
  const [isAuth, setIsAuth] = useState(false);
  //console.log(authHeader())

  localStorage.getItem("username") && localStorage.getItem("token") && api
    .get(`/users/get?username=${localStorage.getItem("username")}`)
    .then((response) => {
      if (response.status !== 200) {

        throw new Error("user doesn't exist");
      }

      setIsAuth(true);
    })
    .catch(() => {
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      localStorage.removeItem("chatId");
      setIsAuth(false);
    });

  useEffect(() => {
    const getAllProducts = async () => {
      if (!isAuth) {
        return;
      }

      const response = await api.get(`/products?username=${localStorage.getItem("username")}`)

      if (response.status !== 200) {
        alert("Couldn't retrieve products");
        return;
      }

      if (response.data) {
        setProducts(response.data);
      }

    };
    getAllProducts();
  }, [isAuth]);

  // useEffect(() => {
  //   localStorage.setItem(PRODUCTS_LS_KEY, JSON.stringify(products))
  // }, [products]);

  const login = async (user) => {

    api.post(`/auth/sign-in`, user).then((response) => {
      if (!response || response.status !== 200) {
        alert("Please check your username and password");
        return;
      }
      console.log(response.data);
      console.log(user);
      localStorage.setItem("token", response.data);
      localStorage.setItem("username", user.username);
      //localStorage.setItem("chatId", user.chatId);
      setIsAuth(true);
      window.location.reload();
    }).catch((err) => {

      alert("Please check your username and password")
    })

    // let response

    // response = await api.post(`/auth/sign-in`, user);


    // if (!response || response.status !== 200) {
    //   alert("Please check your username and password")
    //   return;
    // }
    // console.log(response.data);
    // console.log(user)
    // localStorage.setItem("token", response.data);
    // localStorage.setItem("username", user.username);
    // //localStorage.setItem("chatId", user.chatId);
    // setIsAuth(true);
    // window.location.reload();
  }

  const logout = () => {

    localStorage.removeItem("token");
    localStorage.removeItem("username");
    setIsAuth(false);
  }

  const signUp = async (user) => {

    console.log(`going to add the user ${JSON.stringify(user)}`);

    return await api.post("/auth/sign-up", user)
      .then((response) => {
        if (response.status !== 200 && response.status !== 201) {
          alert("Couldn't add a new user")
          return false;
        };
        return true;
      }).catch((error) => {
        alert("Couldn't add a new user")
        console.log(error);
      });
    // const response = await api.post("/users", user);

    // if (response.status !== 200 && response.status !== 201) {
    //   alert("Couldn't add a new user")
    //   return false;
    // };

    // return true;
    // login(user, "ok")
  }

  // if (!isAuth) {
  //   return (<Auth login={login} signUp={signUp} />)
  // }

  const addProductHandler = async (product) => {
    console.log(`going to add the product ${JSON.stringify(product)}`);
    const request = {
      id: (products.length + 10),
      ...product
    };
    const username = localStorage.getItem("username")
    const response = await api.post(`/products?username=${username}`, request)
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
      products.map((p) => {
        return p.id === product.id ? { ...product } : p;
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
        <Header logout={logout} />
        <Routes>
          <Route path={myConstants.HOME} exact
            element={
              <TransactionsMainPage products={searchTerm.length < 1 ? products : searchResult}
                term={searchTerm}
                searchHandler={searchHandler} />}
          />
          {/* <Route
            path={myConstants.ADD_PRODUCT}
            element={<AddProduct
              addProductHandler={addProductHandler} />}
          />
          <Route
            path="/product/:id"
            element={<ProductDetail {...props} removeProductHandler={removeProductHandler} />}
          /> */}
          <Route
            path={"/transaction/validate/:id"}
            element={<ValidateTransaction {...props} updateProductHandler={updateProductHandler} />}
          />

          <Route

            path={"/operator/auth"}
            element={<Auth login={login} signUp={signUp} />}
          />
          <Route

            path={"/transaction/upload"}
            element={<UploadTransactions/>}
          />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
