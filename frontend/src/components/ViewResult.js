import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { TableCell, TableRow, TableBody, Table } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import CheckIcon from '@mui/icons-material/Check';
import { red } from '@mui/material/colors';
import TextField from '@mui/material/TextField';
import { Link } from "react-router-dom";

const ViewResult = (props) => {
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
      "operatorResult": {
         "id": 1,
         "transactionId": 1,
         "isClear": false,
         "operatorId": 1,
         "isClearByOperator": false,
         "comment": "Да, действительно, клиент находится в санкционном списке"
      }
   }

   const isClear = transaction.operatorResult.isClearByOperator

   return (
      <div className="main">
         <div>
            <h3>Transaction Validation</h3>
            <Link to={`/transaction/result/view/all`}>
               <button className="ui right floated button black ">To Checked List</button>
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
         <h4 className={`ui ${isClear ? 'green' : 'red'} header`}>Validation result: {isClear ? 'clear' : 'sanctioned'}</h4>

         <TextField
            className="ValidationResultTextField bottomMargin"
            id="outlined-multiline-static"
            label="Comment:"
            multiline
            rows={4}
            defaultValue={transaction.operatorResult.comment}
            inputProps={
               { readOnly: true, }
            }
         />
      </div>
   );
}

export default ViewResult;