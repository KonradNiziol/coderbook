# Spring learning!
A project designed to consolidate and expand knowledge about the Spring environment.

## Technology stack
* Spring Boot
* Spring Data
* Spring MVC
* Spring Security


## Tasks completed
- [x] added the possibility of logging in by the user (from the model in the database to the form for registration)
- [x] added support for Spring Security and log in form for user
- [x] password format check and dispaly errors in reg-form (added custom annotation 'ValidPassword.java')
- [x] added user verifications by sending a token to an e-mail

## TODO List
- [ ] create Component with examples messages (ERROR, WARNING, SUCCES) and replace all redundante code.
- [ ] checking that the passwords are the same
- [ ] Valid entered object from client GUI
- [ ] Create new preety mail format for users with token for registration
- [ ] Add generation new token for user request and removal of invalidities out of date token
- [ ] All system mesages should be created on servises (pl.kniziol.coderbook.controller.SingController#activateAccount)
- [ ] i18n for all messages send to user (PL, ENG)
- [ ] hiding the buttons on the page, depending on the role
- [ ] exception service( wrong url and app exception)
- [ ] Doker!
