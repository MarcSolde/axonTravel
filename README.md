# axonTravel

Sample app for Axon Framework

![](http://forthebadge.com/images/badges/built-by-developers.svg)
![](http://forthebadge.com/images/badges/powered-by-electricity.svg)

All running on a tomcat server @localhost:8080

API paths

/init - Creates a Customer and an Order, returns "OK {orderCreatedID}

/balance/id - Gives the id and money left of a user with id as {id}

/order/id - Gives the id and cost of a Order with id as {id} (Cost not fully implemented tho)

/order - Gives a list of all Orders giving away their id and cost.


in order to get a runnable .jar with any microservice, run
`mvn package`
