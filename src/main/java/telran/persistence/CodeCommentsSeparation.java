package telran.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeCommentsSeparation {
    private static final String COMMENTS_SYMBOL = "//";

    public static void main(String[] args) {    
        if (args == null || args.length < 3) {
            new IllegalArgumentException();
        }

        try {
            separateCodeComments(args[0], args[1], args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void separateCodeComments(String filePath, String codePath, String commentsPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        PrintWriter codeWriter = new PrintWriter(codePath);
        PrintWriter commentsWriter = new PrintWriter(commentsPath);

        reader.lines().forEach(line -> {
            if (isComment(line)) {
                commentsWriter.println(line);
            } else {
                codeWriter.println(line);
            }
        });

        codeWriter.close();
        commentsWriter.close();
        reader.close();
    }

    private static boolean isComment(String line) {
        return line.trim().startsWith(COMMENTS_SYMBOL);
    }
}
