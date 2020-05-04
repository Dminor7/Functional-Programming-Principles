def sqr(x: Double) = {

  def isGoodEnough(guess: Double): Boolean =
    math.abs(guess * guess - x) / x < 0.001

  def improve(guess: Double): Double =
    (guess + x / guess) / 2


  @scala.annotation.tailrec
  def sqrIter(guess: Double): Double = {
    if (isGoodEnough(guess)) guess
    else sqrIter(improve(guess))
  }


  sqrIter(1.0)
}
sqr(2.0)
sqr(1.0e50)
sqr(1.0e-6)