# selenium-java-examples

Selenium Java example tests

The Test Gold Product allows you to submit a Java selenium jar.

1. It learns from an old JAR run
2. Make code changes to the site
3. It heals your Selenium JAR to work on the new site

Example is now with mvn:

1. cd reactbank
2. export your TG_TOKEN, e.g. export TG_TOKEN='eyJ3YWxTZ..' , get an account from nocode.testgold.dev if you dont have an account. Note that for new users, you have to wait up to 24 hours before you are manually activated. This is a measure to guard against hackers.
3. ./test_original_app.sh to test the original site
4. ./test_modified_app.sh to test the modified site, you will see testgold healing

You can use this pom.xml in your projects, note that you need to add org.aichemy.testgold as a dependency, and add in a new repository, check the pom file for more details.
