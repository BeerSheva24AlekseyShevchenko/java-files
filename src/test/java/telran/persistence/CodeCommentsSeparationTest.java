package telran.persistence;

import org.junit.jupiter.api.Test;

public class CodeCommentsSeparationTest {
    @Test
    // @Deprecated
    void separateTest() {
        CodeCommentsSeparation.main(new String[] {
            "testCodeCommentsSeparation.txt",
            "code.txt",
            "comments.txt"
        });
    }
}
