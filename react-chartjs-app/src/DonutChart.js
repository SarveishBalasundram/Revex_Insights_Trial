import React, { useState, useEffect } from 'react';
import { Doughnut } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';

ChartJS.register(ArcElement, Tooltip, Legend);

const DonutChart = () => {
    const [chartData, setChartData] = useState({
        labels: [],
        datasets: [
            {
                label: 'Revenue vs Expense',
                data: [],
                backgroundColor: [],
                borderColor: [],
                borderWidth: 3,
            },
        ],
    });

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:8080/getRevenueExpenseData', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        department: 0, //from dropdown (integer)
                        year:"" //from calendar (String)
                    }),
                });

                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

                const data = await response.json();
                console.log("data ",data); 

                const labels = ['Revenue', 'Expense'];
                const backgroundColors = ['rgba(75, 192, 192, 0.6)', 'rgba(255, 99, 132, 0.6)'];
                const borderColors = ['rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)'];

                function roundUpTo2dp(value) {
                    return Math.ceil(value * 10) / 10;
                }


                const chartData = [
                    roundUpTo2dp(data[0].avg_positive_amount),
                    roundUpTo2dp(data[0].avg_absolute_negative_amount)
                ]; 
                setChartData({
                    labels: labels,
                    datasets: [
                        {
                            label: 'Revenue vs Expense',
                            data: chartData,
                            backgroundColor: backgroundColors,
                            borderColor: borderColors,
                            borderWidth: 3,
                        },
                    ],
                });
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const options = {
        cutout: '70%', // This makes it a donut chart by cutting out the middle
    };

    return <Doughnut data={chartData} options={options} />;
};

export default DonutChart;
