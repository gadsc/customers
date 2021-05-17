# Customers microservice
- Features:
    - Crud API of customers
    - Search endpoint to execute a free text across all customer fields
    
- Technologies
    - Kotlin
    - Spring
        - Boot
        - MVC
        - Data (Postgres and ElasticSearch)
        - Flyway
        - Cloud Messaging (SNS/SQS)
    - Databases
        - Postgres
        - ElasticSearch
    - LocalStack
    
- Main Decisions
    1. Monolith first approach
        - We choose to use a monolith first approach with two *main packages*, because if our project grow we can easily broke in microservices:
            - Package `api`
                - Have the responsibility to manage all customer operations (Create, Read, Update, Delete, List) in a relational database (Postgres) and produce messages every time that have a change in the customer state.  
            - Package `searcher`
                - Have the responsibility to manage all search operations in a NoSQL database (ElasticSearch), our first search operation is a full text search in all the customers fields
    2. SQS/SNS
        - We choose to use SQS/SNS approach to represent an a async architecture with a component responsible for the search and another with the domain, the best approach right here, is to use a event broker such as Kafka and produce events like `CustomerCreated`, `CustomerUpdated` or `CustomerDeleted` instead of produce a command with the customer data.
    3. LocalStack instead of terraform the infrastructure
        - We choose to use LocalStack instead of terraform because we will need to bootstrap all the infrastructure and it will take a lot of time and a lot of code, because, maybe we will need to build some terraform modules like the ecs because the community module isn't production ready ([ECS community module](https://registry.terraform.io/modules/terraform-aws-modules/ecs/aws/latest)) 
    4. Use package by layer
        - Even the [package by feature](http://www.javapractices.com/topic/TopicAction.do?Id=205) being our favorite approach for microservices we decided to move with a package by layer approach to make more easy to understand the components and the responsibilities. 
    5. Functional tests instead of automatized tests :(
        - Due our time to finish the project we didn't have any automatized test for the `searcher` module, this module had a lot of tests but exclusively functional tests, during this week we will automatized all this tests :) 

- How to run locally
    - Prerequisites:
        - git
        - Java v11
        - Docker
        - Docker compose
    - Copy the `application.yaml.sample` in the `src/main/resources`
    Like this:
      ```shell script
      cp src/main/resources/application.yaml.sample src/main/resources/application.yaml
      ```
    - With the `application.yaml` created run this make command:
      ```shell script
      make local-without-application
      ```
    - After the bootstrap of `localstack`, `postgres` and `elasticsearch` you can run the application with the `./gradlew` or with an IDE
      ```shell script
      ./gradlew bootRun
      ```
