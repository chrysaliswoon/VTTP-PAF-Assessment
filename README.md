# Persistence & Analytics Asessement

## Application Overview
This project is an online e-commerce business. When the checkout button is pressed, the order will be sent to the backend Spring Boot application to be processed and dispatchded to the warehouse for delivery. 

If the dispatch is successful, a delivery id will be returned and the customer will be shown View 1, otherwise View 2. 

## Project Set-up - Task 1

- [X] Create a Git repository for this project: https://github.com/chrysaliswoon/VTTP-PAF-Assessment
- [X] Generate a SpringBoot application and include the following dependencies:
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

## e-Commerce Form - Task 3 Part 1 (50 marks)

- [] A customer enters his/her name into the name field
- [] Use the "Add" button to add one or more items into the order
- [] Items can be deleted from the order with the item's corresponding delete (X) button
- [] The "Checkout" button will be enabled only if you have entered the following details:
  - [] Customer's name
  - [] At least 1 item in your order
- [] Click the "Checkout" button to submit the order to your e-commerce backend application for processing.

New orders are processed in 3 steps:
1. Creating and Saving the order
2. Dispatching the order to the warehouse
3. Send the result back to the client

## Creating and Saving the order - Task 3 Part 2 (50 marks)

- [] Write a request handler in the given ```OrderController``` class to process the order
- [] Check if customer is valid
  - [] Complete the method ```CustomerRepository.findCustomerByName()```. This method should check if the customer's name exists in the csutomers table **(name coloumn)**
  - [] If the customer DOES NOT EXIST, return a NOT FOUND status code with the following JSON payload as the error message:
    ```json
    {"error": "Customer <customer_name> not found"}
    ```
  *Note: DO NOT CHANGE THE SIGNATURE OF THE METHOD ```customerRepository.findCustomerByName()```!*

- [] Populate the model
  - Use the 


## Submission & Deployment - Task 7 (5 marks)

- [] Push your code to your repository when you have completed your work
- [] The site must be deployed on Railway and be accessible from a link: vttp-paf.up.railway.app
  - **Step 1:** ```railway init``` [Initialise a new project. Use railway link if you have already initialised and want to link your springboot application to railway]
  - **Step 2:** ```railway up --detach``` [Deploy the project to railway and detach from the deployment]
  - **Step 3:** ```railway add``` [Add database(s) to your project]
  - **Step 4:** Add variables to the app in Railway




## Resources & References

- 