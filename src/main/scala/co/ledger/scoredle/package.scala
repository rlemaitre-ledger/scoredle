package co.ledger

import scala.util.control.NoStackTrace

package object scoredle {
  type Word               = String
  type Letter             = Char
  type Position           = Int
  type LetterWithPosition = (Letter, Position)

  enum LetterStatus:
    case Correct
    case Misplaced
    case Wrong

  enum DomainError extends Throwable with NoStackTrace:
    case InvalidWord(word: Word) 

}
