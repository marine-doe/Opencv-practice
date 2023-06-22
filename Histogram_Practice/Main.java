import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double before = System.currentTimeMillis() * 1000;
        // OpenCV 라이브러리 로드
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 이미지 파일 경로
        String imagePath1 = "src/img/clothe1.png";
        String imagePath2 = "src/img/clothe3.png";

        // 이미지 파일 읽기
        Mat image1 = Imgcodecs.imread(imagePath1);
        Mat image2 = Imgcodecs.imread(imagePath2);

        // 이미지를 그레이스케일로 변환
        Mat grayImage1 = new Mat();
        Mat grayImage2 = new Mat();
        Imgproc.cvtColor(image1, grayImage1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(image2, grayImage2, Imgproc.COLOR_BGR2GRAY);

        // 히스토그램 비교
        double similarity = Imgproc.compareHist(
                calcHistogram(grayImage1),
                calcHistogram(grayImage2),
                Imgproc.CV_COMP_CORREL
        );

        // 유사도 출력
        System.out.println("이미지 유사도: " + similarity);
        double after = System.currentTimeMillis() * 1000;
        System.out.println(after - before);
    }

    // 이미지의 히스토그램 계산
    private static Mat calcHistogram(Mat image) {
        Mat hist = new Mat();
        List<Mat> images = new ArrayList<>();
        images.add(image);
        MatOfFloat ranges = new MatOfFloat(0f, 256f);
        MatOfInt histSize = new MatOfInt(256);
        Imgproc.calcHist(
                images,
                new MatOfInt(0),
                new Mat(),
                hist,
                histSize,
                ranges
        );
        return hist;
    }
}
