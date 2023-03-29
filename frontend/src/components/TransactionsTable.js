import React from "react";
import MaterialTable from "material-table";
import { ThemeProvider, createTheme } from '@mui/material';
import { Link } from "react-router-dom";

export const TransactionsTable = () => {
    const defaultMaterialTheme = createTheme();
    const transactions = [ { "id":1, "clientFrom":{ "id":1, "name":"Ivan Petrov", "patronymic":"Andreevich", "birthDay":[ 2001, 1, 1 ], "phoneNumber":"+79993334455", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "clientTo":{ "id":2, "name":"Ivan Yan", "patronymic":"Andreevich", "birthDay":[ 2002, 1, 2 ], "phoneNumber":"+79993334444", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "amount":100, "currency":"RUB", "bankFrom":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "bankTo":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemFrom":{ "name":"VISA", "code":"VSA", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemTo":{ "name":"VISA", "code":"VSA", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "transferDate":[ 2023, 3, 29, 7, 0 ], "cityFrom":"Moscow", "cityTo":"Moscow", "countryFrom":{ "id":1, "name":"Russia", "code":"RUS" }, "countryTo":{ "id":1, "name":"Russia", "code":"RUS" }, "rulesEngineResult":{ "id":1, "transactionId":1, "isClear":false, "clientSanctionId":1, "bankSanctionId":null, "paysystemSanctionId":null, "countrySanctionId":null, "clientSanction":{ "id":1, "code":"SC1", "description":"Client is in DPRK list", "entity_id":1 } }, "operatorResult":null }, { "id":1, "clientFrom":{ "id":1, "name":"Artur Hen", "patronymic":"Andreevich", "birthDay":[ 2001, 1, 1 ], "phoneNumber":"+79993334455", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "clientTo":{ "id":2, "name":"Van Gover", "patronymic":"Andreevich", "birthDay":[ 2002, 1, 2 ], "phoneNumber":"+79993334444", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "amount":303, "currency":"RUB", "bankFrom":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "bankTo":{ "id":1, "name":"VTB", "code":"VTB", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemFrom":{ "name":"MIR", "code":"MIR", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemTo":{ "name":"Mastercard", "code":"MSC", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "transferDate":[ 2023, 3, 29, 7, 0 ], "cityFrom":"Moscow", "cityTo":"Moscow", "countryFrom":{ "id":1, "name":"Russia", "code":"RUS" }, "countryTo":{ "id":1, "name":"Russia", "code":"RUS" }, "rulesEngineResult":{ "id":1, "transactionId":1, "isClear":false, "clientSanctionId":1, "bankSanctionId":null, "paysystemSanctionId":null, "countrySanctionId":null, "clientSanction":{ "id":1, "code":"SC1", "description":"Client is in DPRK list", "entity_id":1 } }, "operatorResult":null }, { "id":1, "clientFrom":{ "id":1, "name":"Ivan Petrov", "patronymic":"Andreevich", "birthDay":[ 2001, 1, 1 ], "phoneNumber":"+79993334455", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "clientTo":{ "id":2, "name":"Ivan Yan", "patronymic":"Andreevich", "birthDay":[ 2002, 1, 2 ], "phoneNumber":"+79993334444", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "amount":100, "currency":"RUB", "bankFrom":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "bankTo":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemFrom":{ "name":"VISA", "code":"VSA", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemTo":{ "name":"VISA", "code":"VSA", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "transferDate":[ 2023, 3, 29, 7, 0 ], "cityFrom":"Moscow", "cityTo":"Moscow", "countryFrom":{ "id":1, "name":"Russia", "code":"RUS" }, "countryTo":{ "id":1, "name":"Russia", "code":"RUS" }, "rulesEngineResult":{ "id":1, "transactionId":1, "isClear":false, "clientSanctionId":1, "bankSanctionId":null, "paysystemSanctionId":null, "countrySanctionId":null, "clientSanction":{ "id":1, "code":"SC1", "description":"Client is in DPRK list", "entity_id":1 } }, "operatorResult":null }, { "id":1, "clientFrom":{ "id":1, "name":"Ivan Petrov", "patronymic":"Andreevich", "birthDay":[ 2001, 1, 1 ], "phoneNumber":"+79993334455", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "clientTo":{ "id":2, "name":"Ivan Yan", "patronymic":"Andreevich", "birthDay":[ 2002, 1, 2 ], "phoneNumber":"+79993334444", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "amount":100, "currency":"RUB", "bankFrom":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "bankTo":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemFrom":{ "name":"VISA", "code":"VSA", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemTo":{ "name":"VISA", "code":"VSA", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "transferDate":[ 2023, 3, 29, 7, 0 ], "cityFrom":"Moscow", "cityTo":"Moscow", "countryFrom":{ "id":1, "name":"Russia", "code":"RUS" }, "countryTo":{ "id":1, "name":"Russia", "code":"RUS" }, "rulesEngineResult":{ "id":1, "transactionId":1, "isClear":false, "clientSanctionId":1, "bankSanctionId":null, "paysystemSanctionId":null, "countrySanctionId":null, "clientSanction":{ "id":1, "code":"SC1", "description":"Client is in DPRK list", "entity_id":1 } }, "operatorResult":null }, { "id":1, "clientFrom":{ "id":1, "name":"Ivan Petrov", "patronymic":"Andreevich", "birthDay":[ 2001, 1, 1 ], "phoneNumber":"+79993334455", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "clientTo":{ "id":2, "name":"Ivan Yan", "patronymic":"Andreevich", "birthDay":[ 2002, 1, 2 ], "phoneNumber":"+79993334444", "citizenshipCountry":{ "id":1, "name":"Russia", "code":"RUS" } }, "amount":100, "currency":"RUB", "bankFrom":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "bankTo":{ "id":1, "name":"Tinkoff", "code":"TNF", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemFrom":{ "name":"VISA", "code":"VSA", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "paySystemTo":{ "name":"VISA", "code":"VSA", "country":{ "id":1, "name":"Russia", "code":"RUS" } }, "transferDate":[ 2023, 3, 29, 7, 0 ], "cityFrom":"Moscow", "cityTo":"Moscow", "countryFrom":{ "id":1, "name":"Russia", "code":"RUS" }, "countryTo":{ "id":1, "name":"Russia", "code":"RUS" }, "rulesEngineResult":{ "id":1, "transactionId":1, "isClear":false, "clientSanctionId":1, "bankSanctionId":null, "paysystemSanctionId":null, "countrySanctionId":null, "clientSanction":{ "id":1, "code":"SC1", "description":"Client is in DPRK list", "entity_id":1 } }, "operatorResult":null } ]

    const data = [
        { name: "Nur", age: 22 },
        { name: "Ann", age: 21 },
        { name: "Kan", age: 23 },
        { name: "Nur", age: 22 },
        { name: "Ann", age: 21 },
        { name: "Kan", age: 23 },
        { name: "Nur", age: 22 },
        { name: "Ann", age: 21 },
        { name: "Kan", age: 23 },
        { name: "Nur", age: 22 },
        { name: "Ann", age: 21 },
        { name: "Kan", age: 23 },
    ]
    const columns = [
        { title: 'Sender client', field: "clientFrom.name" },
        { title: 'Recipient client', field: "clientTo.name" },
        { title: 'Amount', field: "amount" },
        { title: 'Sender Bank', field: "bankFrom.name" },
        { title: 'Recipient Bank', field: "bankTo.name" },
        { title: 'Sender Paysystem', field: "paySystemFrom.name" },
        { title: 'Recipient Paysystem', field: "paySystemTo.name" },
        {
            title: 'Validate', render: rowData => <Link to={`/transaction/validate/${rowData.name}`}>
                <button className="ui button blue">Validate</button>
            </Link>
        }
    ]
    return (<div>
        <ThemeProvider theme={defaultMaterialTheme}>
            <MaterialTable
                title="Suspicious Transactions"
                data={transactions}
                columns={columns}
                options={{
                    filtering: true,
                    pageing: true,
                    pageSize: 6,
                    pageSizeOptions: [6, 12, 24, 48],
                }}
            />
        </ThemeProvider>
    </div>)
} 