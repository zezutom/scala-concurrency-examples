package org.zezutom.learningconcurrency.part01_primer.exercises

/**
 * Implement a permutations function, which, given a string, returns a
 * sequence of strings that are lexicographic permutations of the input string:
 *
 * def permutations(x: String): Seq[String]
 */
object Ex05Permutations extends App {

  def permutations(x: String): Seq[String] = {

    // Generates permutations recursively
    def nextPermutation(s: String): Seq[String] = {
      if (s.length == 0) Seq("")
      else for {
        // Avoids duplications by merging indices of identical characters
        i <- s.map(c => s.indexOf(c)).toSet[Int].toSeq
        y <- nextPermutation(s.take(i) + s.takeRight(s.length - i - 1))
      } yield s(i) + y
    }

    // Results are lexicographically sorted in a non-decreasing order
    nextPermutation(x.sorted)
  }

  println(permutations("CBDA"))
  println(permutations("abba"))
  println(permutations("AAAA"))
}
