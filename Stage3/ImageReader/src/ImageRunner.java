import imagereader.Runner;

public final class ImageRunner {
    /**
     * constructor
     */
    private ImageRunner() {
    }

    public static void main(String[] args) {
        ImageIOTanX imageIOer = new ImageIOTanX();
        ImageProcessorTanX processor = new ImageProcessorTanX();
        Runner.run(imageIOer, processor);
    }
}
