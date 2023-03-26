import React from "react";
import { Link } from "react-router-dom";
import ProductCard from "./ProductCard";
import * as myConstants from './Constants';
import { TransactionsTable } from "./TransactionsTable";


const TransactionsMainPage = (props) => {
    return (
        <div className="main">
            <TransactionsTable/>
        </div>
    );
};

export default TransactionsMainPage