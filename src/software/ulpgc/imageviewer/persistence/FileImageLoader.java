package software.ulpgc.imageviewer.persistence;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.presenter.ImagePresenter;

import java.io.*;

public class FileImageLoader implements ImageLoader {
    private final File[] files;
    private InputStream inputStream;
    private ImagePresenter imagePresenter;

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(imagePath());
    }

    private FileFilter imagePath() {
        return (File pathname) -> pathname.getName().endsWith(".jpg");
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String id() {
                return files[i].getName();
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % files.length);
            }

            @Override
            public Image prev() {
                return imageAt(i > 0 ? i -1 : files.length-1);
            }

            @Override
            public void close() throws IOException {
                if(inputStream != null) {
                    inputStream.close();
                }
            }

            @Override
            public InputStream stream() {
                try {
                    return new BufferedInputStream(new FileInputStream(files != null ? files[i] : null));
                } catch (FileNotFoundException e) {
                    return null;
                }
            }
        };
    }
}
