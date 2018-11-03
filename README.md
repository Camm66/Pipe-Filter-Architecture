# Pipe-Filter Architecture
This program demonstrates the use of a Pipe-Filter architectural pattern to implement a text processing platform. The goal of this platform is to determine the top 10 significant words that occur in the input text.

## Command-line compilation instructions
 Using the included Maven build tool, run from the root directory:
```
> mvn package
```
A guide for the operation of Maven can be found [here](https://maven.apache.org/guides/getting-started/).

## Instructions to run this program:
 Run the included jar file:
 ```
 > java -jar PipeFilter-0.0.1-SNAPSHOT.jar
 ```
 This implementation then prompts the user to enter the relative path of the text file:
 ```
 > Enter path of the text file:
 > text_files/kjbible.txt
 ```

## Architecture
### Components
_DataPump_
 - Reads the text file and injects it into pipeline.
 
_FilterRemoveNonAlpha_
 - Removes all non-alphabetic characters.

_FilterRemoveUpper_
 - Converts all words to lowercase.

_FilterRemoveStopWords_
 - Removes all stopwords (ie. non-significant words/terms).

_FilterRootForms_
 - Converts words down to their root forms.

_DataSink_
 - Counts filtered words and displays the top 10 occurrences.
