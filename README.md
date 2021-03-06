# xmlproject
Assume your library will be used in a production environment.  
It should be structured and written to be maintainable, flexible and extensible.  
Anything you wouldn’t normally do in such a situation please make note of in a comment 
(it’s fine, this is just an exercise and we won’t really be using it in a production environment). 
All code must be Java 8 compatible.  Please use any Java 8 features where appropriate (Lambdas, Generics, concurrency, etc.). 
But, don’t use them just to use them. 
Use any 3rd party open source library you would like. 
Provide a main() method that demonstrates the usage of your library. 
Provide a build script that produces a runnable JAR file (e.g. java -jar transform.jar). 

# installation and run
create a subdirectory called xmlRadioLibrary
clone the repository
put the input.xml in appropriate directory
download or put the jackson library into the project dependencies
build the code
run the FileConversion.java main()
check the output parsed element.
# output format:
All fields are written in json format and the output file datetime stamp is stored in "LastUpdated" field generated as ISO 8601 timeformat.
      
# output sample:
{
  "locations" : [ {
    "_id" : "23317403",
    "description" : "In Manhattan, major road construction on Worth St between W Broadway and Church St.",
    "geo" : {
      "type" : "Point",
      "_id" : null,
      "description" : null,
      "coordinate" : {
        "x" : "-74.00625",
        "y" : "40.717075"
      },
      "addr" : "Worth St"
    },
    "tmc" : null,
    "eventCode" : "702",
    "severity" : "3",
    "validStart" : "2019-02-10T05:00",
    "validEnd" : "2019-04-13T03:58:59",
    "type" : null,
    "lastUpdated" : null
  }, {
    "_id" : "24049284",
    "description" : "In Bronx, road construction on Creston Ave between Minerva Pl and E 198th St.",
    "geo" : null,
    "tmc" : {
      "type" : "TrafficIncident",
      "_id" : "16078",
      "description" : null,
      "startId" : "16078"
    },
    "eventCode" : "701",
    "severity" : "2",
    "validStart" : "2019-03-02T05:00",
    "validEnd" : "2019-04-02T03:58:59",
    "type" : null,
    "lastUpdated" : null
  } ]
}
