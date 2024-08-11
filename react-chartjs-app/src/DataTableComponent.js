import React, { useState, useEffect } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';

function DataTableComponent() {
    const [products, setProducts] = useState([]);
    const [globalFilter, setGlobalFilter] = useState(null);

    useEffect(() => {
        // Mock data for example purposes
        const fetchData = async () => {
            const data = [
                // { department: 'City Council', revenue: '1,000,000 MYR', expense: '500,00 MYR', ratio: '50%' },
                { department: 'A', revenue: '1,000,000', expense: '500,00', ratio: '50%' },
                { department: 'B', revenue: '2,000,000', expense: '600,00', ratio: '50%' },
                { department: 'C', revenue: '3,000,000', expense: '700,00', ratio: '50%' },
                { department: 'D', revenue: '4,000,000', expense: '800,00', ratio: '50%' },
                { department: 'E', revenue: '5,000,000', expense: '900,00', ratio: '50%' },
                { department: 'F', revenue: '6,000,000', expense: '1000,00', ratio: '50%' },

                // Add more products as needed
            ];
            setProducts(data);
        };
        fetchData();
    }, []);

    return (
        <div className="table-container">
            {/* <div className="p-d-flex p-jc-between p-ai-center p-mb-4">
                <h3 className="p-m-0 p-datatable-header">Product List</h3>
                <span className="p-input-icon-left">
                    <i className="pi pi-search" />
                    <InputText
                        type="search"
                        onInput={(e) => setGlobalFilter(e.target.value)}
                        placeholder="Search..."
                    />
                </span>
            </div> */}

            <DataTable
                value={products}
                paginator
                rows={5}
                globalFilter={globalFilter}
                sortMode="multiple"
                emptyMessage="No products found."
                className="p-datatable-sm"
            >
                <Column field="department" header="Department" sortable />
                <Column field="revenue" header="Revenue (USD)" sortable />
                <Column field="expense" header="Expense (USD)" sortable />
                <Column field="ratio" header="Ratio (%)" sortable />
                {/* <Column
                    header="Action"
                    body={(rowData) => (
                        <Button
                            label="View"
                            icon="pi pi-eye"
                            className="p-button-rounded"
                            onClick={() => alert(`Viewing ${rowData.name}`)}
                        />
                    )}
                /> */}
            </DataTable>
        </div>
    );
}

export default DataTableComponent;
