package funsets

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remvoe the
   * @Ignore annotation.
   */
//  @Ignore("not ready yet")
  @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  @Test def `intersect contains the common elements between each set`: Unit = {
    new TestSets {
      val s = intersect(s2, union(s1, s2))
      assert(!contains(s, 1), "Intersect 1")
      assert(contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
    }
  }

  @Test def `difference returns a set which contains all the elements of the set s that are not in the set t.`: Unit = {
    new TestSets {
      val s = diff(s2, union(s1, s2))
      assert(!contains(s, 1), "diff 1")
      assert(contains(s, 2), "diff 2")
      assert(!contains(s, 3), "diff 3")
    }
  }


  @Test def `filter from set with element 1 the elements that are not greater than one`: Unit = {
    new TestSets {
      val s = filter(s1, x => x > 1)
      assert(!contains(s, 1), "Filter 1")
      assert(!contains(s, 2), "Filter 2")
      assert(!contains(s, 3), "Filter 3")
    }
  }
  @Test def `filter from set with element 3 the elements that are not greater than one`: Unit = {
    new TestSets {
      val s = filter(s3, x => x > 1)
      assert(!contains(s, 1), "Filter 1")
      assert(!contains(s, 2), "Filter 2")
      assert(contains(s, 3), "Filter 3")
    }
  }

  @Test def `forall positive numbers`: Unit = {
    new TestSets {
      val s = union(s1, union(s2,s3))
      assert(forall(s, x => x > 0), "forall positive")
    }
  }

  @Test def `exists at least a number greater than 1`: Unit = {
    new TestSets {
      assert(exists(union(s1,s2), x => x > 1), "exists greater 1")
    }
  }

  @Test def `map multiplying by 3`: Unit = {
    new TestSets {
      assert(contains(map(s3, x => x * 3), 9), "map multiplying by 3 set s3")
    }
  }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
