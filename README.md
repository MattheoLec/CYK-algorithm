# CYK-algorithm
CYK algorithm implementation for the Efficient Algorithms course.

Instruction to start the program :
- Move to the src directory: `cd src`
- Compile the program: `javac Main.java`
- Execute the program: `java Main`

### Main program

The main.java give 2 enumeration examples for the first grammar.\
Each enumeration start with a string of 40 characters, and adds 40 more with each call to nextElement().\
The objective is to evaluate the evolution of the execution time, in order to determine the complexity of 3 
implementations of the CYK algorithm.\
The algorithm return true if the given word belongs to the grammar, false otherwise.\
A counter is also displayed, which indicates the number of iterations performed by each algorithm.