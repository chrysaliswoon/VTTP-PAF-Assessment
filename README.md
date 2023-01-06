# Persistence & Analytics Asessement

## Application Overview
This project is an online e-commerce business. When the checkout button is pressed, the order will be sent to the backend Spring Boot application to be processed and dispatchded to the warehouse for delivery. 

If the dispatch is successful, a delivery id will be returned and the customer will be shown View 1, otherwise View 2. 

## Project Set-up - Task 1

- [X] Create a Git repository for this project
- [X] Unzip the file given which has the following dependencies:
  - Spring Boot Dev Tools
  - Spring Web
  - Thymeleaf
  - JDBC API
  - MySQL Connector Java
  - JSON-P
  - Spring Data MongoDB

## MySQL Database - Task 2 (14 marks)

In the database directory, there is a file called ```data.csv``` which contains the records of 5 users. This is a colon (:) separated file. 

- [X] Create a file called ```schema.sql``` in the database directory
- [X] In the ```schema.sql``` write the following SQL statements to perform the following:
  - [X] Create a database called ```eshop```
  - [X] Create a table called ```customers``` in the ```eshop``` database according to the above requirements
  - [X] Write SQL insert statements in the ```schema.sql``` file to populate the ```customers``` table with customer's data from ```data.csv``` file
- [X] Create a MySQL database hosted in Railway
- [X] Use the ```schema.sql``` to create the eshop database
- [X] Configure eshop Spring Boot application to use the MySQL database that you have provisioned. 
- [X] Start the eshop application and open the landing page at ```localhost:8080```.

*Note: Sensitive info like database password SHOULD NOT be in the  ```application.properties``` file or in the source code.*

## e-Commerce Form 

Check that the following works in the given program:
- [X] A customer can enter his/her name into the name field
- [X] Use the "Add" button to add one or more items into the order
- [X] Items can be deleted from the order with the item's corresponding delete (X) button
- [X] The "Checkout" button will be enabled only if you have entered the following details:
  - [X] Customer's name
  - [X] At least 1 item in your order
- [X] Click the "Checkout" button to submit the order to your e-commerce backend application for processing.

New orders are processed in 3 steps:
1. Creating and Saving the order
2. Dispatching the order to the warehouse
3. Send the result back to the client

## Creating and Saving the order - Task 3 (50 marks)

- [] Write a request handler in the given ```OrderController``` class to process the order
- [] Check if customer is valid
  - [] Complete the method ```CustomerRepository.findCustomerByName()```. This method should check if the customer's name exists in the customers table **(name coloumn)**
  - [] If the customer DOES NOT EXIST, return a NOT FOUND status code with the following JSON payload as the error message:
    ```json
    {"error": "Customer <customer_name> not found"}
    ```
  *Note: DO NOT CHANGE THE SIGNATURE OF THE METHOD ```customerRepository.findCustomerByName()```!*

- [X] Populate the model
Use the provided models (in ```models``` directory) to hold the order details sent from the frontend e-store:
  - [X] Generate a unique 8-character long order id for the order [Use the UUID class to generate the order id]
  - [X] Set the order date

- [X] Create an Order table
  - [X] Create one or more database tables to store the order in the estore database
  - [X] Write your SQL statements to create the table(s) in the ```schema.sql``` file
  - [X] Execute the SQL statement to create the tables

- [] Save the order to the database
  - [] Save the order to the ```estore``` database - create SQL Query
  - [] When you save, you have to ensure the integrity of the order data.
  - [] Write this save order in ```OrderRepository``` class
  - [] If the save fails, return an **Internal Server Error** status with the following JSON payload as the error message:
  ```json
  {"error": <the error message>}
  ```

## Dispatching the order to the warehouse - Task 4 (30 marks)

If task 3 is successful, then the order will need to be dispatched to the warehouse to be packed and shipped to the customer.

- [] Dispatch the order to the following **REST** endpoint with a ```POST``` method
  ```
  http://paf.chuklee.com/dispatch/<order id>
  ```
  The order id is the order id of the order you are dispatching
- [] The POST payload is the created order (from Task 3) in JSON format in the following structure:
  ```json
  {
    "orderID": <order id>,
    "name": <name>,
    "address": <address>,
    "email": <email>,
    "lineItems": [
      {"item": <item>, "quantity": <quantity>},
      {"item": <item>, "quantity": <quantity>},
    ],
    "createdBy": <your name in NRIC>
  }
  ```

- [] Add an additional attribute called ```createdBy``` to the order
- [] Use the ```WarehouseService.dispatch()``` to write the order dispatch function. 

*DO NOT CHANGE THE METHOD SIGNATURE*

-[] If the dispatch request is successful, the endpoint will return an OK status with the delivery id in the following JSON pauload:

```json
{
  "orderId": <order id>,
  "deliveryId": <delivery id>
}
```

*Note: The dispatch can fail if the order details are incorrect / incomplete. The endpoint is also unstable and has a 33% chance of failing.*

- [X] Write SQL statements to create a table called ```order_status``` in the ```schema.sql``` file.
- [X] The table contains the following mandatory columns:
  - [X] order_id
  - [X] delivery_id
  - [X] status
  - [X] status_update
- [X] Create the table in your eshop database

The ```order_status``` is for recording the dispatched status. An order status record is inserted into this table whenever an order is dispatched to the warehouse.

- [] If the dispatch is successful, write the order if, the delivery id and the value **dispatched** in the status column and the timestamp.
- [] If the dispatch is not successful, write the order id, and the value **pending** in the **status** column and the timestamp

## Send the result back to the client - Task 5 (5 marks)

- [] If the dispatch is successful, send an OK status with the following JSON payload:
  ```json
  {
    "orderId": <order id>,
    "deliveryId": <delivery id>,
    "status": "dispatched"
  }
  ```
- [] If the dispatched failed, send an OK status with the following payload:
  ```json
  {
    "orderId": <order id>,
    "status": "pending"
  }
  ```

## API Requests
The e-store front makes the following HTTP request to get the total number of dispatched and pending orders for a customer.

```
GET /api/order/<name>/status
Accept: application/json
```

- [] Create a RESTController called orderRESTController
- [] Write a request handler to process the request and return the required data in the following JSON document structure:
```json
{
  "name": <name>,
  "dispatched": <number of dispatched orders>
  "pending": <number of pending orders>
}
```

- [] Use a **join statement** to get this result from your estore database

## Submission & Deployment - Task 7 (5 marks)

- [X] Push your code to your repository when you have completed your work
- [X] The site must be deployed on Railway and be accessible from a link: vttp-paf.up.railway.app
  - **Step 1:** ```railway init``` [Initialise a new project. Use railway link if you have already initialised and want to link your springboot application to railway]
  - **Step 2:** ```railway up --detach``` [Deploy the project to railway and detach from the deployment]
  - **Step 3:** ```railway add``` [Add database(s) to your project]
  - **Step 4:** Add variables to the app in Railway




## Resources & References

- Loading csv data file: https://stackoverflow.com/questions/59993844/error-loading-local-data-is-disabled-this-must-be-enabled-on-both-the-client
- 