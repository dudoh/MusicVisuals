package C21336753;

import  ie.tudublin.Visual;
import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PApplet;
import processing.core.PConstants;

public class andrew extends Visual {

    float angleCube = 540;
    float anglePY = 540;
    float speed = 0.02f;
    float PYspeed = 1f;
    float smoothedAmplitude = 0;
    float angle;
    

    public void drawCube(DON e) 
    {
        e.calculateAverageAmplitude();
        e.stroke(DON.map(e.getSmoothedAmplitude(), 0, 0.6f, 0, 255), 255, 255);
        e.strokeWeight(5);
        e.fill(155);; // set no fill color
        

        e.pushMatrix();

        e.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        e.translate(0, 0, -200);
        e.rotateX(angleCube);
        e.rotateZ(angleCube);       

        float boxSize = 10 + (120 * e.getSmoothedAmplitude()); 
        e.box(boxSize);

        e.popMatrix();

        angleCube += speed * 0.7f;
    }


    public void drawBorder(float smoothedAmplitude, float colour, DON e) {
        //e.background(0);
        float border = DON.map(smoothedAmplitude, 0, 0.15f, 3, 70);
        int color = e.color(DON.map(e.getSmoothedAmplitude(), 0, 0.6f, 0, 255), 255, 255);
        e.stroke(color);
        e.strokeWeight(border/5);
        e.fill(color, 150);
        e.rect(0, 0, e.width, border); // Top
        e.rect(e.width - border, 0, border, e.height); // Right
        e.rect(0, e.height - border, e.width, border); // Bottom
        e.rect(0, 0, border, e.height); // Left
    }

    public void drawPyramid(float t, float colour, DON e, float posX, float posY) 
    {
        e.pushMatrix();
        e.stroke(255, 255, 0);
        e.fill(255);
        
        e.translate(posX, posY, -700);
        e.rotateX(anglePY);
        e.rotateY(anglePY);
        
        // Draw pyramid
        e.beginShape(PConstants.TRIANGLES);
    
        e.vertex(-t, -t, -t);
        e.vertex( t, -t, -t);
        e.vertex( 0, 0, t);
        
        e.vertex( t, -t, -t);
        e.vertex( t, t, -t);
        e.vertex( 0, 0, t);
        
        e.vertex( t, t, -t);
        e.vertex(-t, t, -t);
        e.vertex( 0, 0, t);
        
        e.vertex(-t, t, -t);
        e.vertex(-t, -t, -t);
        e.vertex( 0, 0, t);
    
        e.endShape();
        e.popMatrix();

        anglePY += PYspeed * (smoothedAmplitude * 2);
    }

    public void drawBigSpiral(DON e)
    {
       // c.background(0); 
        e.translate(e.width / 2, e.height / 2);  
        e.stroke(240);
        e.strokeWeight(3);
      
        for (int i = 0; i < 1000; i++)
        {
            float red = (float) e.map(e.sin(e.radians(angle)), -1, 1, 180, 232);
            float green = (float) e.map(e.cos(e.radians(angle)), -1, 1, 192, 120);
            float blue = (float) e.map(e.cos(e.radians(angle)), -1, 1, 203, 255);
            
            //c.fill(red, green, blue);
            
            e.noFill();
            e.scale((float) 0.95);
            e.rotate(e.radians(angle));
            float side = 800;
            float offset = side / 2;
            e.ellipse(side, 0, side, side);
        }
        angle += 0.17;
        
    }
    
    

    public void render(DON e)
    {
        e.colorMode(Visual.HSB);
        e.background(0);
        smoothedAmplitude = e.getSmoothedAmplitude() / 8;

        drawBorder(smoothedAmplitude, ADD, e);

        drawCube(e);

        // Draw smaller pyramids in each corner
        float cornerSize = 50 + (smoothedAmplitude * 200);
        drawPyramid(cornerSize, 0, e, cornerSize, cornerSize);
        drawPyramid(cornerSize, 0, e, e.width - cornerSize, cornerSize);
        drawPyramid(cornerSize, 0, e, cornerSize, e.height - cornerSize);
        drawPyramid(cornerSize, 0, e, e.width - cornerSize, e.height - cornerSize);  
        
        drawBigSpiral(e);
    }

}
