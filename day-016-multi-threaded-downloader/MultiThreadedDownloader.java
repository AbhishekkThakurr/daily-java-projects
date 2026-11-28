import java.util.ArrayList;
import java.util.Scanner;

/**
 * Day 16 — Multi-Threaded File Downloader
 *
 * A console program that simulates downloading several files at the
 * same time. Each file is downloaded by its own thread, so the
 * downloads run concurrently instead of one after another. A shared
 * counter tracks the total megabytes downloaded across all threads,
 * and that counter is updated through a synchronized method so two
 * threads never corrupt it by writing at the same moment.
 */
class DownloadCounter {
    private int totalMbDownloaded = 0;
    private int filesCompleted = 0;

    synchronized void addChunk(int mb) {
        totalMbDownloaded += mb;
    }

    synchronized void markFileCompleted() {
        filesCompleted++;
    }

    synchronized int getTotalMbDownloaded() {
        return totalMbDownloaded;
    }

    synchronized int getFilesCompleted() {
        return filesCompleted;
    }
}

class FileDownloader implements Runnable {
    private final String fileName;
    private final int sizeMb;
    private final DownloadCounter counter;

    FileDownloader(String fileName, int sizeMb, DownloadCounter counter) {
        this.fileName = fileName;
        this.sizeMb = sizeMb;
        this.counter = counter;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] Started downloading " + fileName
                + " (" + sizeMb + " MB)");

        int downloaded = 0;
        try {
            while (downloaded < sizeMb) {
                int chunk = Math.min(2, sizeMb - downloaded);
                Thread.sleep(120);
                downloaded += chunk;
                counter.addChunk(chunk);
                int percent = (downloaded * 100) / sizeMb;
                System.out.println("[" + threadName + "] " + fileName
                        + ": " + downloaded + "/" + sizeMb + " MB (" + percent + "%)");
            }
        } catch (InterruptedException e) {
            System.out.println("[" + threadName + "] Download interrupted: " + fileName);
            Thread.currentThread().interrupt();
            return;
        }

        counter.markFileCompleted();
        System.out.println("[" + threadName + "] Finished downloading " + fileName);
    }
}

public class MultiThreadedDownloader {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Multi-Threaded File Downloader ===");
        System.out.println("Each file downloads on its own thread, all at the same time.");

        int fileCount = readIntInRange("How many files do you want to download? (1-5): ", 1, 5);

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> sizes = new ArrayList<>();

        for (int i = 1; i <= fileCount; i++) {
            String name = readNonEmptyLine("Enter name for file " + i + ": ");
            int size = readIntInRange("Enter size in MB for " + name + " (1-20): ", 1, 20);
            names.add(name);
            sizes.add(size);
        }

        DownloadCounter counter = new DownloadCounter();
        ArrayList<Thread> threads = new ArrayList<>();

        System.out.println();
        System.out.println("Starting " + fileCount + " download thread(s)...");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < fileCount; i++) {
            FileDownloader task = new FileDownloader(names.get(i), sizes.get(i), counter);
            Thread thread = new Thread(task, "Downloader-" + (i + 1));
            // First file gets the highest priority, the rest stay normal.
            if (i == 0) {
                thread.setPriority(Thread.MAX_PRIORITY);
            } else {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        // Show that the threads are alive while the main thread waits.
        System.out.println("Downloads running in background: "
                + countAlive(threads) + " thread(s) alive");

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted while waiting.");
                Thread.currentThread().interrupt();
            }
        }

        long elapsedMs = System.currentTimeMillis() - startTime;

        System.out.println();
        System.out.println("=== Download Summary ===");
        System.out.println("Files completed: " + counter.getFilesCompleted() + "/" + fileCount);
        System.out.println("Total data downloaded: " + counter.getTotalMbDownloaded() + " MB");
        System.out.println("Time taken: " + elapsedMs + " ms");
        System.out.println("All download threads finished: " + (countAlive(threads) == 0));
    }

    private static int countAlive(ArrayList<Thread> threads) {
        int alive = 0;
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                alive++;
            }
        }
        return alive;
    }

    private static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid number. Try again.");
            }
        }
    }

    private static String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Name cannot be empty. Try again.");
        }
    }
}
