package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import android.util.Log;
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.e("AndroidLauncher", "------");
		super.onPause();
		Log.e("AndroidLauncher", "after super.onPause ------");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.e("AndroidLauncher", "+++++");
		super.onResume();
		Log.e("AndroidLauncher", "after onResume +++++");
	}
	
}
