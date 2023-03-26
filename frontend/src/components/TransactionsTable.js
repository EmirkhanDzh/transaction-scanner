import React from "react";
import MaterialTable from "material-table";
import { ThemeProvider, createTheme } from '@mui/material';
import { Link } from "react-router-dom";

export const TransactionsTable = () => {
    const defaultMaterialTheme = createTheme();
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
        { title: 'Name', field: "name" },
        { title: 'Age', field: "age" },
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
                data={data}
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