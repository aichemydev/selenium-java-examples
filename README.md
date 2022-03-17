# selenium-java-examples

Java Selenium testing provides thorough and functioning test cases that can test every aspect of your application.

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

## Using the downloaded TestGold Selenium JAR

You can use the downloaded TestGold Selenium JAR from the TestGold website's `Installation` page instead of relying on a remote repository.
To do this:

- Download the Selenium JAR from the TestGold website and place it in the `reactbank/dependencies` folder.
- Rename the `reactbank/pom.xml` file to `reactbank/pom.xml-backup`.
- Rename the `reactbank/pom.xml-localdepjar` to `pom.xml`
- Make sure maven picks up the changes by running: `mvn clean` and then `mvn validate`.
- Run the examples as directed.

## Running the examples on Windows with PowerShell

You will need a JDK and Maven. We recommend [Adoptium's Temurin distribution](https://adoptium.net/).

- Download the Java JAR package in [Test Gold Interceptor packages](https://dev.k8s.testgold.dev/details/installation).
- Download and install the JDK `.msi` file. Make sure that the "Set JAVA_HOME" option is checked when you install the package.
- Download the binary distribution of Maven from the [Maven website](https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.zip).
- Unzip this and copy the `maven-3.xx-bin` folder to a place on your `C:` drive (we recommend using a directory in `C:\Users\your-username`.).

Add a `MAVEN_HOME` environment variable and the path to the `mvn` binary to your `PATH`. [See this page for help](https://mkyong.com/maven/how-to-install-maven-in-windows/).

- Click on the "Start" button and then type "Advanced system settings" and click on the "View advanced system settings" Control Panel item.
- Click on "Environment Variables" in the dialog that comes up.
- Click on the "New..." button under the "User variables for YOUR_USERNAME" box. Add the path to where you saved the Maven unzipped folder as the value of the `MAVEN_HOME` environment variable.
- Select "Path" in the "User variables for YOUR_USERNAME" box and click on "Edit...". Click on "New" in the box that pops up. Then type: `%MAVEN_HOME%\bin` and hit Enter to add this item to your `Path` environment variable.

Java Selenium JAR package

- Download the Java Selenium JAR from the TestGold website and place it in the `reactbank/dependencies` folder.
- Rename the `reactbank/pom.xml` file to `reactbank/backup.xml`.
- Rename the `reactbank/pom.xml-localdepjar` to `pom.xml`
- Make sure maven picks up the changes by running: `mvn clean` and then `mvn validate`.
- Run the examples as directed. 


Finally, you can run the Selenium Java examples:

- Open a Windows PowerShell terminal by clicking on the "Start" button and typing in: powershell and hitting Enter.
- Navigate to where you have stored these Java examples by using `cd`, for example: `cd ~/Github/selenium-java-examples`.
- Type: `mvn -version` to verify it is working correctly.
- Copy the value of `TG_TOKEN` on the TestGold Installations page and save it as an environment variable by typing: `$env:TG_TOKEN='your token goes here'`.
- Set the following environment variable: `$env:REACTBANK_LANDING_URL='https://demo1.testgold.dev'`.
- Run the unmodified webapp example by using Maven to launch it: `mvn install`.
- Set the following environment variable: `$env:REACTBANK_LANDING_URL='https://demo2.testgold.dev'`.
- Run the modified webapp example by using Maven to launch it: `mvn install`.

Or

You can do this:

1. cd reactbank
2. export your TG_TOKEN, e.g. export TG_TOKEN='eyJ3YWxTZ..'
3. ./test_original_app.bat to test the original site
4. ./test_modified_app.bat to test the modified site, you will see testgold healing
