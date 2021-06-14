# DelianCubeEngine


## Description
This project implements a simple query engine that receives cube queries and returns the results in tsv files.


## Installation
1. Install Java and MySQL
2. Install cube base(s) (see the README files of the two exemplary cube bases in the InputFiles, which you can install too). This includes setting up:
  * the database in the DBMS 
   * the .ini file with the name of the cube base where the dimensions with their levels and hierarchies as well as cubes are described
   * the dbc.ini connection file with the jdbc connection info


## Usage
For v.0.1 - v.0.3, the usage is simple:
1. Start your DBMS (MySQL) service, if not running
2. Start the Server of the project
3. Alternatives
* Start the NaiveJavaClient of the project (query results are in the OutputFiles folder for the server and ClientCache for the client)
* Start the MainApp client in the gui package. Its functionalities include launching queries from a stored file and loading tsv files. 
* ... create a Client of your own (already having clients to work with interestingness and natural language queries)


## Credits and History

### v.0.3 [2021 January]
* S. Zogos added sessions and query history management
* D. Gkitsakis added the first v. of the natural language querying package (also: tests & clients) to allow the querying via natural language
* E. Mouselli added the first v. of the interestingness package (also: tests & clients) to assess query interestingness

### v.0.2 [2018 August]
P. Vassiliadis extended the engine with the generation of models for queries. Expansion of the GUI, the testing and refactorings also took place 

### v.0.1 [2018 June and July]
P. Vassiliadis extracted a relevant subset of v.0.0, and refactored/adapted it for the first v. of a simple query engine that simply answers cube queries
A set of functionalities like RMI file transfer and a small GUI were added too.

### v.0.0 [2018 January]
* Code for the Delian Cube Engine comes largely from  the Cinecubes project, implemented via the contributions of:
* D. Gkesoulis, who was the original main developer for Cinecubes
* A. Todoris who added the RMI part
* A. A. Xynogala-Karathanou who produced a refactored version of Cinecubes.

### Delian Problem
The Delian Problem is the problem of accurately computing the [doubling of a cube's volume](https://en.wikipedia.org/wiki/Doubling_the_cube) (which of course entails computing the 3rd root of 2).
Credits for solutions to the problem go to Menaechmus for a first solution, and also to Diocles, Nicomedes, Philo, Archytas  and Eratosthenes.


## License
See the [copyright](copyright.md) file.


## ToDo
Community 
- [ ] ToDo: Must complement this file with a section on how to contribute. Sorry!

Refactorings & Corrections
- [ ] Expand natural language querying with more elegant solutions
- [ ] Expand interestingness assessment with syntactic checks
- [ ] Address the todo's inside the src
- [ ] Introduce an intermediate abstraction level between cubebase and (relational) database, s.t., new DBMS types are pluggable
- [ ] Refactor the GUI client, to kill cycles in the package diagram
- [ ] Refactor the result package to add a hierarchy of packages for modules (abstract, general-purpose, KPIs, clusterings, ...)
- [ ] Refactor how clients communicate with server and server init. Connections should be parsed at server startup and sessions handled by an intermediate class between Server and SQP (probably a sessionManager or a QueryManager, caching sessions, queries and their results, etc) 
- [X] Redefine how client communicates with server: a list of files is produced, not just a single tab file with the data
- [X] Add a "Run Single Query" part at the GUI client that opens sth like a text editor to write a single query and calls the QueryFromString at the server
- [X] Add a GUI client
- [X] Cleanup the code from unused parts of v.0.0 (at least the known ones)
- [X] Fix the fixme comments

Extension to an Intentional OLAP engine
- [ ] Add support to more apache commons math parts that pertain to the computation of simple models
- [ ] (maybe) Link to weka / spark mllib / ... via principled interfacing to add more model extraction methods



