package info.kgeorgiy.ja.popov.walk;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class PJW {
    private long hash;
    private final File file;

    public PJW(File file, long hash) {
        this.hash = hash;
        this.file = file;
    }

    public long getHash() {
        try {
            // NOTE: fix resources (UPD: still not fixed)
            // NOTE2: do not mix old io and new io
            InputStream reader = Files.newInputStream(Path.of(this.file.getPath()));
            byte[] buffer = new byte[1024];
            int size;
            while ((size = reader.read(buffer)) >= 0) {
                PJWHash(buffer, size);
            }
            reader.close();
            return this.hash;
        } catch (InvalidPathException | IOException e) {
            System.out.println("Problem with input file while calculated hash: " + e.getMessage());
        }
        return 0L;
    }

    // NOTE2-no-minus: please, use standard java naming scheme
    private void PJWHash(byte[] arr, int size) {
        for (int i = 0; i < size; i++) {
            this.hash = (this.hash << 8) + (arr[i] & 0xffL);
            final long high = hash & 0xff00_0000_0000_0000L;
            if (high != 0) {
                hash ^= high >> 48;
                hash &= ~high;
            }
        }
    }
}
