package lac.com.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aicun on 9/3/2017.
 */

public class BeatBox {

    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAsset;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAsset = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS,AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames = new String[0];

        try {
            soundNames = mAsset.list(SOUND_FOLDER);
            Log.i(TAG, "found " + soundNames.length + " sounds");
        } catch (IOException e) {
            Log.e(TAG,"Couldn't not find sounds",e);
            e.printStackTrace();
        }

        for(String soundName : soundNames) {
            try {
                String assetPath = SOUND_FOLDER + "/" + soundName;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG,"Couldn't load sound " + soundName,e);
            }
        }
    }

    public void play(Sound sound) {
        Integer soundId = sound.getmSoundId();
        if(soundId == null) return;
        mSoundPool.play(soundId,1.0f,1.0f,1,1,1.0f);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = mAsset.openFd(sound.getmAssetPath());
        int soundId = mSoundPool.load(assetFileDescriptor,1);
        sound.setmSoundId(soundId);
    }

    public void release() {
        mSoundPool.release();
    }

}
