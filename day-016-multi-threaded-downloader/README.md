# Day 16 — Multi-Threaded File Downloader

A console program that simulates downloading several files at the
same time. Each file is downloaded by its own thread, so the
downloads run concurrently instead of one after another. A shared
counter tracks the total megabytes downloaded across all threads,
and that counter is updated through a `synchronized` method so two
threads never corrupt it by writing at the same moment. The main
thread waits for every download to finish with `join()` and then
prints a summary.

## Concepts used

- **Runnable interface** — `FileDownloader implements Runnable`;
  the download logic lives in `run()`, and each task is handed to
  its own `Thread`
- **Thread class** — `new Thread(task, "Downloader-" + (i + 1))`,
  `thread.start()` begins concurrent execution, and
  `Thread.currentThread().getName()` identifies which thread is
  printing each progress line
- **Thread.sleep()** — pauses the current thread for 120 ms per
  2 MB chunk to simulate real download time; the resulting
  `InterruptedException` is caught and the interrupt flag restored
- **join()** — the main thread calls `thread.join()` for every
  download thread, so the summary prints only after all downloads
  are done
- **isAlive()** — counts how many download threads are still
  running while the downloads are in progress, and confirms at the
  end that none are left alive
- **Thread priorities** — the first file's thread is set to
  `Thread.MAX_PRIORITY`, the rest stay at `Thread.NORM_PRIORITY`
- **synchronized methods** — `DownloadCounter.addChunk(...)` and
  `markFileCompleted()` are `synchronized`, so the shared totals
  stay correct even when threads update them at the same time
- **ArrayList** — file names, sizes and the `Thread` objects are
  kept in `ArrayList`s
- **Input validation** — file count (1–5), file size (1–20 MB) and
  non-empty file names are checked and asked again when wrong

## How to run

```bash
javac MultiThreadedDownloader.java
java MultiThreadedDownloader
```

## Sample run

Downloaded three files at the same time — `lecture-notes.pdf`
(6 MB), `setup-video.mp4` (8 MB) and `photo-archive.zip` (4 MB).
The progress lines interleave because the three threads run
concurrently, and the summary confirms all 18 MB finished.
(The exact order of the progress lines and the time taken can
vary from run to run — that is normal for threads.)

```
=== Multi-Threaded File Downloader ===
Each file downloads on its own thread, all at the same time.
How many files do you want to download? (1-5): Enter name for file 1: Enter size in MB for lecture-notes.pdf (1-20): Enter name for file 2: Enter size in MB for setup-video.mp4 (1-20): Enter name for file 3: Enter size in MB for photo-archive.zip (1-20):
Starting 3 download thread(s)...
Downloads running in background: 3 thread(s) alive
[Downloader-1] Started downloading lecture-notes.pdf (6 MB)
[Downloader-3] Started downloading photo-archive.zip (4 MB)
[Downloader-2] Started downloading setup-video.mp4 (8 MB)
[Downloader-2] setup-video.mp4: 2/8 MB (25%)
[Downloader-1] lecture-notes.pdf: 2/6 MB (33%)
[Downloader-3] photo-archive.zip: 2/4 MB (50%)
[Downloader-2] setup-video.mp4: 4/8 MB (50%)
[Downloader-1] lecture-notes.pdf: 4/6 MB (66%)
[Downloader-3] photo-archive.zip: 4/4 MB (100%)
[Downloader-3] Finished downloading photo-archive.zip
[Downloader-2] setup-video.mp4: 6/8 MB (75%)
[Downloader-1] lecture-notes.pdf: 6/6 MB (100%)
[Downloader-1] Finished downloading lecture-notes.pdf
[Downloader-2] setup-video.mp4: 8/8 MB (100%)
[Downloader-2] Finished downloading setup-video.mp4

=== Download Summary ===
Files completed: 3/3
Total data downloaded: 18 MB
Time taken: 537 ms
All download threads finished: true
```
