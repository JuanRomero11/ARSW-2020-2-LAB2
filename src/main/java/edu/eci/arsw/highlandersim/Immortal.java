package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;

public class Immortal extends Thread {

    private ImmortalUpdateReportCallback updateCallback=null;
    
    private int health;
    
    private int defaultDamageValue;

    private final List<Immortal> immortalsPopulation;

    private final String name;

    private final Random r = new Random(System.currentTimeMillis());
    
    private static boolean pause = false;
    
    boolean  retirado = false;
    
    


    public Immortal(String name, List<Immortal> immortalsPopulation, int health, int defaultDamageValue, ImmortalUpdateReportCallback ucb) {
        super(name);
        this.updateCallback=ucb;
        this.name = name;
        this.immortalsPopulation = immortalsPopulation;
        this.health = health;
        this.defaultDamageValue=defaultDamageValue;
    }

    public void run() {

        while (!ControlFrame.stop && !retirado) {
        	
        	synchronized (this) {
        	if(this.pause) {
        		try {
					this.wait();
					
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
        	}
        	}
        	
            Immortal im;

            int myIndex = immortalsPopulation.indexOf(this);

            int nextFighterIndex = r.nextInt(immortalsPopulation.size());

            //avoid self-fight
            if (nextFighterIndex == myIndex) {
                nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
            }

            im = immortalsPopulation.get(nextFighterIndex);
            
            if (this.health==0 ) {
            	this.retirado=true;
            	immortalsPopulation.remove(myIndex);
            }else {
            	
            	this.fight(im);
            	
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

	public void fight(Immortal i2) {
		
		
    	if(this.getId()<i2.getId()) {
	    	synchronized(this) {
	    		synchronized (i2) {
	    		
			        if (i2.getHealth() > 0) {
			            i2.changeHealth(i2.getHealth() - defaultDamageValue);
			            this.health += defaultDamageValue;
			            if (updateCallback!=null) { // el condicional es para pder hacer las pruebas sin inbolucrar componentes frame
			            updateCallback.processReport("Fight: " + this + " vs " + i2+"\n");}
			        } else {
			        	if (updateCallback!=null) { // el condicional es para pder hacer las pruebas sin inbolucrar componentes frame
			            updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");}
			        }
	    		}
	    	}
    	}
    	if(this.getId()>i2.getId()) {
	    	synchronized(i2) {
	    		synchronized (this) {
	    		
			        if (i2.getHealth() > 0) {
			            i2.changeHealth(i2.getHealth() - defaultDamageValue);
			            this.health += defaultDamageValue;
			            if (updateCallback!=null) { // el condicional es para pder hacer las pruebas sin inbolucrar componentes frame
			            updateCallback.processReport("Fight: " + this + " vs " + i2+"\n");}
			        } else {
			        	if (updateCallback!=null) { // el condicional es para pder hacer las pruebas sin inbolucrar componentes frame
			            updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");}
			        }
	    		}
	    	}
    	}
    }

    public void changeHealth(int v) {
        health = v;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {

        return name + "[" + health + "]";
    }

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public synchronized void setRenaudar(boolean b) {
		this.notifyAll();
		this.pause=false;
	}

    
}
