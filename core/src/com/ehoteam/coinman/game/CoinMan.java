package com.ehoteam.coinman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CoinMan extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] man;
	int manState;
	int pause = 0;
	float gravity = 0.2f;
	float velocity = 0;
	int manY = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		man = new Texture[4];
		man[0] = new Texture("frame-1.png");
		man[1] = new Texture("frame-2.png");
		man[2] = new Texture("frame-3.png");
		man[3] = new Texture("frame-4.png");

		manY = Gdx.graphics.getHeight() / 2;
	}

	@Override
	public void render () {
		batch.begin();

		// set background
		batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// if user taught the screen
		if (Gdx.input.justTouched()) {
			velocity = -10;
		}

		// take some pause
		if (pause < 8) {
			pause++;
		} else {
			pause = 0;

			// take a man frame
			if (manState < 3) {
				manState++;
			} else {
				manState = 0;
			}
		}

		// falling down
		velocity += gravity;
		manY -= velocity;

		// take him to the ground
		if (manY <= 0) {
			manY = 0;
		}

		// draw the man
		batch.draw(man[manState], Gdx.graphics.getWidth() / 2 - man[manState].getWidth() / 2, manY);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
