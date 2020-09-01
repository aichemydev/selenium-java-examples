# selenium-java-examples
Selenium Java example tests

The Test Gold Product allows you to submit a Java selenium jar.

1. It learns from an old JAR run
2. Make code changes to the site
3. It heals your Selenium JAR to work on the new site

Under reactbank/page_object_model_tests, you can see java page object model tests built via test NG.

1. To build an old site jar, run build_old_selenium_jar.sh, the jar will be built in ./build/libs/ReactBank-test-all-0.1-SNAPSHOT.jar
2. To build the new site jar, run build_new_selenium_jar.sh, the jar will be built in ./build/libs/ReactBank-test-all-0.1-SNAPSHOT.jar

NOTE:
The only difference between the new site and the old site jars is that it is pointing to a different website in the properties file, https://demo1.testgold.dev (old site) or https://demo2.testgold.dev (new site)
