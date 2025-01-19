import group.stringbuilder_task.CustomStringBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomstringbuilderTest {
    private CustomStringBuilder csb = new CustomStringBuilder();


    @Test
    public void testCustomStringBuilderMethods() {
        csb.append("Word-1");
        csb.append("Word-2");

        assertEquals("Word-1Word-2", csb.toString());

        csb.undo();
        assertEquals("Word-1", csb.toString());

        csb.undo();
        assertEquals("", csb.toString());


    }
}
