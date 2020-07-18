# Incorporation of Apache Spark functionality in an On-Line Analytical Processing system


## Description
Στόχος της διπλωματικής εργασίας είναι να ενσωματώσουμε τη λειτουργικότητα του [Apache Spark](https://spark.apache.org/) στο σύστημα DelianCube Engine, ένα σύστημα [On-Line Analytical Processing](https://en.wikipedia.org/wiki/Online_analytical_processing). Επιπλέον έγιναν μερικές διορθώσεις στον κώδικα του DelianCube Engine, μετατράπηκε το project σε [Maven Project](https://maven.apache.org/) και έγινε ανασχεδιασμός του γραφικού περιβάλλοντος με την βιβλιοθήκη [JFoenix](http://www.jfoenix.com/). Τέλος εγκαταστήσαμε και εκτελέσαμε το project στο cluster της σχολής.

## Table of Contents

* [Installation](#Installation)
* [Usage](#Usage)
* [Measures](#Measures)
  * [Configurations](#Configurations)
  * [Results](#Results)
  * [Conclusions](#Conclusions)
* [Output Files](#Output-Files)
* [GUI](#GUI)
* [Extra](#Extra)
  * [Example of cube](#Example-of-cube)
  * [Videos](#Videos)
* [Versioning](#Versioning)
  * [v.0.3](#v03-2020-july)
* [Authors](#Authors)
* [License](#License)

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
-------
Για τις μετρήσεις μας
1. Eκτελέσαμε το [query](https://github.com/pvassil/DelianCubeEngine/blob/nano/InputFiles/10K-products/Sales_Queries.txt) στο cluster της σχολής.
2. Μετρήθηκαν οι χρόνοι για αριθμό εγγραφών 1.000, 10.000, 100.000, 1.000.000 και 10.000.000.
3. Hardware :
 * Για το σύστημα RDBMS χρησιμοποιήθηκε η MySQL, η οποία έτρεχε σε έναν υπολογιστή του cluster.<br>
 * Για το σύστημα Spark έγιναν μετρήσεις, με τη χρήση από 1 εώς 10 κόμβους-σκλάβους.<br>
4. Τα χαρακτηριστικά του cluster είναι :
* Κάθε κόμβος του cluster αποτελείται από ένα Sun  Fire X4100 Server με 4 CPUs (Dual Core AMD Opteron στα 2193 MHz κάθε CPU).
* Κάθε κόμβος έχει 12GB Ram. Για την εκτέλεσή μας, χρησιμοποιήθηκαν 3GB RAM σε κάθε κόμβο.
5. Έγιναν 10 επαναλήψεις για κάθε συνδυασμό των παραπάνω.

Οι μετρήσεις έγιναν με τη χρήση της συνάρτησης `Instant` που προσφέρει η Java.

### Results
-----
Οι εικόνες παρουσιάζουν τα αποτελέσματα των μετρήσεων. Στην μπάρα `RDBMS`, αναφερόμαστε στους χρόνους του RDBMS συστήματος. Στις μπάρες `x Node` αναφρόμαστε στο σύστημα Spark, όπου x ο αριθμός των κόμβων-σκλάβων.

<p align="center">
<img src="https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%201K%20Lines.png" width="700">
</p>
<br>

<p align="center">
<img src="https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%2010K%20Lines.png" width="700">
</p>
<br>
<p align="center">
<img src="https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%20100K%20Lines.png" width="700">
</p>
<br>
<p align="center">
<img src="https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%201M%20Lines.png" width="700">
</p>
<br>
<p align="center">
<img src="https://github.com/pvassil/DelianCubeEngine/blob/nano/OutputFiles/script_times/Average%20time%20of%20query%20execution%20for%2010M%20Lines.png" width="700">
</p>
<br>

### Conclusions
-----
Όπως παρατηρούμε από τις γραφικές παραστάσεις, το σύστημα Spark είναι πιο χρονοβόρο όταν τα δεδομένα είναι λίγα. Οπότε, στην περίπτωση που τα δεδομένα είναι λίγα συνίσταται η χρήση του συστήματος RDBMS. Στην περίπτωση όπου έχουμε να κάνουμε με μεγάλα δεδομένα η χρήση του Spark είναι κατά μέσο όρο x10 πιο γρήγορη από αυτή ενός συστήματος RDBMS.

## Output Files

Μετά την εκτέλεση του προγράμματος, τα αποτελέσματα εξάγονται στον φάκελο `OutputFiles`. Τα ονόματα των αρχείων περιγράφουν τα δεδομένα που περιέχουν, π.χ. εάν εκτελέσαμε τον αλγόριθμο Kmeans το όνομα του αρχείου θα είναι `[name_of_our_query]_KMeansApache.tab` για τα αποτελέσματα του αλγορίθμου καθώς και ένα αρχείο `[name_of_our_query]_KMeansApache_info.txt` για μια σύντομη περιγραφή για το τί εκτελέστηκε.

Τα αποτελέσματα μπορούν επίσης να προβληθούν και μέσα στο γραφικό περιβάλλον του DelianCube Engine. Παράδειγμα εμφάνισης αποτελεσμάτων βλέπουμε στην Εικόνα 1.

<p align="center">
<img src="https://github.com/pvassil/DelianCubeEngine/blob/nano/GUI-images/GUI%20-%20Results.png" width="500">
<br>Εικόνα 1
</p>

## GUI

Η Εικόνα 2 δείχνει το γραφικό περιβάλλον του παραθύρου σύνδεσης.
<br>
<p align="center">
<img src="https://github.com/pvassil/DelianCubeEngine/blob/nano/GUI-images/Delian%20Cubes%20Client%20Application%20-%20JDBC%20-%20Login.png" width="500">
<br>Εικόνα 2
</p>

<br><br>
Η Εικόνα 3 δείχνει το γραφικό περιβάλλον της κεντρικής εφαρμογής, μετά τη σύνδεση.
<br>
<p align="center">
<img src="https://github.com/pvassil/DelianCubeEngine/blob/nano/GUI-images/Delian%20Cubes%20Client%20Application%20-%20JDBC%20-%20Main%20App.png" width="500">
<br>Εικόνα 3
</p>


## Extra

### Example of cube

Στο αρχείο `[cube_name].ini` ορίζουμε τον κάθε dimension table ως εξής :

    CREATE DIMENSION [dimension_table_name]_dim
    RELATED SQL_TABLE [dimension_table_name]
    LIST OF LEVEL {
     [dimension_table_name].[id_variable_name] AS lvl0,
     [dimension_table_name].[variable_name_of_1st_level] AS lvl1,
     [dimension_table_name].[variable_name_of_2nd_level] AS lvl2,
     ...
     ...
     [dimension_table_name].[variable_name_of_last_level] AS lvl[N]
    }
    HIERARCHY lvl0>lvl1>lvl2>...>...>lvl[N];

και στο τέλος τον fact table ως εξής :

    CREATE CUBE [cube_table_name]_cube
    RELATED SQL_TABLE [cube_table_name]
    MEASURES [measure_name] AS [cube_table_name].[variable_name_to_measure]
    REFERENCES DIMENSION 
     [1st_dimension_table_name]_dim AT [cube_table_name].[foreign_key_to_dimension_table],
     [2nd_dimension_table_name]_dim AT [cube_table_name].[foreign_key_to_dimension_table],
     ...
     ...
     [Nth_dimension_table_name]_dim AT 	[cube_table_name].[foreign_key_to_dimension_table]
     
*Στη θέση του **RELATED SQL_TABLE** το όρισμα παραμένει ίδιο για RDBMS και Spark.

Για συμπληρωμένο παράδειγμα μπορείτε να δείτε [εδώ](https://github.com/pvassil/DelianCubeEngine/blob/nano/InputFiles/10K-products/sales.ini).


### Videos

## Versioning

### v.0.3 [2020 July]
Ενσωματώθηκε η λειτουργικότητα του Apache Spark, έγινε ένα 'μικρό' μέρος refactoring και μετατράπηκε το Java Project σε Maven Project, από τον Καδόγλου Κωνσταντίνο.

## Authors

:pencil2:  <b>Konstantinos Kadoglou</b>

## License
See the [copyright](copyright.md) file.
