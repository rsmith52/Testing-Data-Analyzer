# Data-Analyzer
## Description
IT Case Data Analyzer is a program built for DoIT to assign categories to IT cases for the service desk and departmental support branches. The ticketing software they use called WiscIT can export a list of cases worked on, but often times these cases are not properly assigned categories. Our program is a neural network that we trained to assign categories based on a string description, and then allow the user to export the categorized dataset as a .csv file, and print out a PDF with some metrics on the data. The program reads in .csv files in the format WiscIT outputs.

## How to Run
Use Branch: FInalTesting

Our code is in the form of a maven java project, and can be imported to most java IDE's such as Eclipse and then run from Main.java. 

Alternatively, we have compiled a .jar that can be run as a standalone on any device running java: Data Analyzer, which can be found in the target folder. However this is currently not finding dependencies correctly, so we recommend
running from an IDE like eclipse where you won't experience crashes.

In the folder called "Test Datasets" there are 3 .csv data files you can use that contain cases to be run through the program. When selecting a .csv file, select one of these.
All output files are output to the main project folder as of now.

##Known Issues
* UI has some sizing issues on some displays, where text may appear very small
* When opening a .csv file, you need to name it first in the text box, and then hit enter before clicking the open button