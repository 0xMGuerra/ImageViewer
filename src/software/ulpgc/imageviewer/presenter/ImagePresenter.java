package software.ulpgc.imageviewer.presenter;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;
import software.ulpgc.imageviewer.view.ImageDisplay.*;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on((Shift) this::shift);
        this.display.on((Released) this::released);
    }

    private void shift(int offset) {
        display.clear();
        display.paint(image, offset);
        if (offset > 0)
            display.paint(image.prev(), offset - display.getWidth());
        else
            display.paint(image.next(), display.getWidth() + offset);

    }

    private void released(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2)
            image = offset > 0 ? image.prev() : image.next();
        repaint();
    }

    public void show(Image image) {
        this.image = image;
        repaint();
    }

    private void repaint() {
        this.display.clear();
        this.display.paint(image, 0);
    }
}
