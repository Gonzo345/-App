package aplicacions.acj.mastermind;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoAplicacio {
	private Context pContext;
	private SoundPool sndPool;
	private float rate = 1.0f;
	private float leftVolume = 1.0f;
	private float rightVolume = 1.0f;
	
	public SoAplicacio(Context appContext){
		
		sndPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 100);
		pContext=appContext;
		
	}
	
	public int carregar(int idso){
		return sndPool.load(pContext, idso,1);
		
	}

	public void play(int idSonido)
	{
		sndPool.play(idSonido, leftVolume, rightVolume, 1, 0, rate); 	
	}
	
	// Libera memoria de todos los objetos del sndPool que ya no son requeridos.
	public void unloadAll()
	{
		sndPool.release();		
	}


}
