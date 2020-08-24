/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ConcurrentTest;

import org.junit.Before;
import org.junit.Test;

import edu.eci.arsw.highlandersim.Immortal;



import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;


public class ConcurrentTest {

    public ConcurrentTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void funcionaInvariante() throws Exception {
    	//manterner el invariante 4hilos*100health cada uno= 400
    	
    	
    	List<Immortal> il = new LinkedList<Immortal>();
    
        for (int i = 0; i < 4; i++) {
            Immortal i1 = new Immortal("im" + i, il, 100, 10,null);
            il.add(i1);
            i1.start();
        }
        
        long startTime = System.currentTimeMillis(); 
        long endTime = startTime + 1000L; 
        
        while (System.currentTimeMillis() < endTime) { 
                   
        }    
        
        il.get(0).setPause(true);
        
        int sum=0;
        for (Immortal im : il) {

			sum += im.getHealth();
		}
        assertEquals(sum, 100*4);
        
    }
    
    @Test
    public void noFuncionaInvariante() throws Exception {
    	//manterner el invariante 4hilos*100health cada uno= 400
   
    	List<Immortal> il = new LinkedList<Immortal>();
    
        for (int i = 0; i < 4; i++) {
            Immortal i1 = new Immortal("im" + i, il, 100, 10,null);
            il.add(i1);
            i1.start();
        }
        
        long startTime = System.currentTimeMillis(); 
        long endTime = startTime + 1000L; 
        
        while (System.currentTimeMillis() < endTime) { 
                   
        }    
        
        il.get(0).setPause(true);
        
        int sum=0;
        for (Immortal im : il) {

			sum += im.getHealth();
		}
        assertFalse(sum!=100*4);
        
    }
    
    @Test
    public void siPausa() throws Exception {
    	//manterner el invariante 4hilos*100health cada uno= 400
   
    	List<Immortal> il = new LinkedList<Immortal>();
    
        for (int i = 0; i < 4; i++) {
            Immortal i1 = new Immortal("im" + i, il, 100, 10,null);
            il.add(i1);
            i1.start();
        }
        
        long startTime = System.currentTimeMillis(); 
        long endTime = startTime + 1000L; 
        
        while (System.currentTimeMillis() < endTime) { 
                   
        }    
        
        il.get(0).setPause(true);
       
        assertEquals(Thread.State.WAITING, il.get(0).getState());
        
    }
    
    @Test
    public void funcionaInvarianteATravesDelTiempo() throws Exception {
    	//manterner el invariante 4hilos*100health cada uno= 400
    	
    	
    	List<Immortal> il = new LinkedList<Immortal>();
    
        for (int i = 0; i < 4; i++) {
            Immortal i1 = new Immortal("im" + i, il, 100, 10,null);
            il.add(i1);
            i1.start();
        }
        
        long startTime = System.currentTimeMillis(); 
        long endTime = startTime + 100L; //un poco de tiempo
        
        while (System.currentTimeMillis() < endTime) { 
                   
        }    
        
        //pausar para calcular la salud primera vez
        il.get(0).setPause(true);
        
        //toma de primera suma      
        int sum1=0;
        for (Immortal im : il) {
			sum1 += im.getHealth();
		}
        
       //reanudar
        for (Immortal im : il) {
			im.setRenaudar(true);
		}
        
        
        startTime = System.currentTimeMillis(); 
        endTime = startTime + 1000L; //un mucho de tiempo
        
        while (System.currentTimeMillis() < endTime) { 
            
        } 
        
        //pausar para calcular la salud segunda vez
        il.get(0).setPause(true);
        
        //toma de segunda suma      
        int sum2=0;
        for (Immortal im : il) {
			sum2 += im.getHealth();
		}
        
        
        assertEquals(sum1, sum2);
        
    }
    	
    	
    	
}
