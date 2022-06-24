package co.ledger.scoredle

import co.ledger.scoredle.LetterStatus.*
import munit.FunSuite

class ScoredleTest extends FunSuite:

  def dictionary(word: Word) = Dictionary(List(word))

  test("Having the solution is all green".ignore) {
    val word = "abcde"
    assertEquals(
      Scoredle(word).test(word),
      List(Correct, Correct, Correct, Correct, Correct)
    )
  }

  test("All wrong".ignore) {
    val solution = "abcde"
    val proposal = "fghij"
    assertEquals(
      Scoredle(solution).test(proposal),
      List(Wrong, Wrong, Wrong, Wrong, Wrong)
    )
  }

  test("One misplaced".ignore) {
    val solution = "abcde"
    val proposal = "faghi"
    assertEquals(
      Scoredle(solution).test(proposal),
      List(Wrong, Misplaced, Wrong, Wrong, Wrong)
    )
  }

  test("Twice the same letter, only once in solution".ignore) {
    val solution = "abcde"
    val proposal = "aafgh"
    assertEquals(
      Scoredle(solution).test(proposal),
      List(Correct, Wrong, Wrong, Wrong, Wrong)
    )
  }

  test("Twice the same letter, one correct, other misplaced".ignore) {
    val solution = "abade"
    val proposal = "aafgh"
    assertEquals(
      Scoredle(solution).test(proposal),
      List(Correct, Misplaced, Wrong, Wrong, Wrong)
    )
  }

  test("Three times the same letter, one correct, other misplaced".ignore) {
    val solution = "abade"
    val proposal = "aafga"
    assertEquals(
      Scoredle(solution).test(proposal),
      List(Correct, Misplaced, Wrong, Wrong, Wrong)
    )
  }

  test("Three times the same letter, only once in solution".ignore) {
    val solution = "abcde"
    val proposal = "aaagh"
    assertEquals(
      Scoredle(solution).test(proposal),
      List(Correct, Wrong, Wrong, Wrong, Wrong)
    )
  }
