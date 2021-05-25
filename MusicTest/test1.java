package MusicTest;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

import static javax.sound.sampled.AudioSystem.*;

public class test1 {
    public static class MusicTest {
        private static Clip bgm;//背景乐
        private static Clip hit;//音效
        private static AudioInputStream ais;

        MusicTest() {
        }

        public static void playBackgound() {
            try {
                bgm = getClip();
                InputStream is = MusicTest.class.getClassLoader().getResourceAsStream("克罗地亚狂想曲.wav");
                //getclassLoader得到当前类的加载器.getResourceAsStream加载资源，只能加载wav的音乐格式
                if (is != null) {
                    ais = getAudioInputStream(is);//获取输入流
                }
                bgm.open(ais);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
            bgm.start();//开始播放
            bgm.loop(Clip.LOOP_CONTINUOUSLY);//循环播放
        }

        public static void stop() {
            if (ais != null)
                bgm.close();
        }
    }

}
