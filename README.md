# Stock Tracker Service
This project is created to fulfill the Layanan dan Aplikasi Web course synchronous service assignment.
Copied from the old repository on 29/06/2023.
Java: 19
SDK: 19

## Features:
- Add Tracker
Endpoint: /add-tracker
Method: POST
Parameter: **symbol**(String), **lot**(int), **averagePrice**(double), **profitTarget**(int).
Return: **StockTracker Object** if the stock symbol was valid, else will return *null*.
- Get Report
Endpoint: /report
Method: GET
Parameter: -
Return: **stockSymbol**(Symbol), **averagePrice**(Your average price), **closedPrice**(Last closed price), **stockPriceChange**(Price change between average and the last closed price), **gainOrLossAmount**(Your gain or loss amount), **stockChangePercentage**(Price change by its percentage), **profitTarget**(Your profit target percentage).
