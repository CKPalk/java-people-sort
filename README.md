# People sort impementation

I decided to implement the sort using the Java8 stream API. Since the application would be running on a web server
I assume it can be configured with sufficient cores needed for parallelism. With multiple cores a large number of
people can be sorted concurrently. That being said, I still needed to take into consideration the overhead caused by
using streams.

While stream sorting was my final option I considered using `Arrays.parallelSort` which also sorts in parallel. Depending
on the number of people being sorted this option can be faster as there is much less overhead compared to streams. 

Because both strategies were viable I loosely coupled the Comparator with the sort strategy used. This makes it easy to
modify my implementation to use `Arrays.parallelSort` or any other sorting strategy that use Comparators.

Finally, I enjoy using the functional programming patterns in Java 8.
