val tolerance = 0.0001

def isClosedEnough(x: Double, y: Double): Boolean ={
  math.abs((x - y) / x) / x  < tolerance
}

def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

def fixedPoint(f: Double => Double)(firstGuess: Double): Double = {
  def iterate(guess: Double): Double ={
    val next = f(guess)
    if(isClosedEnough(guess, next)) next
    else iterate(next)
  }
  iterate(firstGuess)
}

def sqrt(x: Double): Double = fixedPoint(averageDamp(y => x / y))(firstGuess = 1.0)
sqrt(64)