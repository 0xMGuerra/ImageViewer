package software.ulpgc.imageviewer.ImageViewer;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.persistance.FileImageLoader;
import software.ulpgc.imageviewer.presenter.ImagePresenter;
import software.ulpgc.imageviewer.view.MainFrame;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileImageLoader fileImageLoader = imageLoader();
        Image image = fileImageLoader.load();
        MainFrame frame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay());
        presenter.show(image);
        frame.setVisible(true);
        image.close();
    }

    private static FileImageLoader imageLoader() {
        File file = new File("src\\software\\ulpgc\\imageviewer\\images\\");
        return new FileImageLoader(file);
    }
}
