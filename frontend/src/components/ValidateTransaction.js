import React, { useState } from "react";
import { PRODUCT } from "./Constants"
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { TableCell, TableRow, TableBody, Table } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import CheckIcon from '@mui/icons-material/Check';
import { red } from '@mui/material/colors';
import TextField from '@mui/material/TextField';
import { Link } from "react-router-dom";

const ValidateTransaction = (props) => {
    const navigate = useNavigate();
    const { id } = useParams()


    return (
        <div className="main">
            <div>
            <h3>Transaction Validation</h3>
            <Link to={`/`}>
                <button className="ui right floated button black ">Back</button>
            </Link>
            </div>
            
            <h4>Rules result:</h4>
            <Table className="TransactionDetailTable">
                <TableBody>
                    <TableRow>
                        <TableCell className="FieldHeader">Header 1</TableCell>
                        <TableCell className="FieldValue">Cell 1</TableCell>
                        <TableCell className="FieldResultIcon">{true ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription"></TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Header 2</TableCell>
                        <TableCell className="FieldValue">Cell 2</TableCell>
                        <TableCell className="FieldResultIcon">{false ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas blandit diam non arcu fringilla tincidunt sed a ipsum. In dapibus justo eu est pulvinar tempus. Integer nec vehicula tellus. Proin suscipit libero vitae velit scelerisque pharetra. Curabitur a velit pulvinar, rutrum enim ac, tristique ex</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Header 1</TableCell>
                        <TableCell className="FieldValue">Cell 3</TableCell>
                        <TableCell className="FieldResultIcon">{true ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription"></TableCell>
                    </TableRow>
                </TableBody>
            </Table>
            <h4>Validation result:</h4>

            <TextField
                className="ValidationResultTextField bottomMargin"
                id="outlined-multiline-static"
                label="Input there a comment"
                multiline
                rows={4}
                defaultValue=""
            />

            <div>
            <Link to={`/`}>
                <button className="ui left floated button positive">Save As Clear</button>
            </Link>
            <Link to={`/`}>
                <button className="ui right floated button negative">Save As Sactional</button>
            </Link>
            </div>
            

        </div>
    );
}

export default ValidateTransaction;