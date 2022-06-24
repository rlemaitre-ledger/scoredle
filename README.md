# Scoredle coding interview
## Description
The goal of this exercise is to implement a [Scoredle](https://scoredle.com)clone.

## Steps
### Step 1: Compute the score of a proposal
Given two five-letters words (the solution and the proposal), write a method that returns the result. The result is composed by five letter results:
- `Correct` if the proposed letter is in the solution at the same position
- `Misplaced` if the proposed letter is in the solution but at a different position
- `Wrong` if the proposed letter is not in the solution
If a letter is present multiple times in the proposal:
- if the solution does not contain the letter, all occurrences are in `Wrong` status
- else if the solution contains only once the letter
	- if one of the occurence is at a correct position, its status is `Correct`, the other occurences status are `Wrong`
	- else the first occurence status is `Misplaced`, the other are `Wrong`
- else
	- all well placed occurences have `Correct` status, other ones (with the limit of number of occurrence of the letter is solution) have `Misplaced` status and the rest have `Wrong` status

examples:
```scala
type Word = String

sealed trait LetterStatus
object Correct extends LetterStatus
object Misplaced extends LetterStatus
object Wrong extends LetterStatus

case class Scoredle(solution: Word) {
  def test(proposal: Word): List[LetterStatus]
}

assert(Scoredle("abcde").test("fghij") == List(Wrong, Wrong, Wrong, Wrong, Wrong))
assert(Scoredle("abcde").test("afghi") == List(Correct, Wrong, Wrong, Wrong, Wrong))
assert(Scoredle("abcde").test("faghi") == List(Wrong, Misplaced, Wrong, Wrong, Wrong))
assert(Scoredle("abcde").test("aafgh") == List(Correct, Wrong, Wrong, Wrong, Wrong))
assert(Scoredle("abade").test("aafgh") == List(Correct, Misplaced, Wrong, Wrong, Wrong))
assert(Scoredle("abade").test("fafaa") == List(Wrong, Misplaced, Wrong, Misplaced, Wrong))
```
### Step 2: Check the proposal is in the dictionary
A `dictionary.txt` file contains all the possible playable words, modify the `Scoredle` class to check that each proposal is in the dictionary
### Step 3: Show the number of possible solutions after each proposal
Modify the `Scoredle` class to indicate the possible solutions after each proposal
### Step 4: Limit a game to 6 proposals 
