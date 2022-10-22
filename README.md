# Goal of project
The main goal of the project is to create an implementation of a sorting algorithm that will be faster than the sorting provided in the java.util package for the **specific input data** posed in the task. This input data is an array of movies taken from a file located in the main directory. Why was the bucket sort algorithm chosen? Because in this particular case, for this particular file, the values by which the movies will be sorted have a relatively low variance. Therefore, the sorting algorithm will work perfectly here.
The secondary purpose of the project is to retrieve rows from a particular file with the extension .csv, to filter the retrieved rows and create instances from them. 
## What is bucket sort?
It is sorting algorithm. In a nutshell, sorting involves supplying to a certain
container of values corresponding to the index of this container, then you just need to combine these containers according to the
index numbers. It is based on two phases.
*Phase 1: Based on the value of the input structure, create buckets. The index interval starts and ends, respectively, with the index corresponding to the smallest and largest value of the element of the
of the input structure. In the case of sorting by integers, the matter is simple, just
give the containers index numbers corresponding to the values from the structure. In other cases
such as floating point numbers or character strings, it is necessary to cast to integers or adopt a
a specific rule for assigning indexes. The elements from the input structure are then dropped into a
bucket with the corresponding index of their element of the value by which the sorting takes place.
*Phase 2: From the buckets starting from the smallest to the largest indexes are added at the end of the
of the output sequence the elements

