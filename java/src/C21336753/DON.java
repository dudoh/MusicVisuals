package C21336753;

import ie.tudublin.Visual;

public class DON extends Visual{
    public void settings()
    {
        size(900, 900, P3D);

        println("CWD: " + System.getProperty("user.dir"));
    }

    int mode = 1;
    boolean paused = true;

    public void keyPressed()
    {
        if (key >= '0' && key <= '5')
        {
		    mode = key - '0';
	    }

        switch (key)
        {
            case ' ':
            {
                if (paused)
                {
                    getAudioPlayer().play();
                    paused = false;
                }

                else
                {
                    getAudioPlayer().pause();
                    paused = true;
                }

                break;
            }

            case '1':
            {
                getAudioPlayer().play();
                break;
            }

            case 'r':
            {
                getAudioPlayer().cue(0);
                getAudioPlayer().play();
            }
        }
    }

    public void setup()
    {
        colorMode(HSB);
        // noCursor();


        
        setFrameSize(1024);
        frameRate(60);

        startMinim();
        loadAudio("song.mp3");

        getAudioPlayer().setGain(-10);
    }

    andrew Andrew = new andrew();
    jon Jon = new jon();
    david David = new david();
    Combined Combined = new Combined();

    public void draw()
    {    
        background(255);


        switch(mode)
        {
            case 1: //Eoin SECTION 1
            {
                David.draw(this);
                break;
            }
            
            case 2: //Kieran SECTION 2
            {
                Jon.draw(this);
                break;
            }

            // case 3:
            // {
            //     Combined.render(this);
            //     break;
            // }

            // case 4: //Aman SECTION 3
            // {
            //     Andrew.draw(this);
            //     break;
            // }

           
        }
    }
}
