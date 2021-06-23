package info.kgeorgiy.ja.popov.crawler;

import info.kgeorgiy.java.advanced.crawler.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;

public class WebCrawler implements Crawler {

    private final ExecutorService downloadsManager;
    private final ExecutorService extractionsManager;
    private final Downloader downloader;
    private final Map<String, Semaphore> permitsByHost;
    private final int perHost;

    private static final int MIN_ARGS_COUNT = 1;
    private static final int MAX_ARGS_COUNT = 5;

    private static final int DEFAULT_DEPTH = 1;
    private static final int DEFAULT_DOWNLOADERS = 1;
    private static final int DEFAULT_EXTRACTORS = 1;
    private static final int DEFAULT_PER_HOST = 1;

    public WebCrawler(Downloader downloader, int downloaders, int extractors, int perHost) {
        this.downloader = downloader;
        this.downloadsManager = Executors.newFixedThreadPool(downloaders);
        this.extractionsManager = Executors.newFixedThreadPool(extractors);
        this.permitsByHost = new ConcurrentHashMap<>();
        this.perHost = perHost;
    }


    @Override
    public Result download(String url, int depth) {
        var errors = new ConcurrentHashMap<String, IOException>();
        Set<String> downloaded = ConcurrentHashMap.newKeySet();
        downloaded.add(url);
        var phaser = new Phaser(1);
        download(url, depth, downloaded, errors, phaser);
        phaser.arriveAndAwaitAdvance();
        downloaded.removeAll(errors.keySet());
        return new Result(new ArrayList<>(downloaded), errors);
    }

    private void download(String url, int depth, Set<String> downloaded, Map<String, IOException> errors, Phaser phaser) {
        if (depth == 0) return;
        Runnable downloadTask = () -> {
            try {
                var host = URLUtils.getHost(url);
                permitsByHost.putIfAbsent(host, new Semaphore(perHost));
                try {
                    permitsByHost.get(host).acquire();
                    var document = downloader.download(url);
                    if (depth == 1) return;
                    Runnable extractTask = () -> {
                        try {
                            document.extractLinks().forEach(link -> {
                                if (downloaded.add(link)) {
                                    download(link, depth - 1, downloaded, errors, phaser);
                                }
                            });
                        } catch (IOException e) {
                            errors.put(url, e);
                        } finally {
                            phaser.arrive();
                        }
                    };
                    phaser.register();
                    extractionsManager.submit(extractTask);
                } catch (InterruptedException ignored) {}
                finally {
                    permitsByHost.get(host).release();
                }
            } catch (IOException e) {
                errors.put(url, e);
            } finally {
                phaser.arrive();
            }
        };
        phaser.register();
        downloadsManager.submit(downloadTask);
    }

    private static void showUsage() {
        System.out.println("Usage: WebCrawler <url> [depth] [downloads] [extractors] [perHost].");
    }

    private static void validateInput(String[] args, int[] arguments) {
        if (args.length < MIN_ARGS_COUNT || args.length > MAX_ARGS_COUNT) {
            showUsage();
            throw new IllegalArgumentException();
        }
        for (var i = 0; i < args.length; i++) {
            var arg = args[i];
            Objects.requireNonNull(arg);
            try {
                arguments[i] = Integer.parseInt(arg);
            } catch (NumberFormatException ignored) {
                throw new IllegalArgumentException("Each and every argument should be an integer value.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Objects.requireNonNull(args);
        var arguments = new int[args.length];
        try {
            validateInput(args, arguments);
        } catch (IllegalArgumentException ignored) {
            return;
        }
        var url = args[0];
        var depth = args.length > 1 ? arguments[1] : DEFAULT_DEPTH;
        var downloaders = args.length > 2 ? arguments[2] : DEFAULT_DOWNLOADERS;
        var extractors = args.length > 3 ? arguments[3] : DEFAULT_EXTRACTORS;
        var perHost = args.length > 4 ? arguments[4] : DEFAULT_PER_HOST;
        new WebCrawler(new CachingDownloader(), downloaders, extractors, perHost).download(url, depth);
    }

    @Override
    public void close() {
        close(downloadsManager);
        close(extractionsManager);
    }

    private void close(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}