# AUORA - An open source forum

## Description
AUORA is a 'high-level, high-quality, very-good, very-nice' REST-API built with lots of love <3

### What is AUORA?

Auora is an open source forum to discuss any topic in the form of a so-called "thread",
ask any questions to the community, and/or to comment answers or opinions beneath those
questions and threads. <br/>
And of course, the most important feature of any application, you can also up and/or down
vote any question, thread or comment!


Generally speaking it's the base of such a forum. In this repository you'll find anything 
related to the [design phase](https://github.com/z-100/AUORA/tree/master/other) and
[the code](https://github.com/z-100/AUORA). The entire project basically is a REST-API, which 
is expandable and easy to use:
* You can call it by simple POST/GET request via any browser
* It works with a small amount of parameters, of which most are used for authentication
    * Store once, use many times
* To add more features like topics or categories, just add more entities.

##Documentation


### How I worked myself through the project

The whole project started off as an idea. I wanted to create another Spring Boot RESTful API-project.
The idea sounded great, as I was able to greatly improve my SpringBoot skills once again.

After the idea, and the initial [description](#description) I continued with the design of the  whole application:
1. I created a [use case model](#use-case-model), which was then used further into the project to determine, which classes, ... were needed.
2. After that, I moved to the [use case specification](#use-case-specification), to further improve the project by specifying all the cases.
3. From the [use case specification](#use-case-specification), I've created the [domain model](#domain-model). This helped me a lot, later on in the design phase.
4. After the design, I started the planning, by creating a set of [CRC cards](#crc-cards), which helped me complete my understanding of the relationships
5. And last but not least, I created a very useful [class diagram](#class-diagram), which was fairly easy to do, as I already have created a [domain model](#domain-model) and some [CRC cards](#crc-cards).


### The things, which changed

#### The updated class diagram

Quite a low amount of stuff changed in the backend. But the classes and dependencies which did are listed here:
* EntityFactory.java
* ...

![New classdiagram](https://github.com/z-100/auora/tree/master/other/class/updated.png)

#### The updated use case

Not many functionalities have changed in the frontend. What I forgot in planning however, was security:
* I implemented further password validation
* ...

![New usecase diagram](https://github.com/z-100/auora/tree/master/other/usecase/updated.png)

### What technologies were being used?

| The technology | What was it used for?                                                               | Where to find?                                                                                                                    |
|:---------------|:------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------|
| Java 17        | The entire project is written in Java                                               | [com.auora](https://github.com/Z-100/AUORA/tree/master/src/main/java)                                                             |
| Spring Boot    | The API-layer of the application (including repositories, entities and controllers) | [com.auora.api.components.[...]](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/components)               |
| MapStruct      | The mapping between the entities and DTOs of the project                            | [com.auora.api.components.[...].service.mapper](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/components)|
| Lombok         | The getters & setters in the entities. The constructors in other components         | [com.auora](https://github.com/Z-100/AUORA/tree/master/src/main/java/)                                                            |

| The pattern       | What it does in the project                                                         | Where to find?                                                                                                                                 |
|:------------------|:------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| Factory Pattern   | Instantiates all kinds of objects                                                   | [com.auora.service.impl.EntityFactory](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/service/impl/EntityFactory.java) |
| Bridge Pattern    | Sends data from API- to backend-layer                                               | [com.auora.api.components.[...].controller](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/components)                 |
| Singleton Pattern |                                                                                     | [com.auora.api.components.[...].service.mapper](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/components)             |
| SOLID - S         | Each class/method has one general purpose                                           | [com.auora](https://github.com/Z-100/AUORA/tree/master/src/main/java/)                                                                         |
| SOLID - O         | Entities can not be modified. Only properties via setters and getters               | [com.auora.api.components.[...].entity](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/components)                     |
| SOLID - L         | Classes are not being extended if its' unnecessary                                  | [com.auora](https://github.com/Z-100/AUORA/tree/master/src/main/java/)                                                                         |
| SOLID - I         | As seen in e.g. .account: Three different interfaces for different responsibilities | [com.auora.api.components.[...].service.crud](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/components)               |
| SOLID - D         |                                                                                     | [com.auora](https://github.com/Z-100/AUORA/tree/master/src/main/java/)                                                                         |

### The test cases


### The design phase

#### Use Case Model

![usecase-before](https://github.com/Z-100/AUORA/tree/blob/other/usecase/before.png)
![usecase-after](https://github.com/Z-100/AUORA/tree/blob/other/usecase/after.png)

#### Use Case Specification

![usecase-spec-before](https://github.com/Z-100/AUORA/blob/master/other/usecase/usecase_spec.png)
![usecase-spec-after](https://github.com/Z-100/AUORA/blob/master/other/usecase/usecase_spec_after.png)

#### Domain Model

![domain-before](https://github.com/Z-100/AUORA/blob/master/other/domain/before.png)
![domain-after](https://github.com/Z-100/AUORA/blob/master/other/domain/before.png)

#### CRC Cards

The crc cards have been there since the beginning.

[crc-before](https://github.com/Z-100/AUORA/blob/master/other/domain/cards.json)
[crc-after](https://github.com/Z-100/AUORA/blob/master/other/domain/cards_after.json)


#### Class Diagram

In the class diagram, some stuff has been modified; some more classes were added, I switched up some relationships, ...

The reason for that is simple. I am not as advanced in Spring Boot as developers, programming Spring Boot applications for 10 years straight and there were many new ideas, just coming into my mind, as I was doing some of my programming and equally as many things, which weren't working the way intended. 

An example for that, was the new dependencies in between the Thread/Question <- Comment Services. I originally intended to just pass in the id as a foreign key to set it. But I declared the foreign key as an object of that Type and not just a simple Long. Therefore I'd have to use the other repositories.
<br/>
As this would've been a dumb descision and work against SOLID, I just exported the "addComment" methods to the corresponding Question & Thread Services.

Another example would be the entirety of the Validator & Factory. I did not intend to use the Factory pattern, but saw it's one of the requirements and implemented that aswell. The Validator was another idea, as multiple of the same checks would've been redundant.

##### Class diagram after planning
![class-before](https://github.com/Z-100/AUORA/blob/master/other/class/before.png)

##### Class diagram after implementing
![class-after](https://github.com/Z-100/AUORA/blob/master/other/class/after.png)


## Requirements

### Design**

| The requirement                                                            | Done |
|:---------------------------------------------------------------------------|:----:|
| Clear description of application                                           |  🟢  |
| Detailed usecase (correct relations & actors)                              |  🟢  |
| Detailed domain model (reflects business model, clearly defined relations) |  🟢  |
| CRC Cards                                                                  |  🟢  |
| Use case specifications (Clear structure, pre- / post-conditions)          |  🟢  |
| Class diagram (Clear from domain, show methods and associations)           |  🟢  |

### Code

| The requirement                                                            | Done |
|:---------------------------------------------------------------------------|:----:|
| Good code                                                                  |  🟢  |
