# SENG201
SENG201 project for 2020 term 1

## Compiling the program:
**IMPORTANT! This project will only compile under Java 8 due to JAXB support being removed in later versions.**
The compiled program can run on later versions of Java.
This was a mistake that wasn't caught before it was too late to fix,
however we were given explicit instructor permission to continue using Java 8 and will provide a copy of the email upon request.

#### First, if you don't have Java 8 installed, you can download it here:
* https://www.oracle.com/java/technologies/javase-jdk8-downloads.html
* The program has not been tested with OpenJDK 8, and we can't guarantee it will work with it.

### Compiling from Eclipse:
#### Importing the Porject into Eclipse
* First, make sure you've installed the .zip and located the src folder
* Create a new, empty Java Project. **Make sure that you selected JavaSE-1.8 as your JRE.**
* Next, **right click the src package** and select **Import->General->File System** ...
* Select the src folder from the project .zip.
* Make sure that all subfolder of src are slected, then select Finish.

**If Eclipse asks you to overwrite settings, select "Yes".**

* There should now be three folders in src, gameLogic, gameScreens, and tests. If any are missing, make sure that you have selected all of the folders in the src folder in the Import wizard.
* If the tests folder is showing errors, it means that JUnit is not in your build path.
* Mousing over one of the annotations in any of the .java files in the tests package should bring a drop down that allows you to add JUnit 5 to your build path.

#### Compiling the project
* Once the project is imported, you can compile the project.
* **Note that the program runs from src/gameLogic/Welcome.java** should you ever get lost
* First, you'll need to tell eclipse the build configuration.
  * The easiest way to do this is to open up the **src/gameLogic** package, right click on **Welcome.java**, then select **Run As->Java Application**.
  * You can close out of the game screen that opens, starting it once is sufficient.
* Then, select **File->Export->Runnable JAR file**
* For **Launch Configuration**, select **Welcome - [your project name]**
* For **Export Destination**, browse to the directory you want the game in and give the file a name.
* For **Library Handling**, select **Extract required ibraries into generated JAR**
* Finally, select **Finish**.
  * If a pop up appears about licenses, select **Ok**
  * Don't worry if a message appears afterwards that "Program compiled with warnings", select **ok**. The program will run just fine.
* **Done! Your executable jar is located wherever you instructed Eclipse to place it.**

### Running the executable:
* Ensure you have java installed on your machine
```
java -version
```
  * If you don't have java installed, see instruction above for installing Java 8
  * Alternatively, any later version of Java should also work
* **Note: The version of Java you are using can be Java 8 or later**
  * We haven't tested earlier versions
* Open a terminal and go to the directory that your executable .jar file is located in
* **To run the game:**
  * Input the following command into your terminal:
  ```
  java -jar ./[your_executable_name].jar
  ```
* Have fun!

