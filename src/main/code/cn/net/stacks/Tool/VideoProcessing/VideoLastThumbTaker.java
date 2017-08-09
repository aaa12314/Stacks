package cn.net.stacks.Tool.VideoProcessing;

import java.io.IOException;

/**
 * 获取视频最后一帧
 */
public class VideoLastThumbTaker extends VideoThumbTaker {

    public VideoLastThumbTaker(String ffmpegApp) {
        super(ffmpegApp);
    }

    public void getThumb(String videoFilename, String thumbFilename, int width,
                         int height) throws IOException, InterruptedException {
        VideoInfo videoInfo = new VideoInfo(ffmpegApp);
        videoInfo.getInfo(videoFilename);
        super.getThumb(videoFilename, thumbFilename, width, height,
                videoInfo.getHours(), videoInfo.getMinutes(),
                videoInfo.getSeconds() - 0.2f);
    }

    public static void main(String[] args) {

        VideoFirstThumbTaker VFTT = new VideoFirstThumbTaker("C:\\Users\\Administrator\\Desktop\\Demo\\ffmpeg\\bin\\ffmpeg.exe");

        VFTT.getThumb("C:\\Users\\Administrator\\Desktop\\Demo\\2.mp4","C:\\Users\\Administrator\\Desktop\\Demo\\2.png",360,480);

    }

}
