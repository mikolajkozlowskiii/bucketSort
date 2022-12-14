# Goal of project
The main goal of the project is to create an implementation of a sorting algorithm that will be faster than the sorting provided in the java.util package for the **specific input data** posed in the task. This input data is an array of movies taken from a file located in the main directory. Why was the bucket sort algorithm chosen? Because in this particular case, for this particular file, the values by which the movies will be sorted have a relatively low variance. Therefore, the sorting algorithm will work perfectly here. In case the films had a larger variancy of values the time of bucket sorting would increase significantly. 
The secondary purpose of the project is to retrieve rows from a particular file with the extension .csv, to filter the retrieved rows and create instances from them. 
## What is bucket sort?
It is sorting algorithm. In a nutshell, sorting involves supplying to a certain
container of values corresponding to the index of this container, then you just need to combine these containers according to the
index numbers. It is based on two phases.
* Phase 1: Based on the value of the input structure, create buckets. The index interval starts and ends, respectively, with the index corresponding to the smallest and largest value of the element of the
of the input structure. In the case of sorting by integers, the matter is simple, just
give the containers index numbers corresponding to the values from the structure. In other cases
such as floating point numbers or character strings, it is necessary to cast to integers or adopt a
a specific rule for assigning indexes. The elements from the input structure are then dropped into a
bucket with the corresponding index of their element of the value by which the sorting takes place.
* Phase 2: From the buckets starting from the smallest to the largest indexes are added at the end of the
of the output sequence the elements

***Computational complexity analysis***: Phase one takes
O(n) - n because the max and min values in the while petla of method one must be determined, and again n because
all elements must be assigned to buckets. Phase two takes O(n+N) - N because you have to go through
all buckets in the outer loop and n because you have to go through the entire list in the inner loop, and it will
will execute as many times as there are elements. This gives us a computational complexity of O(n+N).

## My implementation
My implementation was created solely for sorting Film type objects and not to create an API. 
BucketSort class contains 2 methods for sorting, which should be used depending on the situation - input data.
***sortWithLoosing()*** this method is better when there is absolute certainty that all instances in the array have values (those by which sorting is performed) that are integers. Otherwise, an exception will be reported.
***sortWithoutLoosing()*** this method is better in the opposite case to the one mentioned above, due to the fact that it does not lose information about the values by which it sorts, unlike the first method.


### sortWithLoosing() ###
This method sorts in a bucket style array of <code>Film</code> instances faster.
If you are not sure is whole array has Films instances with ratings in integers
casts to double, better not to use this method. In that case this method via the
<code>getMinMaxWithLoosing</code> method throws exception, because this sort may not work
correctly. Sort algorithm is comparing in Film object <code>rating</code> field, which
can be cast to integer with loosing information after dot, for example 7.0 would
be converted to 7. All movies in specified "projekt.csv" file have ratings in double
value, but they are really integers numbers converted to double, so the problem of
bucket sorting (assigning the appropriate bucket) is made easy. This method uses
<code>getMinMax</code> method which provides array of integers with information about buckets.
### sortWithoutLoosing() ###
This method sorts in a bucket style array of <code>Film</code> class instances safer.
It is alternative version of previous <code>bucketSort</code> method. If you are sure
is whole array has Films with ratings in integers casts to double, better not to use
this method. This method is much slower than second method in bigger capacities.This
algorithm is comparing in Film instance <code>rating</code> field, which can not
be cast to integer without loosing important information after dot, for example
5.2 would be converted to 5. This method uses <code>getMinMax2</code> method which
provides HashMap with whole information about all buckets.
## Usage  ##
A comprehensive example of code usage is shown in the Driver class, which also shows the difference in sorting time between the created algorithms and the sorting from the java.util package. To sort by an array of movies, you first need to choose which sorting method you want to use (for a detailed description of these, see the source file or the sortWithLoosing() and sortWithouLoosing() sections). Then just call the static method from the BucketSort class as follows: <code>BucketSort.sortWithLoosing(filmsArray)</code>, <code>BucketSort.sortWithoutLoosing(filmsArray)</code>
## Room for improvment ###
In a new branch, I'm looking for a way to use the class from bucket sorting in a more general way. One idea that has successfully passed the tests is to change the argument in the sort methods of this class, from an array argument of type film to an array argument of type DoubleSupplier. Then any array of objects implementing the DoubleSupplier interface can be sent to these methods, not just arrays of type Film. But as mentioned earlier, the purpose of the task was not to create an API, but to create a sorting algorithm for a specific object.
