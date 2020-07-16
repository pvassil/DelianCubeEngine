# Incorporation of Apache Spark functionality in an On-Line Analytical Processing system


## Description
Στόχος της διπλωματικής εργασίας είναι να ενσωματώσουμε τη λειτουργικότητα του [Apache Spark](https://spark.apache.org/) στο σύστημα DelianCube Engine, ένα σύστημα [On-Line Analytical Processing](https://en.wikipedia.org/wiki/Online_analytical_processing). Επιπλέον έγιναν μερικές διορθώσεις στον κώδικα του DelianCube Engine, μετατράπηκε το project σε [Maven Project](https://maven.apache.org/) και έγινε ανασχεδιασμός του γραφικού περιβάλλοντος με την βιβλιοθήκη [JFoenix](http://www.jfoenix.com/). Τέλος εγκαταστήσαμε και εκτελέσαμε το project στο cluster της σχολής.

## Table of Contents

* [Benchamrk](#Benchamrk)
  * [Libraries](#Libraries)
  * [Run](#Run)
  * [Results](#Results)
* [Running the tests](#Running-the-tests)
* [Visualization](#Visualization)
  * [Example of Visualization](#Example-of-Visualization)
* [Output Files](#Output-Files)
* [Build With](#Build-With)
* [Versioning](#Versioning)
* [Authors](#Authors)
* [Copyright](#Copyright)

## Installation
1. Εγκατάσταση της Java και MySQL
2. Εκτέλεση της εντολής `mvn package -DskipTests` για να κατεβάσει τα dependencies, δίχως να εκτελεστούν τα Junit tests.
3. Εγκατάσταση του κύβου [(Δεδομένα για παράδειγμα)](https://github.com/pvassil/DelianCubeEngine/tree/nano/InputFiles/10K-products) :
   * Σε περίπτωση που θέλουμε να χρησιμοποιήσουμε το σύστημα με RDBMS
     * Φορτώνουμε το σχήμα της βάσης δεδομένων.
     * Φορτώνουμε τα δεδομένα στη βάση δεδομένων.
     * Δημιουργία αρχείου με όνομα `[dbc.ini]`, το οποίο περιέχει τις πληροφορίες σύνδεσης του JDBC.
   * Σε περίπτωση που θέλουμε να χρησιμοποιήσουμε το σύστημα με Spark
     * Δημιουργούμε έναν φάκελο με το όνομα του κύβου `[cube_name]`.
     * Για κάθε πίνακα του κύβου δημιουργούμε το αρχείο `[csv_file_name]_attrs.csv`, το οποίο περιέχει τα πεδία όπως θα τα ορίζαμε σε μία σχεσιακή βάση δεδομένων.
     * Αποθηκεύουμε τα δεδομένα στον φάκελο `Data`.
   * Γράφουμε το initialization αρχείο του κύβου που θέλουμε να χρησιμοποιήσουμε `[cube_name].ini`.

## Usage
Για την εκτέλεση του DelianCube Engine με **GUI**.
1. Γράφουμε στο αρχείο `[project_path.ini]` το path του φακέλου που είναι το project.
2. Εκκινούμε τον [Server](https://github.com/pvassil/DelianCubeEngine/blob/nano/src/main/java/mainengine/Server.java).
3. Εκτελούμε το αρχείο [MainApp](https://github.com/pvassil/DelianCubeEngine/blob/nano/src/main/java/client/gui/application/MainApp.java).
4. Από το γραφικό περιβάλλον, επιλέγουμε τον τρόπο σύνδεσης (RDBMS ή Spark).
5. Συμπληρώνουμε τα απαιτούμενα πεδία που μας ζητούνται στο γραφικό περιβάλλον.
6. Κάνουμε σύνδεση.

Για την εκτέλεση του DelianCube Engine σε **τερματικό**.
1. Γράφουμε στο αρχείο `[project_path.ini]` το path του φακέλου που είναι το project.
2. Εκκινούμε τον [Server](https://github.com/pvassil/DelianCubeEngine/blob/nano/src/main/java/mainengine/Server.java).
3. Επεξεργασία δεδομένων, εισόδου και λειτουργίας, στο αρχείο [naive.ini](https://github.com/pvassil/DelianCubeEngine/blob/nano/naive.ini)
4. Εκτέλεση του αρχείου [NaiveJavaClient](https://github.com/pvassil/DelianCubeEngine/blob/nano/src/main/java/client/naiveJavaClient/NaiveJavaClient.java)

## Measures
### Configurations
Για τις μετρήσεις μας
1. Eκτελέσαμε το [query](https://github.com/pvassil/DelianCubeEngine/blob/nano/InputFiles/10K-products/Sales_Queries.txt) στο cluster της σχολής.
2. Μετρήθηκαν οι χρόνοι για αριθμό εγγραφών 1.000, 10.000, 100.000, 1.000.000 και 10.000.000
3α. Για το σύστημα RDBMS χρησιμοποιήθηκε η MySQL, η οποία έτρεχε σε έναν υπολογιστή του cluster.
3β. Για το σύστημα Spark έγιναν μετρήσεις, με τη χρήση από 1 εώς 10 κόμβους-σκλάβους.
4. Τα χαρακτηριστικά του cluster είναι :
* Κάθε κόμβος του cluster αποτελείται από ένα Sun  Fire X4100 Server με 4 CPUs (Dual Core AMD Opteron στα 2193 MHz κάθε CPU).
* Κάθε κόμβος έχει 12GB Ram. Για την εκτέλεσή μας, χρησιμοποιήθηκαν 3GB RAM σε κάθε κόμβο.
5. Έγιναν 10 επαναλήψεις για κάθε συνδυασμό των παραπάνω.

Οι μετρήσεις έγιναν με τη χρήση της συνάρτησης `Instant` που προσφέρει η Java.

### Results

Οι εικόνες παρουσιάζουν τα αποτελέσματα των μετρήσεων. Στην μπάρα `RDBMS`, αναφερόμαστε στους χρόνους του RDBMS συστήματος. Στις μπάρες `x Node` αναφρόμαστε στο σύστημα Spark, όπου x ο αριθμός των κόμβων-σκλάβων.

#### Αποτελέσματα για 1.000 εγγραφές
![1K Results](https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%201K%20Lines.png)

#### Αποτελέσματα για 10.000 εγγραφές
![1K Results](https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%2010K%20Lines.png)

#### Αποτελέσματα για 100.000 εγγραφές
![1K Results](https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%20100K%20Lines.png)

#### Αποτελέσματα για 1.000.000 εγγραφές
![1K Results](https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%201M%20Lines.png)

#### Αποτελέσματα για 10.000.000 εγγραφές
![1K Results](https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%2010M%20Lines.png)

## License
See the [copyright](copyright.md) file.


## ToDo
Community 
- [ ] ToDo: Must complement this file with a section on how to contribute. Sorry!

Refactorings & Corrections
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



