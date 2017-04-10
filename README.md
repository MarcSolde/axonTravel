# axonTravel

Sample app for Axon Framework using microservices, ES and CQRS.

![](http://forthebadge.com/images/badges/built-by-developers.svg) <br />
![](http://forthebadge.com/images/badges/powered-by-electricity.svg)<br />
![](http://forthebadge.com/images/badges/60-percent-of-the-time-works-every-time.svg)

## Configuration

The microservices are supposed to be running on local on the port:
  - **8080** - Customer Microservice
  - **8081** - Query Microservice
  - **8082** - Order Microservice

Also you need a rabbitMQ running on local with default configuration enabled and a MySQL database running on a docker container (you can check the configuration at `customers/src/main/resources/application.properties`).

The rabbitMQ is used to send messages between the different microservices and the MySQL database is used to exemplify the persistence of an eventStore (keep in mind that the persistence is only implemented in the _customers_ microservice, since it is supposed to be an example)
  
## API paths

  - _POST_ **:8080/newCust/** Given the parameters CustomerID(optional) and money on the body, it creates a customer with the given id and money. 
  
  - _POST_ **:8080/buyProd/** Given the parameters CustomerId, ProductId and the cost of such product, it checks if the user has enough moeny to pay, and creates an order that will be accepted or rejected depending on said condition. If the order is accepted, the cost is substracted from the user's balance.

  - _GET_ **:8081/balance/{id}/** Gives the id and money left of a user with id as {id}

  - _GET_ **:8081/balance/** Gives a list of all customers and their respective balance

  - _GET_ **:8081/order/{id}/** - Gives the id and cost of a Order with id as {id}
  
  - _GET_ **:8081/order/** - Gives a list of all Orders giving away their id and cost.

## Other things
In order to get a runnable .jar with any microservice, run
`mvn package`
