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

 - Answer: Implementado en codigo.

![alt text](https://github.com/JuanRomero11/ARSW-2020-2-LAB2/blob/master/images/punto1.2.PNG)

3. Make the producer now produce very fast, and the consumer consumes slow. Taking into account that the producer knows a Stock limit (how many elements he should have, at most in the queue), make that limit be respected. Review the API of the collection used as a queue to see how to ensure that this limit is not exceeded. Verify that, by setting a small limit for the 'stock', there is no high CPU consumption or errors.

 - Answer: Implementado en codigo.

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

      - Answer: Al implementar las opciones 'pause and check' sigue sin cumplirse la invariante.
      
4. A first hypothesis that the race condition for this function (pause and check) is presented is that the program consults the list whose values it will print, while other threads modify their values. To correct this, do whatever is necessary so that, before printing the current results, all other threads are paused. Additionally, implement the ‘resume’ option.

    - Answer: Implementado en codigo.
    
5. Check the operation again (click the button many times). Is the invariant fulfilled or not ?.

    - Answer: Sigue sin cumplirse el invariante
    
6. Identify possible critical regions in regards to the fight of the immortals. Implement a blocking strategy that avoids race conditions. Remember that if you need to use two or more ‘locks’ simultaneously, you can use nested synchronized blocks:

    - Answer: Implementado en codigo.
    
7. After implementing your strategy, start running your program, and pay attention to whether it comes to a halt. If so, use the jps and jstack programs to identify why the program stopped.

     - Answer: Al implementar el anterior codigo surgio un DeadLock.
     
8. Consider a strategy to correct the problem identified above (you can review Chapter 15 of Java Concurrency in Practice again).

     - Answer: implementado en codigo.
     
9. Once the problem is corrected, rectify that the program continues to function consistently when 100, 1000 or 10000 immortals are executed. If in these large cases the invariant begins to be breached again, you must analyze what was done in step 4.
        
     - Answer: se realizaron las respectivas pruebas, y no hubo fallo de alguna forma.
     
10. An annoying element for the simulation is that at a certain point in it there are few living 'immortals' making failed fights with 'immortals' already dead. It is necessary to suppress the immortal dead of the simulation as they die. 

       10.1 Analyzing the simulation operation scheme, could this create a race condition? Implement the functionality, run the simulation and see what problem arises when                 there are many 'immortals' in it. Write your conclusions about it in the file ANSWERS.txt.
     
       10.2 Correct the previous problem WITHOUT using synchronization, since making access to the shared list of immortals sequential would make simulation extremely slow.
              
               - Answer: se modifico el metodo run en donde se esta realizando la verificacion de la vida, esto quiere decir que en el momento que este la pierda se removera de                  la lista de inmortales
       
       
11. To finish, implement the STOP option.
    
      - Answer: implementado en codigo.



