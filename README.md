# school-schedule-generator

***Project for AI course.***

Implemented a program that creates an initial random high-school schedule based on some courses and teachers given from text files. The initial schedule defies some(or all) the constraints that were given. The program creates a state space in the form of a tree (with children etc.) by changing the position of some courses or teachers randomly.

A heuristic function was used for evaluation of every schedule (here modelled as a state) that was generated during the process. 

The [simulated annealing algorithm](https://en.wikipedia.org/wiki/Simulated_annealing) was implemented in order to search the state space that was created. The algorithm chooses the best possible schedule by selecting neighbors of each node according to the score that was given to them by the heuristic function.

Files lessons.txt and teachers.txt consist the program's inputs.
