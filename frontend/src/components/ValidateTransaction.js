import React, { useState } from "react";
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

    const transaction = {
        "id": 1,
        "clientFrom": {
            "id": 1,
            "name": "Ivan Petrov",
            "patronymic": "Andreevich",
            "birthDay": [
                2001,
                1,
                1
            ],
            "phoneNumber": "+79993334455",
            "citizenshipCountry": {
                "id": 1,
                "name": "Russia",
                "code": "RUS"
            }
        },
        "clientTo": {
            "id": 2,
            "name": "Ivan Yan",
            "patronymic": "Andreevich",
            "birthDay": [
                2002,
                1,
                2
            ],
            "phoneNumber": "+79993334444",
            "citizenshipCountry": {
                "id": 1,
                "name": "Russia",
                "code": "RUS"
            }
        },
        "amount": 100,
        "currency": "RUB",
        "bankFrom": {
            "id": 1,
            "name": "Tinkoff",
            "code": "TNF",
            "country": {
                "id": 1,
                "name": "Russia",
                "code": "RUS"
            }
        },
        "bankTo": {
            "id": 1,
            "name": "Tinkoff",
            "code": "TNF",
            "country": {
                "id": 1,
                "name": "Russia",
                "code": "RUS"
            }
        },
        "paySystemFrom": {
            "name": "VISA",
            "code": "VSA",
            "country": {
                "id": 1,
                "name": "Russia",
                "code": "RUS"
            }
        },
        "paySystemTo": {
            "name": "VISA",
            "code": "VSA",
            "country": {
                "id": 1,
                "name": "Russia",
                "code": "RUS"
            }
        },
        "transferDate": [
            2023,
            3,
            29,
            7,
            0
        ],
        "cityFrom": "Moscow",
        "cityTo": "Moscow",
        "countryFrom": {
            "id": 1,
            "name": "Russia",
            "code": "RUS"
        },
        "countryTo": {
            "id": 1,
            "name": "Russia",
            "code": "RUS"
        },
        "rulesEngineResult": {
            "id": 1,
            "transactionId": 1,
            "isClear": false,
            "clientSanctionId": 1,
            "bankSanctionId": null,
            "paysystemSanctionId": null,
            "countrySanctionId": null,
            "clientSanction": {
                "id": 1,
                "code": "SC1",
                "description": "Client is in DPRK list",
                "entity_id": 1
            }
        },
        "operatorResult": null
    }

    return (
        <div className="main">
            <div>
                <h3>Transaction Validation</h3>
                <Link to={`/operator`}>
                    <button className="ui right floated button black ">Home</button>
                </Link>
            </div>

            <h4>Rules result:</h4>
            <Table className="TransactionDetailTable">
                <TableBody>
                    <TableRow>
                        <TableCell className="FieldHeader">Sender Scoring</TableCell>
                        <TableCell className="FieldValue">{transaction.clientFrom.name}</TableCell>
                        <TableCell className="FieldResultIcon">{true ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription"></TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Recipient Scoring</TableCell>
                        <TableCell className="FieldValue">{transaction.clientTo.name}</TableCell>
                        <TableCell className="FieldResultIcon">{false ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription">Получатель находится в списке санкций</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Sender Bank Scoring</TableCell>
                        <TableCell className="FieldValue">{transaction.bankFrom.name}</TableCell>
                        <TableCell className="FieldResultIcon">{true ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription"></TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Recipient Bank Scoring</TableCell>
                        <TableCell className="FieldValue">{transaction.bankTo.name}</TableCell>
                        <TableCell className="FieldResultIcon">{true ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription"></TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Sender Paysystem Scoring</TableCell>
                        <TableCell className="FieldValue">{transaction.paySystemFrom.name}</TableCell>
                        <TableCell className="FieldResultIcon">{true ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription"></TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Recipient Paysystem Scoring</TableCell>
                        <TableCell className="FieldValue">{transaction.paySystemTo.name}</TableCell>
                        <TableCell className="FieldResultIcon">{true ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription"></TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Sender Country Scoring</TableCell>
                        <TableCell className="FieldValue">{transaction.countryFrom.name}</TableCell>
                        <TableCell className="FieldResultIcon">{true ? <CheckIcon /> : <CloseIcon sx={{ color: red[500] }} />}</TableCell>
                        <TableCell className="FieldResultDescription"></TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell className="FieldHeader">Recipient Country Scoring</TableCell>
                        <TableCell className="FieldValue">{transaction.countryTo.name}</TableCell>
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

            <div style={{ justifyContent: "space-between", display: "flex", paddingBottom: "0.5rem" }}>
                <Link to={`/`}>
                    <button className="ui   button positive">Save As Clear</button>
                </Link>
                <Link to={`/`}>
                    <button className="ui button negative">Save As Sactional</button>
                </Link>
            </div>

        </div>
    );
}

export default ValidateTransaction;