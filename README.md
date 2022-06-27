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

The whole project started off as an idea. I wanted to create another Spring Boot REST-ful API-project.
The idea sounded great, as I was able to greatly improve my SpringBoot skills once again.

After the idea, and the initial [description](#description) I continued with the design of the  whole application:
1. I created a [use case model](#use-case-model), which was then used further into the project to determine, which classes, ... were needed.
2. After that, I moved to the [use case specification](#use-case-specification), to further improve the project by specifying all the cases.
3. From the [use case specification](#use-case-specification), I've created the [domain model](#domain-model). This helped me a lot, later on in the design phase.
4. After the design, I started the planning, by creating a set of [CRC cards](#crc-cards), which helped me complete my understanding of the relationships
5. And last but not least, I created a very useful [class diagram](#class-diagram), which was fairly easy to do, as I already have created a [domain model](#domain-model) and some [CRC cards](#crc-cards).


### The things, which changed

Quite a low amount of stuff changed in the backend. But the classes and dependencies which did are either completely new
classes or just slightly updated dependencies, as things weren't working out as planned.

Also, not one functionality has been left out of the release, since planning.
However, what did change, are newly implemented features which have not been planned.

A more specific explanation on why the stuff, which changed, did change can be found in the [design phase section](#the-design-phase)


### What technologies were being used?

In this section you'll encounter a small list of all the technologies, with another list of all the patterns used 
within this project. The "where to find" part of each table represents the location in the code, of where to find the
just-mentioned component/technology/pattern.

| The technology    | What was it used for?                                                               | Where to find?                                                                                                                                 |
|:------------------|:------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| Java 17           | The entire project is written in Java                                               | [com.auora](https://github.com/Z-100/AUORA/tree/master/src/main/java)                                                                          |
| Spring Boot       | The API-layer of the application (including repositories, entities and controllers) | [com.auora.api.components.[...]](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/components)                            |
| MapStruct         | The mapping between the entities and DTOs of the project                            | [com.auora.api.components.[...].service.mapper](https://github.com/Z-100/AUORA/tree/master/src/main/java/com/auora/api/components)             |
| Lombok            | The getters & setters in the entities. The constructors in other components         | [com.auora](https://github.com/Z-100/AUORA/tree/master/src/main/java/)                                                                         |

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

Listed here, are the test cases. Even though I've implemented plenty of JUnit tests, I still wanted to have some actual 
user tests, as it's hard to test the API, from the API. That's why I've decided to just create four simple OK/NOK
test-cases, of which all test different access points to the API.

The rest of the tests can be found [here](https://github.com/Z-100/AUORA/tree/master/src/test/java).

| Tested component                          | Expected result                                 | Actual result                                      | Passed                   |
|:------------------------------------------|:------------------------------------------------|:---------------------------------------------------|:-------------------------|
| Component name and activity tested        | What should happen after doing something        | What actually happened after doing so              | :heavy_check_mark: or :x:|

#### Account
| Account requests                       | Expected result                                    | Actual result                                      | Passed             |
|:---------------------------------------|:---------------------------------------------------|:---------------------------------------------------|:-------------------|
| Correct login                          | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Incorrect login                        | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| E-Mail not exists register             | User receives a new account + success response     | User receives a new account + success response     | :heavy_check_mark: |
| E-Mail already exists register         | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Get all accounts: Worked               | User receives a list of accounts                   | User receives a list of accounts                   | :heavy_check_mark: |
| Get all accounts: Error in BE          | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Delete account: Correct info           | User receives a success response + account deleted | User receives a success response + account deleted | :heavy_check_mark: |
| Delete account: Incorrect info         | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Update account: Correct info           | User receives a success response + account changed | User receives a success response + account changed | :heavy_check_mark: |
| Update account: Incorrect info         | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Forgotten password: Correct sentence   | User receives a success response + new password    | User receives a success response + new password    | :heavy_check_mark: |
| Forgotten password: Incorrect sentence | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |

#### Question
| Question requests                      | Expected result                                    | Actual result                                      | Passed             |
|:---------------------------------------|:---------------------------------------------------|:---------------------------------------------------|:-------------------|
| Get questions from acc: Worked         | User receives a list of questions                  | User receives a list of questions                  | :heavy_check_mark: |
| Get questions from acc: Error in BE    | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add question: Correct info             | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add question: Incorrect info           | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add upvote: Correct info               | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add upvote: Incorrect info             | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add downvote: Correct info             | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add downvote: Incorrect info           | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add comment: Correct info              | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add comment: Incorrect info            | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Delete question: Correct info          | User receives a success response, question deleted | User receives a success response, question deleted | :heavy_check_mark: |
| Delete question: Incorrect info        | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |

#### Thread
| Thread requests                        | Expected result                                    | Actual result                                      | Passed             |
|:---------------------------------------|:---------------------------------------------------|:---------------------------------------------------|:-------------------|
| Get threads from acc: Worked           | User receives a list of threads                    | User receives a list of threads                    | :heavy_check_mark: |
| Get threads from acc: Error in BE      | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add thread: Correct info               | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add thread: Incorrect info             | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add upvote: Correct info               | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add upvote: Incorrect info             | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add downvote: Correct info             | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add downvote: Incorrect info           | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add comment: Correct info              | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add comment: Incorrect info            | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Delete thread: Correct info            | User receives a success response, thread deleted   | User receives a success response, thread deleted   | :heavy_check_mark: |
| Delete thread: Incorrect info          | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |

#### Comment
| Comment requests                       | Expected result                                    | Actual result                                      | Passed             |
|:---------------------------------------|:---------------------------------------------------|:---------------------------------------------------|:-------------------|
| Get comments from acc: Worked          | User receives a list of comments                   | User receives a list of comments                   | :heavy_check_mark: |
| Get comments from acc: Error           | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Get comments from question: Worked     | User receives a list of comments                   | User receives a list of Comments                   | :heavy_check_mark: |
| Get comments from question: Error      | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Get comments from thread: Worked       | User receives a list of comments                   | User receives a list of comments                   | :heavy_check_mark: |
| Get comments from thread: Error        | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add upvote: Correct info               | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add upvote: Incorrect info             | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add downvote: Correct info             | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add downvote: Incorrect info           | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Add comment: Correct info              | User receives a success response                   | User receives a success response                   | :heavy_check_mark: |
| Add comment: Incorrect info            | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |
| Delete comment: Correct info           | User receives a success response, comment deleted  | User receives a success response, comment deleted  | :heavy_check_mark: |
| Delete comment: Incorrect info         | User receives an error response                    | User receives an error response                    | :heavy_check_mark: |

### The design phase

#### Use Case Model

The usecase model is a great way to start off the planning of an application. I defined most of the cases, a user or,
a developer should be able to access. ("Most of" means, that I later on in the design phase added one or the other).

The actors "visitor" and "member" are hypothetical, as I didn't want to implement a frontend, but...
With some checks, a visitor could e.g. never write a comment, as a use account is needed for that.
This means I've even implemented that!

As I've added some more functionality to the application, the usecase of course had to change too. This is shown in the
after picture.

##### Use case model after planning
![usecase-before](https://github.com/Z-100/AUORA/blob/master/other/usecase/usecase_before.png)

##### Use case model after implementation
![usecase-after](https://github.com/Z-100/AUORA/blob/master/other/usecase/usecase_after.png)


#### Use Case Specification

The usecase specification is, as the name suggests, a more specific and detailed version of the usecase diagram.

In this more specific diagram I've created some useful cases, which describe each individual usecase exactly the way
it's intended to be working.

In the updated version of the usecase specification I've simply created the missing specifications of the updated version
of the "normal" usecase diagram.

##### Usecase specification after planning
[usecase-spec-before](https://github.com/Z-100/AUORA/blob/master/other/usecase/usecase_spec_before.md)

##### Usecase specification after planning
[usecase-spec-after](https://github.com/Z-100/AUORA/blob/master/other/usecase/usecase_spec_after.md)

 #### Domain Model

The domain model was a great way to plan ahead, as, even tho, this application isn't particularly huge, it still is a _bigger_ project.

The advantages of such a domain model were, that I could already plan out the layout I wanted to implement, without 
having to struggle out all the properties and methods I'd eventually need for the API to work securely and correctly.

I started off, by separating the application into four different sections: One for each entity, as this is the REST-ful
way of creating an application.
After that, I pretty much just had to work out one of these structures, which I then repeated three more times, to have
the entire application implemented.
Then, I've drawn some more liens, which would later on be constraints between the entities, and I was done.

##### Domain model after planning
![domain-before](https://github.com/Z-100/AUORA/blob/master/other/domain/domain_before.png)

##### Domain model after implementation
![domain-before](https://github.com/Z-100/AUORA/blob/master/other/domain/domain_after.png)


#### CRC Cards

The crc cards have been there since the beginning. With the help of these mighty cards, it was easier to further
understand, which class should have which dependency and/or functionality.

The cards are fairly easy to create and were absolutely worth it, regarding the expense <-> result ratio.

The cards were updated accordingly to the domain-, as well as the class diagram, due to changes in the dependencies
and functionalities.

##### CRC-cards after planning
[crc-before](https://github.com/Z-100/AUORA/blob/master/other/crc/cards_before.json)

##### CRC-cards after implementation
[crc-after](https://github.com/Z-100/AUORA/blob/master/other/crc/cards_after.json)


#### Class Diagram

In the class diagram, some stuff has been modified; some more classes were added, I switched up some relationships, ...

The reason for that is simple. I am not as advanced in Spring Boot as developers, programming Spring Boot applications for 10 years straight and there were many new ideas, just coming into my mind, as I was doing some of my programming and equally many things, which weren't working the way intended. 

An example for that, was the new dependencies in between the Thread/Question <- Comment Services. I originally intended to just pass in the id as a foreign key to set it. But I declared the foreign key as an object of that Type and not just a simple Long. Therefore, I'd have to use the other repositories.
<br/>
As this would've been a dumb decision and work against SOLID, I just exported the "addComment" methods to the corresponding Question & Thread Services.

Another example would be the entirety of the Validator & Factory. I did not intend to use the Factory pattern, but saw it's one of the requirements and implemented that as well. The Validator was another idea, as multiple of the same checks would've been redundant.

##### Class diagram after planning
![class-before](https://github.com/Z-100/AUORA/blob/master/other/class/before.png)

##### Class diagram after implementation
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
| Code is the same as the class diagram                                      |  🟢  |
| Complex classes / methods commented                                        |  🟢  |
| App works as defined in the use case                                       |  🟢  |
| App surpasses it's own expectations                                        |  🟢  |
| Coded with SOLID                                                           |  🟢  |
| Code includes design patterns                                              |  🟢  |
| Changes to the design are documented (New class & usecase diagrams         |  🟢  |

### Documentation

| The requirement                                                            | Done |
|:---------------------------------------------------------------------------|:----:|
| Includes test cases (Test use cases)                                       |  🟢  |
| Includes the process and splitting of work                                 |  🟢  |
| Repository explained                                                       |  🟢  |
