// src/App.js

import React, { useState } from 'react';
import './App.css';
import BarChart from './BarChart';
import LineChart from './LineChart';
import PieChart from './PieChart';
import DonutChart from './DonutChart';
import { Calendar } from 'primereact/calendar';  // Import PrimeReact Calendar
import DataTableComponent from './DataTableComponent';  // Import the DataTableComponent

import 'primereact/resources/themes/saga-blue/theme.css';  // or another theme
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';


function App() {
  const [dates, setDates] = useState(null);

  return (
    <div className="App">
      <header className="header">
        <div className="dashboard-name">
          RexEx Insights
        </div>
        <div className="filters">
          <p className='filter'>Date Range</p>
          <Calendar value={dates} onChange={(e) => setDates(e.value)} selectionMode="range" readOnlyInput hideOnRangeSelection placeholder="Select Date Range" />
          <p className='DeptFilter'>Select Department</p>
          <select className="department-filter">
            <option value="">All Departments</option>
            <option value="Sales">Sales</option>
            <option value="Marketing">Marketing</option>
            <option value="Engineering">Engineering</option>
            <option value="HR">HR</option>
          </select>
        </div>
      </header>
      <div className="chart-container">
        <div className='pie'>
          <div className="chart pie-chart">
            <p className='BreakdownText'>Breakdown of Expense</p>
            <hr></hr>
            <PieChart />
          </div>
          <div className="chart donut-chart">
            <p className='RevenueText'>Revenue vs Expense</p>
            <hr></hr>
            <DonutChart />
          </div>
        </div>
        <div className="chart line-chart">
          <p className='LineText'>Timeline</p>
          <hr></hr>
          <LineChart />
        </div>
        <div className="chart bar-chart">
          <p className='BarText'>Profit vs Loss</p>
          <hr></hr>
          <BarChart />
        </div>
        <div className="table-container" style={{ marginTop: '20px' }}>
          <p className='TableText'>Department Financial Table</p>
          <hr></hr>
          <DataTableComponent /> {/* Use the DataTableComponent */}
        </div>
      </div>

    </div>
  );
}

export default App;
