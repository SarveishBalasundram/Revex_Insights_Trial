import React from 'react';
import './Header.css';

const Header = () => {
    return (
        <header className="header">
            <div className="dashboard-name">
                RexEx
            </div>
            <div className="filters">
                <input type="text" placeholder="From: dd/mm/yy" className="date-filter" />
                <input type="text" placeholder="To: dd/mm/yy" className="date-filter" />
                <select className="department-filter">
                    <option value="">All Departments</option>
                    <option value="Sales">Sales</option>
                    <option value="Marketing">Marketing</option>
                    <option value="Engineering">Engineering</option>
                    <option value="HR">HR</option>
                </select>
            </div>
        </header>
    );
};

export default Header;
