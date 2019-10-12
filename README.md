## websocketChat8

* In chatroomTests.java under test, 5 unit tests are developed to test enter, chat, and logout. Tests 1-4 are developed to cover enter and chat, using MockMvc. Test 5 tests logout by using HtmlUnit. A running Chat Room server is expected for Test 5 to run properly. Tests 1-4 using MockMvc do not need that, and they run faster than Test 5 using HtmlUnit.

* In IntelliJ terminal, we can do "mvn spring-boot:run" to start the server. When the Chat Room server is running and listening to port 8080, in IntelliJ Maven -> Lifecycle, we can do multiple clean, test, or package. It takes about 39 sec to run 5 tests. Most runtime of 5 tests is consumed by Test 5, using HtmlUnit. Both test and package in Maven Lifecycle pass 5 unit tests to lead to test result "[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0".

* Please see a recording "chatting online" and a screenshot "package passes 5 tests".
