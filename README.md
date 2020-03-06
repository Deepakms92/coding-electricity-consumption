# coding-electricity-consumption
This project is to get the consumption of electricity frm each of the villages .

### Technologies & frameworks:
Java 8, Spring Boot, H2, Junit, Mockito, Maven ,Docker

### Run the application

* git clone https://github.com/Deepakms92/coding-electricity-consumption.git
* cd coding-electricity-consumption
* mvn clean install
* The above gives you the target folder.
* Go to target folder and run the jar created by java -jar coding-electricity-consumption-0.0.1-SNAPSHOT.jar

### Things Done.
* The API is to create a new counter with the amount .
* The API is able to consume the data and get the village information from the given API
* The API is able to create a report .
* Swagger enpoint.
* DockerFile to deploy the application on cloud.
* Covered most of the test cases.
* If at all the village has to be created , exposed an end point for it. 
* Exposed one more endpoint to associate an electricity counter with village.

### Assumptions
* There is a one to one relationship between village and electricity counters.
* The last 24h will be considered from current time when the report is generated, which will consider the last updated time of the counter.
* All the electrical counter is operational all the time in every village.
* GET https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/1 is always up and working and doesn't 
need  any  authentication,
* Duration should always be in days,hours or minutes while generating reports.
* counterID is always positive integer .


### TODO
* Better exception handling when the API doesn't return the value.
* Exposing an ndpoint if at all the user wants to get the counter information that he creates from the application rather than hitting the API.
* Helm packaging .
* Better test cases.





