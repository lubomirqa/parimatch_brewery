# Open Brewery DB Automation Project
This is RestAssured project for Open Brewery DB. Currently only "Search Breweries" method is covered with automation tests but more will come.

**Prerequirements**
- Java 11
- IDE

**Tools**
- Java 11
- Rest Assured
- Maven
- TestNG

**How to use**  
Run tests manually from the `SearchBreweries` class or execute the following command in the console:   
`mvn test`

# Search breweries

**Testing scenarios**    
In the current project 5 main scenarios are used. Although, there are a few of them which also should be considered to add:
- use * as a query param
- use digit as a query param (to validate matching by a digit)
- use .com as a query param (to validate matching by a website_url
- don't use query param (to expect handled behavior)
- use another query param (to expect handled behavior)

# List Breweries
**Test approaches**  
For the proper testing of list breweries method I would have tested it with the each possible parameter. Moreover, each parameter needs individual approach because of the data distinction. For example, *by_city* value contains String entity and should be treated like a String (e.g. filter accuracy check - add excess letter or remove one, remove space (underscore) between words - sandiego, mixed letters etc.). On the other hand, *by_dist* contains Decimal value and actually has 2 initial values, thus should be tested by a fullness (latitude and longitude both are present), add excess value (2 coma separators), accuracy (filtered values are correct according to the param value), check that "." is considered during the filtering. 
Other than that, every filter should be checked by a null parameter value, digits with a negative values.

**Test design**   
Overall, being acquainted with this method, I would suggest using the following test design techniques:
- Boundary Value Analysis (for filters using digits, e.g. by_dist, per_page, by_postal)
- Equivalent Class Partitioning (for filters using some kind of a limitation (per_page, by_postal)

**Estimation**   
Given one endpoint with 9 available params I would consider 1.5 days to be a good result for finishing this, where:
- 1 hour for each param including test design and automation = 9 hours
- 1 hour to clarify requirements if needed
- 1 hour to implement the basic code to test endpoint
- some time for PR managing 

