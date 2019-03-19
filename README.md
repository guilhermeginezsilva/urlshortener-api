# Url Shortener REST API (Basic Version)

**Challenge:** Design and implement a RESTful API for a URL shortener.

**Requirements:**
1. Develop a RESTful API to create shorten URLs. This API should receive an URL and will return a shortened version of it.

2. Redirect to the original URL when the shortened URL is called.

3. The API should persist the data, because if the application is stopped, it must recover all the data when it starts again.

#### Prerequisites

To run this project you need to have the following applications:

**important**: Below each requirement, I'm noting the version I've used during the development.
	
1. Java 8

	My version: java version "1.8.0_121"
	
	To check if you have installed just type in you console:
	java -version
	
2. Apache Maven

	My version: Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-17T15:33:14-03:00)
	
	To check if you have installed just type in you console:
	mvn --version

#### Clone project

    ###To clone it just type in your console the command below on the desired directory:
    git clone https://github.com/guilhermeginezsilva/urlshortener-api.git

#### Tests
This project contains 59 tests to test all the three layers: 

Controllers, Services and DAOs

    ###In the console on the root directory of the project, just type:
    mvn test
    
#### Running
There are 2 options to run this project:

1. Build and Run:

    ###Just navigate to the cloned directory run the command below in the console:
    mvn spring-boot:run
    
    ###With Optional Parameters:
    
    IDs Range Size: Id generator will create ranges of n IDs for this instance, the range size for the example below is 1000 IDs: (Explained later)
    mvn spring-boot:run -Dspring-boot.run.arguments=--id.range.size=1000
    
2. Run the executable:
        
    ###Just navigate to the releases directory inside the project and run the command below:
    ###On windows:
    start.bat
    
    ###On linux:
    ./start.sh
    
### It's Running!! And now?
Well, running the tests you can check that the application is working correctly, but we know that there isn't nothing better than having some fun with these projects:

* You can use Postman application; a script ("URL Shortener.postman_collection.json") with the request structures is available also in the releases directory, just import in your postman application and have fun;


    ###You can find postman installation instructions here:
    https://www.getpostman.com/
    
    ###After download, you may find a "import" button in the top of the screen. Just import the script and you will be available to make the requests.
    
### Project Design:
Here I explain the design decisions of this project:

**1. Architecture:**

This project follows MVC architecture.

**2. Routes:**

The available routes are (considering you are running in your localhost):

* Shorten a URL Route:

	Request format:
		POST: http://localhost:8080/v1/shu/shorten
	
		Content Body (JSON):
			{
				"url": "http://www.google.com.br"
			}
		
		Constraints:
			* The url parameter is mandatory and must not be empty.
			* The url parameter must contain a valid URL, containing the full structure, including the protocol, like: http, https, ftp...
		
	Response format for success:
		HTTP 200;
		Content Body (JSON):
		{
		    "originalUrl": "http://www.google.com.br",
		    "shortenedUrl": "http://localhost:8080/v1/shu/0000000"
		}
	
* Redirect to the original URL:

	Request format:
		GET: http://localhost:8080/v1/shu/0000000
		
	Constraints:
		* The request URL must be valid and have been returned from a shorten call, or it will return 404, not found.
		* The id part of the URL (After the last '/' of the URL) must have a length of 5 characters.
		* The id part of the URL (After the last '/' of the URL) must not be empty or null.
		
	Response format for success:
		HTTP 301: Get redirected to the original URL

**3. Chosen Technologies:**

**Java language:** I've chosen Java language for this project because it's the language that I'm more used to.

**Spring Boot:** It's a really nice framework to work with RESTful services:

* It's fast to start; (I know that there are other fast frameworks too, but it's also an advantage of Spring);
	
* It has many preconfigured integrations with other technologies, as parsers, database, exception handlers...;
	
* It will help in the next step of this project when integrating with the cloud;
	
