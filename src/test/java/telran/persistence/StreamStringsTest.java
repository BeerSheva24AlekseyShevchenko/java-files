package telran.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import org.junit.jupiter.api.*;

public class StreamStringsTest {
    private static final String PRINT_STREAM_FILE = "printStreamFile.txt";
    private static final String PRINT_WRITER_FILE = "printWriterFile.txt";
    protected static final int TAB_FACTOR = 3;

    @Test
    @Disabled
    void printStreamTest() throws Exception {
        PrintStream printStream = new PrintStream(PRINT_STREAM_FILE);
        printStream.println("Hello");
        printStream.close();
    }

    @Test
    @Disabled
    void printWriterTest() throws Exception {
        PrintWriter printWriter = new PrintWriter(PRINT_WRITER_FILE);
        printWriter.println("Hello");
        printWriter.close();
    }

    @Test
    @Disabled
    void bufferedReaderTest() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(PRINT_WRITER_FILE));
        assertEquals("Hello", reader.readLine());
        reader.close();
    }

    @Test
    void printingDirectory() throws Exception {
        printContentDirectory("./target", 4);
    }

    private void printContentDirectory(String path, int depth) throws Exception {
        Path rootPath = Paths.get(path);
        int rootNameCount = rootPath.getNameCount();

        Files.walkFileTree(
            rootPath,
            EnumSet.noneOf(FileVisitOption.class),
            depth,
            new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    printEntity(file.getFileName().toString(), file.getNameCount() - rootNameCount);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    printEntity(dir.getFileName().toString(), dir.getNameCount() - rootNameCount);
                    return FileVisitResult.CONTINUE;
                }

                private void printEntity(String value, int lvl) {
                    System.out.printf("%s%s\n", " ".repeat(TAB_FACTOR * lvl), value);
                }
            }
        );
    }
}
