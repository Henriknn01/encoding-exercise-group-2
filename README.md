# Encoding exercise

Exercise on encoding, used in the course [IDATA2304 Computer networks](https://www.ntnu.edu/studies/courses/IDATA2304),
[NTNU](https://www.ntnu.edu/), Campus Ålesund.

## Intention

The intention for this exercise is to experience different encodings of data. As you have heard in the course - we
typically send only ones and zeroes over wires (or wireless signals). This implies that to send any kind of data, we
must convert that data to ones and zeroes. In this exercise you will encode a string-message (human-readable) into a bit
stream and vice versa.

Note: here we will use a string of bits, for example "10101100" as a string, just for simplicity of readability. In real
life you would use much more effective data structures.

## Instructions

The task is to implement functions `encode(message)` and `decode(bits)` in the `Encoder` class. The encoding must be
done according to the protocol described in the `Encoder` class.

## Getting started

Clone this Git repository, open this folder inside your IDE (IntelliJ, Netbeans or other). This is a Maven project which
you have seen previously in other course(s).

## Testing and grading

The project comes with unit tests. To check whether you have implemented the functions correctly, you can run the tests
locally on your computer. Simply execute `mvn test` in the terminal (in the project folder), or run the tests from your
IDE. If you use IntelliJ, you can run the tests by right-clicking the `src/test/java` folder and
choosing `Run 'All tests'`.

This exercise is also part of a GitHub classroom assignment and contains auto-grading instructions. This means that
whenever you make a commit to the Git repository (`master` branch) and push it to GitHub, automated grading robot will
check your solution and both you and teachers will see a report - how many of the unit tests passed. To avoid
overspending og free GitHub credits, please, test your solution locally before pushing it to GitHub a hundred times ;).