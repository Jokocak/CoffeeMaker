# CoffeeMaker


*Line Coverage (should be >=70%)*

![Coverage](.github/badges/jacoco.svg)

*Branch Coverage (should be >=50%)*

![Branches](.github/badges/branches.svg)

## API Endpoint
### localhost8080:/api/v1/ingredient
```
[{"id":2,"amount":22,"name":"Chocolate"},{"id":4,"amount":40,"name":"Coffee"}]
```
### localhost8080:/api/v1/inventory
```
[{"id":1}]
```
### localhost8080:/api/v1/inventory_ingredients
```
[{"inventory_id":1,"ingredients_id":2},{"inventory_id":1,"ingredients_id":4}]
```
### localhost8080:/api/v1/recipe
```
[{"id":5,"name":"Mocha","price":2},{"id":6,"name":"Latte","price":4}]
```
### localhost8080:/api/v1/recipe_ingredients
```
[{"recipe_id":5,"ingredients_id":2},{"recipe_id":6,"ingredients_id":4}]
```
