# 🛠️ ARSW-2020 2 lab2
## Juan Romero - Andres Sotelo



## Part I - Before finishing class
Thread control with wait/notify. Producer/consumer
1. Check the operation of the program and run it. While this occurs, run jVisualVM and check the CPU consumption of the corresponding process. Why is this consumption? Which is the responsible class? 

por que es este consumo?
- por que el hilo consumidor mantiene su ejecucion en momentos inecesarios

cual es la clase responsable?
- la clase comsumidor 

![alt text](https://github.com/JuanRomero11/ARSW-2020-2-LAB2/blob/master/images/punto1.PNG)


2. Make the necessary adjustments so that the solution uses the CPU more efficiently, taking into account that - for now - production is slow and consumption is fast. Verify with JVisualVM that the CPU consumption is reduced. 

![alt text](https://github.com/JuanRomero11/ARSW-2020-2-LAB2/blob/master/images/punto1.2.PNG)

3. Make the producer now produce very fast, and the consumer consumes slow. Taking into account that the producer knows a Stock limit (how many elements he should have, at most in the queue), make that limit be respected. Review the API of the collection used as a queue to see how to ensure that this limit is not exceeded. Verify that, by setting a small limit for the 'stock', there is no high CPU consumption or errors.

![alt text](https://github.com/JuanRomero11/ARSW-2020-2-LAB2/blob/master/images/punto1.3.PNG)

## Part II
Synchronization and Dead-Loc
1. Review the “highlander-simulator” program, provided in the edu.eci.arsw.highlandersim package. This is a game in which:

    - You have N immortal players.
    - Each player knows the remaining N-1 player.
    - Each player permanently attacks some other immortal. The one who first attacks subtracts M life points from his opponent, and increases his own life points by the same            amount. 
    - The game could never have a single winner. Most likely, in the end there are only two left, fighting indefinitely by removing and adding life points. 
    
2. Review the code and identify how the functionality indicated above was implemented. Given the intention of the game, an invariant should be that the sum of the life points of all players is always the same (of course, in an instant of time in which a time increase / reduction operation is not in process ). For this case, for N players, what should this value be?

      - Answer: El valor del invariante debe ser el numero de jugadores por el numero de vida de cada jugador 
      
3. Run the application and verify how the ‘pause and check’ option works. Is the invariant fulfilled?

