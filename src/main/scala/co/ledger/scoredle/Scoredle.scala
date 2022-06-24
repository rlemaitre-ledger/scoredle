package co.ledger.scoredle

case class Scoredle(solution: Word):
  import LetterStatus.*
  private val parsedSolution: List[LetterWithPosition] = solution.zipWithIndex.toList

  def test(proposal: Word): List[LetterStatus] =
    val parsedProposal: List[LetterWithPosition] = proposal.zipWithIndex.toList
    val correctResults: List[LetterResult] = parsedProposal
      .filter((c, index) => c == solution.charAt(index))
      .map((c, index) => LetterResult(c, index, Correct))
      .toList
    val lettersNotFoundInSolution: List[LetterWithPosition] = parsedSolution.filter((c, index) =>
      !correctResults.exists(res => res.letter == c && res.originalIndex == index)
    )
    val lettersNotFoundInProposal: List[LetterWithPosition] = parsedProposal.filter((c, index) =>
      !correctResults.exists(res => res.letter == c && res.originalIndex == index)
    )
    val misplacedResults: List[LetterResult] = lettersNotFoundInProposal
      .foldLeft((lettersNotFoundInSolution, List.empty[LetterResult])) {
        case (
              (sol: List[LetterWithPosition], acc: List[LetterResult]),
              (c: Letter, index: Position)
            ) =>
          val found: Option[LetterWithPosition] = sol.filter(_._1 == c).headOption
          found match {
            case None => (sol, acc)
            case Some(l) =>
              val newResult: List[LetterResult] = acc.appended(LetterResult(c, index, Misplaced))
              val remainingSolution: List[LetterWithPosition] = sol.filterNot(_ == l)
              (remainingSolution, newResult)
          }
      }
      ._2
    val wrongResults: List[LetterResult] = parsedProposal
      .filter((c, index) =>
        !(correctResults ++ misplacedResults).exists(res =>
          res.letter == c && res.originalIndex == index
        )
      )
      .map((c, index) => LetterResult(c, index, Wrong))
    (correctResults ++ misplacedResults ++ wrongResults).sortBy(_.originalIndex).map(_.status)

case class LetterResult(letter: Letter, originalIndex: Int, status: LetterStatus)


