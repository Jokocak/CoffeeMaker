# CoffeeMaker


*Line Coverage (should be >=70%)*

![Coverage](.github/badges/jacoco.svg)

*Branch Coverage (should be >=50%)*

![Branches](.github/badges/branches.svg)

## Description of Project and Technologies Used
TODO

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
