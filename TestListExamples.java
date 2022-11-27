import static org.junit.Assert.*;
import org.junit.*;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.List;

class LengthChecker implements StringChecker
{
  public boolean checkString(String s)
  {
    if (s.length() > 1)
    {
      return true;
    }
    return false;
  }
}

public class TestListExamples {
    // Write your grading tests here!
    @Test
    public void testFilter() {
        StringChecker sc = new LengthChecker();

        ArrayList<String> input = new ArrayList<String>();
        input.add("a");
        input.add("apple");
        input.add("b");
        input.add("banana");
        input.add("c");
        input.add("carrot");

        ArrayList<String> expect = input;
        expect.remove("a");
        expect.remove("b");
        expect.remove("c");

        assertArrayEquals(expect.toArray(), ListExamples.filter(input, sc).toArray());
    }
  }