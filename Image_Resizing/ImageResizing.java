import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageResizing {
    public static void main(String[] args) {
        // OpenCV 초기화
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 이미지 경로
        String imagePath = "src/img/myClothe3.jpg";

        // 이미지 리사이징
        Mat resizedImage = resizeImage(imagePath, 160, 160);

        // 리사이징된 이미지 출력
        displayImage(resizedImage);
    }

    public static Mat resizeImage(String imagePath, int newWidth, int newHeight) {
        // 이미지 로드
        Mat image = Imgcodecs.imread(imagePath);

        // 이미지 리사이징
        Mat resizedImage = new Mat();
        Size newSize = new Size(newWidth, newHeight);
        Imgproc.resize(image, resizedImage, newSize);

        return resizedImage;
    }

    public static void displayImage(Mat image) {
        // 이미지 출력
        Imgcodecs.imwrite("resized_image.jpg", image);
        System.out.println("리사이징된 이미지가 resized_image.jpg로 저장되었습니다.");
    }
}
