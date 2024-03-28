# CoffeeMaker


*Line Coverage (should be >=70%)*

![Coverage](.github/badges/jacoco.svg)

*Branch Coverage (should be >=50%)*

![Branches](.github/badges/branches.svg)

## Description of Project and Technologies Used
In this project, our team collaborated to develop a comprehensive inventory management system as part of our coursework. We utilized various technologies and frameworks to ensure the efficiency and functionality of the system. Here's an overview of the technologies we employed:

### Backend
Java: We chose Java as the backend language due to its robustness, versatility, and extensive ecosystem of libraries and tools. Java provided the foundation for implementing the core functionalities of our inventory management system.
Spring Framework: For web application development, we leveraged the Spring Framework. Spring offered a comprehensive suite of tools and libraries for building enterprise-grade Java applications, including features such as dependency injection, aspect-oriented programming, and MVC architecture, which facilitated the development of our backend components.
MySQL: To persistently store data such as inventory items, ingredients, and recipes, we utilized MySQL, a widely-used relational database management system. MySQL allowed us to efficiently manage and query large datasets while ensuring data integrity.
### Frontend
AngularJS: For developing the frontend of our system, we opted for the AngularJS framework. AngularJS facilitated the creation of dynamic and interactive user interfaces, enabling seamless navigation and interaction with the inventory management system.
### Communication
REST APIs: To establish communication between the backend and frontend components of our system, we implemented RESTful APIs. These APIs served as the endpoints through which data could be exchanged between the client-side and server-side components, enabling smooth and efficient data transmission and manipulation.
By leveraging these technologies and frameworks, including the Spring Framework for web application development, we were able to collaboratively develop a robust inventory management system that streamlined the tracking and management of inventory, ingredients, and recipes.

## API Endpoint
### localhost8080:/api/v1/ingredient
This endpoint provides a list of ingredients available. Each ingredient entry includes its unique identifier (id), the quantity (amount), and the name of the ingredient (name).
```
[{"id":2,"amount":22,"name":"Chocolate"},{"id":4,"amount":40,"name":"Coffee"}]
```
### localhost8080:/api/v1/inventory
This endpoint retrieves information about the inventory available. It returns a list of inventory items, each identified by a unique identifier (id).
```
[{"id":1}]
```
### localhost8080:/api/v1/inventory_ingredients
This endpoint provides a mapping between inventory items and the ingredients they contain. Each entry includes the inventory_id and ingredients_id, representing the relationship between an inventory item and an ingredient it contains.
```
[{"inventory_id":1,"ingredients_id":2},{"inventory_id":1,"ingredients_id":4}]
```
### localhost8080:/api/v1/recipe
This endpoint retrieves information about available recipes. It returns a list of recipes, each containing a unique identifier (id), the name of the recipe (name), and the price of the recipe (price).
```
[{"id":5,"name":"Mocha","price":2},{"id":6,"name":"Latte","price":4}]
```
### localhost8080:/api/v1/recipe_ingredients
This endpoint provides a mapping between recipes and the ingredients required to make them. Each entry includes the recipe_id and ingredients_id, representing the relationship between a recipe and its required ingredients.
```
[{"recipe_id":5,"ingredients_id":2},{"recipe_id":6,"ingredients_id":4}]
```
