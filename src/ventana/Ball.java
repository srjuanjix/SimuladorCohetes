/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import java.awt.Graphics2D;
import java.util.Random;

public class Ball {
    
                Random rnd = new Random();
	int x = (int) (rnd.nextDouble() * (150));
	int y =  (int) (rnd.nextDouble() * (150));
	int xa = 1;
	int ya = 1;
	private MainClass ejemplo;

	public Ball(MainClass ejemplo) {
		this.ejemplo= ejemplo;
	}

	void move() {
		if (x + xa < 0)
			xa = 1;
		if (x + xa > ejemplo.getWidth() - 30)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > ejemplo.getHeight() - 30)
			ya = -1;

		x = x + xa;
		y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, 30, 30);
	}
}
