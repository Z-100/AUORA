# Usecase specification

Here you'll find a more specific usecase specification of the [usecase](https://github.com/Z-100/AUORA/blob/master/other/usecase/usecase_after.drawio)

<br/><br/>

| Usecase Nr.    | 1                                                                                               |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Create account                                                                                  |
| Actors         | User                                                                                            |
| Preconditions  | Has API connection. Is not logged in                                                            |
| Main scenario  | User enters information, which is sent to backend and registers a user                          |
| Postconditions | If everything worked: User has a new account, else: Error message stating issue                 |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 2                                                                                               |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Login                                                                                           |
| Actors         | User                                                                                            |
| Preconditions  | Has API connection. Is not logged in                                                            |
| Main scenario  | User enters information, which is sent to backend and checks credentials                        |
| Postconditions | If everything worked: User receives token, else: Error message stating issue                    |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

(Log off can and will not be implemented, as there won't be enough time left to create a front- and backend)
| Usecase Nr.    | 3                                                                                 |
|:---------------|:----------------------------------------------------------------------------------|
| Usecase title  | Log off                                                                           |
| Actors         | User                                                                              |
| Preconditions  | Is logged in                                                                      |
| Main scenario  | User presses button, which deletes token                                          |
| Postconditions | If everything worked: User is being logged off, else: Error message stating issue |
| Exceptions     | Bug |

<br/><br/>

| Usecase Nr.    | 4                                                                                               |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Ask question                                                                                    |
| Actors         | User                                                                                            |
| Preconditions  | Is logged in                                                                                    |
| Main scenario  | User enters information, which is sent to backend and saved                                     |
| Postconditions | If everything worked: New viewable & rateable question, else: Error message stating issue       |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 5                                                                                               |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Start thread                                                                                    |
| Actors         | User                                                                                            |
| Preconditions  | Is logged in                                                                                    |
| Main scenario  | User enters information, which is sent to backend and saved                                     |
| Postconditions | If everything worked: New viewable & rateable question, else: Error message stating issue       |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 6                                                                                               |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Write comment                                                                                   |
| Actors         | User                                                                                            |
| Preconditions  | Is logged in, question/thread to comment on exists                                              |
| Main scenario  | User enters information, which is sent to backend and saved                                     |
| Postconditions | If everything worked: New viewable & rateable comment, else: Error message stating issue        |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 7                                                                                               |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Up/down vote comment                                                                            |
| Actors         | User                                                                                            |
| Preconditions  | Is logged in, comment exists, user hasn't voted that comment yet                                |
| Main scenario  | User calls URL, which is sent to backend and saved                                              |
| Postconditions | If everything worked: Comment upvote-count +/- 1, else: Error message stating issue             |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 8                                                                                               |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Up/down vote question                                                                           |
| Actors         | User                                                                                            |
| Preconditions  | Is logged in, question exists, user hasn't voted that question yet                              |
| Main scenario  | User calls URL, which is sent to backend and saved                                              |
| Postconditions | If everything worked: Question upvote-count +/- 1, else: Error message stating issue            |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 9                                                                                               |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | View question                                                                                   |
| Actors         | Visitor                                                                                         |
| Preconditions  | Question exists.                                                                                |
| Main scenario  | User calls URL, which is sent to backend and responds with the question                         |
| Postconditions | If everything worked: Visitor recieves question, else: Error message stating issue              |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 10                                                                                              |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | View thread                                                                                     |
| Actors         | Visitor                                                                                         |
| Preconditions  | Thread exists.                                                                                  |
| Main scenario  | User calls URL, which is sent to backend and responds with thread                               |
| Postconditions | If everything worked: Visitor recieves entire thread, else: Error message stating issue         |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 11                                                                                              |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | View comments                                                                                   |
| Actors         | Visitor                                                                                         |
| Preconditions  | Comment exists. Comment is on question/thread                                                   |
| Main scenario  | User calls URL, which is sent to backend and responds with all comments of a question/thread    |
| Postconditions | If everything worked: Visitor recieves all comments, else: Error message stating issue          |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 12                                                                                              |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Up/down vote thread                                                                             |
| Actors         | User                                                                                            |
| Preconditions  | Is logged in, question exists, user hasn't voted that question yet                              |
| Main scenario  | User calls URL, which is sent to backend and saved                                              |
| Postconditions | If everything worked: thread   upvote-count +/- 1, else: Error message stating issue            |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 13                                                                                              |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Forgotten password                                                                              |
| Actors         | User                                                                                            |
| Preconditions  | Remembers validation sentence and e-mail                                                        |
| Main scenario  | User calls URL, which is sent to backend and then validated                                     |
| Postconditions | If everything worked: New password saved in database, else: Error message stating issue         |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |

<br/><br/>

| Usecase Nr.    | 13                                                                                              |
|:---------------|:------------------------------------------------------------------------------------------------|
| Usecase title  | Update account                                                                                  |
| Actors         | User                                                                                            |
| Preconditions  | Enters correct password and email and new password and new email                                |
| Main scenario  | User calls URL, which is sent to backend and then saved                                         |
| Postconditions | If everything worked: New password & email saved in database, else: Error message stating issue |
| Exceptions     | API not started, wrong URL called, wrong number of arguments/parameters, wrong authentication   |