**H2 Database:** H2 was chose for this project to keep it simple, so everyone would be able to run it, not needing to install a database. There are 2 different configurations of H2 is this project, for the tests it uses H2 in memory, because it doesn't need to persist the data, and the normal executing process persists the H2 data to the disk, to the same directory that is running the application.
	
**4. How it works:**

**Controllers:** Responsible to receives the routes requests, parse in and out the data to Java object and validate the input data. It uses the Service Layer services.

* ShortenedUrlsController: Expose the 2 routes described in this document.
	
**Services:** Responsible to apply the business rules over the request and to call the Data Acess Object Layer, to store and get persisted data.

* ShortenedUrlIdRangeService: Apply IDs ranges business rules, to manage the used IDs of the shortened URLs.
* UrlsService: Apply Shortened URLs business rules, to shorten an URL or to get the original URL of a shortened one.

**Data Access Objects:** Responsible to communicate with persisting technologies, like databases. In this project it communicates with persisting H2 database.

* ShortenedUrlIdRangeDAO: Database access object of the ID ranges control.

	Model fields:
		rangeId: Auto generated id, just for database;
		firstId: The first ID of the range;
		lastId: The last ID of the range;
		owner: The owner process of this range, it avoids that 2 processes are using the same IDs;
		currentId: The current position of this range;
		lastModified: Last modified date just for history;

* UrlsDAO: Database access object to keep track of the created shortened URLs and Original URLs.

	Model fields:
		shortenedUrlId: The ID of the shortened URL, returned in the shorten route;
		shortenedUrlIdSeed: The seed used to the shortened ID generation;
		originalUrl: The original URL of a shortened URL, used in the redirect to original URL route;
		lastModified: Last modified date just for history;
		
**ID Generation Process:**

1. **Format:**
The id generation process is a simple sequence of decimal values, starting at 0; every time that a ID is generated, it increases the counter by 1.

To transform this decimal ID in the Shortened URL ID the API calculates the base 62 of this number using the characters: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l','m', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'.

To generate a fixed 5 characters ID, after the base 62 transformation, it pad zeroes to the left.

**Design:**
It was designed to have 5 characters length because it would be able to support 916.132.832 shortened URLs generated, that is a big number of shortened URLs and can be handled by a int variable. If the application really get used by a lot of people and systems and this starts to be a problem, the conversion for a long variable is possible.

2. **ID Generation Process:**
The ID generation process was designed to work on a multi application instances environment working with a unique database interface. It works as below:

**Design:**

One or more instances need to generate IDs for the shortened URLs, but if each one generates an ID it would be possible to generate duplicate IDs. I could give each instance a fixed range of IDs to work, but it would be a problem for an auto scaling solution, because I would need to know all my instances ranges to deal with it. So the solution was an hybrid of it.

Instead of generating an ID, the application instances generates Ranges of IDs, this is done by inserting a new Range on the ShortenedUrlIdRange table. This insertion must be synchronized by all instances, so it avoids that two or more instances creates the same range in the database, so all the application instances needs to be connecting to the same database, or same database interface.

In this transaction the application process check the last range existing in the database, if there are none, it creates the first range, else it creates a range starting from the ((lastID from the Last Existing Range) +1 ). 

I decided to use ranges here also because I don't want a synchronized insert for every request the application receives, so the creation of a new Range is done in a transaction, but the update no. Taking into account that there is just one application instance working with each range. So as it receives a big range to work, it just need to update the range position in the range record. Off course you could also just update the record when the range has ended, it would improve even more the performance, but if the application stops in the middle of the range, it would lose the current position and would replace all the range records created when receiving new requests.

3. **Responsabilities:**

* **Application:** is responsible to make the database calls of creation of a new range in a transaction.

* **Database:** is responsible to keep all the IDs range records, if it needs to scale, it would need to be scaled vertically. If it would be scaled horizontally a replication of this table between the database instances would be necessary.


