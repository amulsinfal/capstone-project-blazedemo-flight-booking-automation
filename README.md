## Automation Framework for BlazeDemo Flight Booking Application

**Project Overview:** BlazeDemo End-to-End Automation Framework  
**Application Under Test:** BlazeDemo flight booking application.  
**URL:** https://blazedemo.com  
**Objective:** To design, build, and execute an automated testing framework to validate the end-to-end user lifecycle of the BlazeDemo flight booking application.

### **PREREQUISITES**
1. **Programming Language:** Java v21.0.11  
2. **Automation Tool:** Selenium Webdriver v4.45.0  
3. **Testing Framework:** TestNG v7.8.0  
4. **Dependency & Build Management:** Maven v3.9.14  
5. **Design Pattern:** Page Object Model (POM)  
6. **IDE: Eclipse Version:** 2026-03 (4.39.0)

### Creating Maven Project:
**Group Id:** capstone-project  
**Artifact Id:** blazedemo-automation  
**Packaging:** jar  
<br>
<img width="904" height="687" alt="image" src="https://github.com/user-attachments/assets/7f3088a1-5ee0-44ca-a1d2-7e1d1b18e52c" />  
<br>

**POM.xml:**  
<img width="822" height="926" alt="GUmFmvjB1u" src="https://github.com/user-attachments/assets/c25f4825-e994-4858-9eac-aa0302487788" />

### **Project Structure:**  
<br>
<img width="515" height="935" alt="eclipse_Jjaw3GuUNX" src="https://github.com/user-attachments/assets/e3bbe65c-cf82-4ed2-b606-0052a8c6d054" />

### **Packages:**
- **com.blazedemo.base** - It contains BaseTest.java, which is the parent class for all the test classes.
- **com.blazedemo.driver** - This package contains BrowserFactory.java, it checks the browser name to open Chrome, Firefox, or Edge browser automatically. The package also contains DriverFactory.java, which puts the active browser window inside a ThreadLocal variable. This helps us run tests in parallel in separate browsers.  
- **com.blazedemo.pages** - This package contains all page classes. Each Java files for the individual pages of the website, like HomePage.java and ReservePage.java. Each file contains the web locators (like XPaths) and methods perform actions on the elements like click, type etc.  
- **com.blazedemo.listeners** - The package contains the TestListeners.java class, which implements the ITestListener interface.The class listens for specific event triggers, Like when test starts, passes, or gets skipped, it prints a message to the console and to the html extentReport along with the screenshot.  
- **com.blazedemo.report** - This package contains ExtentReportManager class that creates a reports folder and creates a timestamped HTML file.It also contains ExtentReportFactory class that uses ThreadLocal class from Java, which makes each test run in a separate isolated browser in parallel without each other's interference.  
- **com.blazedemo.dataproviders** - The package contains TestDataProviders.java class, which uses TestNG's built-in @DataProvider feature that allows Data-Driven Testing.  
- **com.blazedemo.utils** - This package contains the utility classes that can be used throughout the framework.
  - ConfigReader.java - This utility class reads the value from the .properties file. The properties file contains the value related to environment configuration in the key value pair.
  - ScreenshotUtil.java - This Utility class captures a screenshot of the current browser window and saves it to a /screenshots/ folder within the project directory.
  - WaitUtil.java - This Utility class contains the explicit wait methods that wait for specific conditions like element to be visible, element to be clickable, etc.
- **com.blazedemo.tests** - This package contains FlightBookingTest.java class, which contains the actual test workflows along with the assertions.  

### **Test:**

<img width="1032" height="1203" alt="image" src="https://github.com/user-attachments/assets/50f0f2e7-66ad-4238-9dbd-6c64813bb460" />

### **Test Suite:**
The testng.xml file is the entry point for the execution of the test suite. It contains configuration for parallel execution and pass parameters for browser, and registers listeners for HTML reporting.  

<img width="958" height="503" alt="4VaLpRVOQr" src="https://github.com/user-attachments/assets/2b0064cb-d497-4d32-a4bf-ebc60e07f76c" />

### Reporting:
ExtentReports generated in html format.
]<img width="1069" height="1032" alt="image" src="https://github.com/user-attachments/assets/3f664444-c7d5-4d91-8821-c3cbca577532" />  
<img width="1068" height="1031" alt="chrome_lvH4oArEKz" src="https://github.com/user-attachments/assets/6272e21e-bdcf-4973-b90d-95cf2f7138bf" />  

### **Run Test via CLI:**
**Command used:** 
```
  mvn clean test
```
<img width="958" height="1030" alt="WindowsTerminal_dMy2EWRzO8" src="https://github.com/user-attachments/assets/56a58bca-bd6c-468c-a082-866e536bfbe6" />  

<img width="958" height="1030" alt="WindowsTerminal_Qjj7iLUf5q" src="https://github.com/user-attachments/assets/35166f33-75fd-4fa3-8639-97dc02612bab" />

### **Run script in jenkins**
<img width="1078" height="1393" alt="chrome_7XXhiNFjhE" src="https://github.com/user-attachments/assets/642d32f9-fa64-43b3-b09b-d793bc363102" />  
<img width="1080" height="1389" alt="chrome_Jib8teAzZI" src="https://github.com/user-attachments/assets/1a3ccb8b-7415-4f11-ae44-3288aa8b9b80" />  
<img width="1080" height="1389" alt="chrome_fMOfXFXEDP" src="https://github.com/user-attachments/assets/86e30569-aad0-40fd-8e64-479fb387e02d" />  


### **How to Run the Project:**  
1. Clone the git repository.
```
git clone https://github.com/amulsinfal/capstone-project-blazedemo-flight-booking-automation.git
```
2. Go to capstone-project-blazedemo-flight-booking-automation
```
cd C:\Users\Amul\Downloads\capstone-project-blazedemo-flight-booking-automation
```
3. Run command mvn clean test
```
mvn clean test
```
  
<img width="1233" height="1022" alt="image" src="https://github.com/user-attachments/assets/ce28ad09-2886-4c56-ac6e-0122d2683c99" />  
<img width="1233" height="1022" alt="image" src="https://github.com/user-attachments/assets/ed3513fb-a455-4cdd-a175-d797abb7cc9e" />











