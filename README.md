# Forking
- This Java program demonstrates the use of the Fork-Join framework for parallel computing. It calculates the sum of a large array of integers by recursively splitting the array into smaller chunks and processing them concurrently.

# ForkJoinSquare class:
- Initializes a ForkJoinPool to manage parallel execution.
- Prompts the user to enter the size of the array.
- Generates an array of random integers.
- Creates an instance of the Square class and starts the computation.
- Measures and prints the execution time.
  
# Square class:
- Extends RecursiveAction to enable parallelism.
- Defines a LIMIT constant to determine when to compute the sum sequentially.
- Stores the result of the computation.
  
# Implements the compute() method to divide and conquer the array:
- If the array size is below the limit, it sequentially sums the elements.
- Otherwise, it splits the array into two halves and recursively computes their sums in parallel.
- This approach improves performance by leveraging multiple processor cores for concurrent processing, reducing the overall execution time compared to sequential computation. The program demonstrates how to efficiently utilize multicore processors to solve computationally intensive tasks.



