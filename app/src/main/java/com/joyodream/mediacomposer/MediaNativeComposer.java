package com.joyodream.mediacomposer;

import static android.R.attr.duration;

/**
 * Created by lipeilong on 2016/10/27.
 *
 * javah -d src\main\jni -classpath build\intermediates\classes\debug -jni com.joyodream.mediacomposer.MediaNativeComposer

 */

public class MediaNativeComposer implements IMediaComposer {

    public static native int native_composer(String mp3Path, String mp4Path, String outMp4Path);

    static {
        System.loadLibrary("MediaComposer");
    }

    @Override
    public int composer(String inMp3Path, String inMp4Path, String outMp4Path) {
        return native_composer(inMp3Path, inMp4Path, outMp4Path);
    }
}
