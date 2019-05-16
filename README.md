## Coding Test
This project shows how to use selenium framework to test given example project.
The algorithm used in the project only works for numbers.

---
## Installation
To develop the testing code
- Selenium WebDriver
- Maven
- Cucumber
- TestNG
- ExtentReports 
- Docker 
- Java 8 JDK 

To run the tests locally with 
- `Chrome`: install ChromeDriver from [here](http://chromedriver.chromium.org)
- `Firefox`: install GeckoDriver from [here](https://github.com/mozilla/geckodriver/releases)


## Files

- Cucumber feature file is under `/resources/features` folder
- Test results report is under`/reports` folder
- `Config.properties` are defined under `src/test/resources/properties` folder.
  - Set grid type to `true` if you want to use grid
  - Set grid path to your docker ip address `grid.path=http://192.168.0.5:4444/wd/hub`
  - Set OS name (mac or windows) `os.name=mac`

## Running tests ##
1.If grid property in `Config.properties` is set to `true` run following commands to make sure that tests will run on docker

```console
./src/test/resources/grid/machub.sh
./src/test/resources/grid/macnode1.sh
./src/test/resources/grid/macnode2.sh
```
2. Run maven project

```console
$ mvn test
```

## Notes ##
The messages given by system when the user enters correct values work wrongly.

System gives successfully and unsuccessfully messages together. First message is 
 `It looks like your answer wasn't quite right` and the following message is `Congratulations you have succeeded. Please submit your challenge`
 I ignore this situation
    