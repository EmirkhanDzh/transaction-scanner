import React, { useState } from "react";
import { PRODUCT } from "./Constants"
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { TableCell, TableRow, TableBody, Table } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import CheckIcon from '@mui/icons-material/Check';
import { red } from '@mui/material/colors';
import TextField from '@mui/material/TextField';
import { Link } from "react-router-dom";

const UploadTransactions = (props) => {
    const navigate = useNavigate();
    const { id } = useParams()


    return (
        <div className="main">
            <div>
            <h3>Upload Transactions To System</h3>
            <Link to={`/operator`}>
                <button className="ui right floated button black bottomMargin">Home</button>
            </Link>
            </div>

            <TextField
                className="ValidationResultTextField bottomMargin"
                id="outlined-multiline-static"
                label="Input there a json array of transactions"
                multiline
                rows={16}
                defaultValue=""
            />

            <div>
            <Link to={`/`}>
                <button className="ui right floated button blue">Save Transactions</button>
            </Link>
            </div>
            

        </div>
    );
}

export default UploadTransactions;