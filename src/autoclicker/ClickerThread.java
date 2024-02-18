/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autoclicker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author ivan nikitin
 */
public class ClickerThread {
    private Thread thrd;
    private long interval=1000;
    private boolean enable=false;
    private boolean exit=false;
    private int button=InputEvent.BUTTON1_DOWN_MASK;
    public ClickerThread(JFrame parent){
        thrd=new Thread(){
            @Override
            public void run(){
                try {
                    Robot r=new Robot();
       
                    while(!exit){
                        try {
                            Thread.sleep(interval);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ClickerThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(enable){
                            if(!parent.isFocused()){
                                r.mousePress(button);
                                r.mouseRelease(button);
                            }
                        }
                    }
                } catch (AWTException ex) {
                    
                    Logger.getLogger(ClickerThread.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(1);
                }
            }
        };
        thrd.start();
                
    }
    public void setMouseButton(int btn){
        button=btn;
    }
    public void setEnability(boolean enables)
    {
        enable=enables;
    }
    public void toggleOnOff(){
        enable=!enable;
    }
    public void disable(){
        enable=false;
    }
    public void enableclick(){
        enable=true;
    }
    public void exitThread(){
        exit=true;
    }
    public void setInterval(long ms){
        if(ms<100){
            ms=100;
        }
        interval=ms;
    }
    public long getInterval(){
        return interval;
    }
}
