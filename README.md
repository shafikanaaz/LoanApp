Loan Interest Calculator App
----------------------------

This is a console application to calculate simple interest for a given loan amount for a given period.

Simple interest formula:  https://www.investopedia.com/terms/s/simple_interest.asp

This application is build with Java 21 build with Gradle

### Set up instruction

- Pre-requisite: You need to install Java if not already installed. 
- Clone the repository from Github using the following command:  
    ``git clone https://github.com/shafikanaaz/LoanApp.git``
- Go to the project root directory  
    ``cd LoanApp``
- Run the following command to build the project  
    ``./gradlew clean build``
- To run the application, use the 'Run App (^R)' command from IDE or use the following command:  
    ``./gradlew run``

### Sample run output
The application takes the following inputs:
1. Start Date (date)
2. End Date (date)
3. Loan Amount (amount field)
4. Loan Currency (currency)
5. Base Interest Rate (percentage)
6. Margin (percentage)

It returns the following results:
1. Daily Interest Amount without margin
2. Daily Interest Amount Accrued
3. Accrual Date (today's date or the loan end date which ever is earlier )
4. Number of Days elapsed since the Start Date of the loan till Accrual Date
5. Total Interest
6. Daily Accrued Interest

````
********************************************
        LOAN INTEREST CALCULATOR
********************************************


Please select an option to continue:
1. Calculate loan interest
2. See previous calculations
3. Exit

Option : 1
Please input the following loan parameters:
Start date (YYYY-MM-DD): 2024-09-09
End date (YYYY-MM-DD): 2024-09-23
Loan Amount: 1000
Loan Currency: inr
Base Interest Rate (%): 7
Margin (%): 1

Calculating interest...
LoanParams{startDate=2024-09-09, endDate=2024-09-23, amount=1000.0, currency=INR (₹), baseRate=7.0, margin=1.0}
LoanResult:
Daily Interest Without Margin = ₹0.192,
Interest Amount Accrued = ₹3.068,
Accrual Date = 2024-09-23,
Days Since Start Date = 14,
Total Interest = ₹3.068
-------------------------------------
Daily Accrued Interest :
0.21917808219178084
0.4383561643835617
0.6575342465753425
0.8767123287671234
1.0958904109589043
1.315068493150685
1.534246575342466
1.7534246575342467
1.9726027397260275
2.1917808219178085
2.4109589041095894
2.63013698630137
2.849315068493151
3.068493150684932
-------------------------------------

Please select an option to continue:
1. Calculate loan interest
2. See previous calculations
3. Exit

Option : 2
1. LoanParams{startDate=2024-09-09, endDate=2024-09-23, amount=1000.0, currency=INR (₹), baseRate=7.0, margin=1.0}

Do you want to re-run a historical calculation (y/n) :n
Please select an option to continue:
1. Calculate loan interest
2. See previous calculations
3. Exit

Option : 3
Thank you!
````