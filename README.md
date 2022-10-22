# Goal of project
The main goal of the project is to create an implementation of a sorting algorithm that will be faster than the sorting provided in the java.util package for the specific input data posed in the task. This input data is an array of movies taken from a file located in the main directory. Why was the bucket sort algorithm chosen? Because in this particular case, for this particular file, the values by which the movies will be sorted have a relatively low variance. Therefore, the sorting algorithm will work perfectly here.
The secondary purpose of the project is to retrieve rows from a particular file with the extension .csv, to filter the retrieved rows and create instances from them. 
## What is bucket sort?
It is sorting algorithm. In a nutshell, sorting involves supplying to a certain
container of values corresponding to the index of this container, then you just need to combine these containers according to the
index numbers. It is based on two phases.

