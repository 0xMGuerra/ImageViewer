package software.ulpgc.imageviewer.model;

import java.io.IOException;
import java.io.InputStream;

public interface Image {
    String id();
    Image next();
    Image prev();
    void close() throws IOException;
    InputStream stream() throws IOException;

}
