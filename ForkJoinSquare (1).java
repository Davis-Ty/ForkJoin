package com.example.forkjoin;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

//class can not be named (recursive action ) program gets confused
//ForkJoinSquare class
public class ForkJoinSquare {
	//main class
	public static void main(String[] args) {
		//setting are vars
		ForkJoinPool pool = new ForkJoinPool();
		Scanner userInput = new Scanner(System.in);
		Random rand= new Random();
		boolean validInput = false;
		int arraySize=0;
		
		//while input is false the loop keeps going
		while (!validInput) {
			//prompts user to enter array size
			System.out.print("Enter the amount of numbers you want to sum up: ");
			String inPut = userInput.nextLine();
			try {
				//Ends the while loop if the user enters a real whole number
				arraySize = Integer.parseInt(inPut);
				validInput = true;
				
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Enter a real whole number.");	
			} 
		}
		//setting array to the given size and filling it with random real numbers up to 99 from 0
		int [] data= new int[arraySize];
		for (int i =0; i<arraySize;i++) {
			data[i]=rand.nextInt(100);
		}
		//print out all the elements in the array (data)
		for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
		//print out blank line to help user read output
		System.out.println();
		
		//making an instance of the square function
		Square app = new Square(data, 0, data.length);
		
		//starting the timer to see how long the process takes when we change the size of (LIMIT) var in the Square class
		long startTime = System.currentTimeMillis();
		//build in method that starts the ForkJoinPool
		pool.invoke(app);
		//prints out the result from the Square class for our app instance
		System.out.println(app.result);
		//getting the time the process ended
		long endTime = System.currentTimeMillis();
		//getting the total time it took
		long totalTime = endTime - startTime;
		//Printing out the amount of time the process took
		System.out.println("Execution time: " + totalTime + " milliseconds");
	}
}



//Square class that extends RecursiveAction(allows the ForkJoin)
class Square extends RecursiveAction {
	//setting the limit to how large the array needs to be before we add it up
	final int LIMIT = 5;
	
	//static var allows others classes to share the var
	static int result;
	
	int start, end;
	int[] data;
	//creating a function that takes in 3 parameters 
	Square(int[] data, int start, int end) {
		//setting the var to the var that were passed.
		this.start = start;
		this.end = end;
		this.data = data;
	}

	
	
	@Override
	//Start of the work from the recursive action 
	//compute function
	protected void compute() {
		//seeing if the passed part of the array is smaller than our limit 
		if ((end - start) < LIMIT) {
			for (int i = start; i < end; i++) {
				//adding all elements in this part of the array and adding it to (result)
				result += data[i];
			}
		//if array is larger than our limit (It breaks the array down)
		} else {
			//finding the middle of the array
			int mid = (start + end) / 2;
			//splits the array into two setting it to two instances of square 
			//gives the left 0 to the mid point
			Square left = new Square(data, start, mid);
			//gives the right midpoint to the end of the array
			Square right = new Square(data, mid, end);
			
			//starting the recursive action (compute) function for left
			left.fork();
			//starting the recursive action (compute) function for right
			right.fork();
			
			//waits for the square instances to run the function a be completed
			left.join();
			right.join();
		}
	}
}