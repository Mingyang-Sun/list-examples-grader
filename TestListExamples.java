import static org.junit.Assert.*;
import org.junit.*;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LengthChecker implements StringChecker {
  public boolean checkString(String s) {
    if (s.length() > 1) {
      return true;
    }
    return false;
  }
}

public class TestListExamples {
  // Write your grading tests here!
  @Test
  public void testFilter1() {
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

  @Test
  public void testMerge1() {
    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    list1.add("a");
    list1.add("b");
    list1.add("c");
    list2.add("d");
    list2.add("e");
    list2.add("f");
    List<String> list3 = ListExamples.merge(list1, list2);
    String[] expected = { "a", "b", "c", "d", "e", "f" };
    assertArrayEquals(expected, list3.toArray());
  }

  @Test
  public void testMerge2(){
    ArrayList<String> input1 = new ArrayList<>(Arrays.asList("a"));
    ArrayList<String> input2 = new ArrayList<>(Arrays.asList("b", "c", "d"));
    ArrayList<String> output = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
    Assert.assertEquals(output, ListExamples.merge(input1, input2));
  }
}