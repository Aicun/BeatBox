package lac.com.beatbox;

/**
 * Created by Aicun on 9/3/2017.
 */

public class Sound {

    private String mAssetPath;
    private String mName;
    private Integer mSoundId;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = mAssetPath.split("/");
        String fileName = components[components.length-1];
        mName = fileName.replace(".wav","");
    }

    public String getmAssetPath() {
        return mAssetPath;
    }

    public String getmName() {
        return mName;
    }

    public Integer getmSoundId() {
        return mSoundId;
    }

    public void setmSoundId(Integer mSoundId) {
        this.mSoundId = mSoundId;
    }
}
