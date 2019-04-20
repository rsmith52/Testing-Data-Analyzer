# Data-Analyzer
## Description
IT Case Data Analyzer is a program built for DoIT to assign categories to IT cases for the service desk and departmental support branches. The ticketing software they use called WiscIT can export a list of cases worked on, but often times these cases are not properly assigned categories. Our program is a neural network that we trained to assign categories based on a string description, and then allow the user to export the categorized dataset as a .csv file, and print out a PDF with some metrics on the data. The program reads in .csv files in the format WiscIT outputs.

The neural network was trained on 750 cases that were manually categorized, and can now be used to assign categories to new sets of cases with relatively high accuracy (70-80%) on similar sets of cases. When a .csv file is selected, the cases inside are immediately run through the network, and then the categorized data set is saved as a .cat file. From this data, you can see output metrics as a .csv or a .pdf.

## How to Run
Use Branch: FInalTesting

Our code is in the form of a Maven java project, and can be imported to most java IDE's such as Eclipse as a Maven project and then run from Main.java. 

**\/ \/ \/ This is broken after recent changes, need to run from IDE for testing for now \/ \/ \/**

Alternatively, we have compiled a .jar that can be run as a standalone on any device running java: Data Analyzer, which can be found in the target folder. However this is currently not finding dependencies correctly, so we recommend
running from an IDE like eclipse where you won't experience crashes.

**/\ /\ /\ This is broken after recent changes, need to run from IDE for testing for now /\ /\ /\**

In the folder called "Test Datasets" there are 3 .csv data files you can use that contain cases to be run through the program. When selecting a .csv file, select one of these.
All output files are output to the main project folder, with pdfs writing to the pdfs folder, and csvs writing to the main project folder. The cats folder stores internal data
for datasets that have already been categorized by the neural network.

From the main menu, you can double click on the name of a categorized data set to access it's page, and then output metrics and data.

## Known Issues
* UI has some sizing issues on some displays, where text may appear very small
* Cannot close application from the dataset view screen, need to go back to main screen before you can close it
* When running program from standalone jar it crashes when dependencies are used - cannot start to run by opening a .csv