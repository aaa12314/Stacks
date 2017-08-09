package cn.net.stacks.Tool.VideoProcessing;

/**
 * 获取视频第一帧
 */
public class VideoFirstThumbTaker extends VideoThumbTaker {

    public VideoFirstThumbTaker(String ffmpegApp) {
        super(ffmpegApp);
    }

    public void getThumb(String videoFilename, String thumbFilename, int width, int height) {

        try {
            super.getThumb(videoFilename, thumbFilename, width, height, 0, 0, 1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {

        VideoFirstThumbTaker VFTT = new VideoFirstThumbTaker("C:\\Users\\Administrator\\Desktop\\Demo\\ffmpeg\\bin\\ffmpeg.exe");

        VFTT.getThumb("C:\\Users\\Administrator\\Desktop\\Demo\\1.mp4","C:\\Users\\Administrator\\Desktop\\Demo\\1.png",320,568);

    }

}
