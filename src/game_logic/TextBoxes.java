package src.game_logic;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TextBoxes {

	Image box;
	
	public void render(GameContainer container, Graphics g) throws SlickException { 
        int x = 200;
        int y = 150;
        
        int pad = 5;
        g.setColor(Color.red);
        g.fillRect(x-pad, y-pad, 400, 200);
        
        g.setColor(Color.white);
        /*int lineHeight = font.getLineHeight();
        
        //only render the rows we have typed out so far (renderRow = current row)
        for (int i=0; i<renderRow+1; i++) {
            String line = lines.get(i);
            //render whole line if it's a previous one, otherwise render the col
            int len = i<renderRow ? line.length() : renderCol;
            String t = line.substring(0, len);
            if (t.length()!=0) {
                g.drawString(t, x, y);
            }
            y += lineHeight;
        }*/
        
        g.drawString("SPACE to restart, ENTER to show all", 10, 40);
    } 
}



/**
 * This method gets all the country names in the file.
 * 
 * @return country names.
 
public String[] getCountryNames() {
	int counter = 0;
	skipLines(3);
	while (scanner.hasNext()) {
		String data = scanner.nextLine();
		String[] token = data.split(",");
		countryNames[counter] = token[0];
		counter++;
	}
	scanner.close();
	return countryNames;
}*/