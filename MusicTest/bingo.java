package MusicTest;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioSystem.getClip;

public class bingo {
    public static class MusicTest {
        private static Clip bgm;//背景乐
        private static Clip bingo;//音效
        private static AudioInputStream ais;

        MusicTest() {
        }

        public static void playbingo() {
            try {
                bingo= getClip();
                InputStream is = MusicTest.class.getClassLoader().getResourceAsStream("bingo.wav");
                //getclassLoader得到当前类的加载器.getResourceAsStream加载资源，只能加载wav的音乐格式
                if (is != null) {
                    ais = getAudioInputStream(is);//获取输入流
                }
              bingo.open(ais);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
           bingo.start();//开始播放
           //循环播放
        }


        public static void stop() {
            if (ais != null)
                bingo.close();
        }
    }
}
