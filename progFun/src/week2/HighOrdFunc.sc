/*
def product(f: Int => Int)(a: Int, b: Int): Int =
  if(a > b) 1
  else f(a) * product(f)(a+1, b)

product(x => x * x)(3,4)


def factorial(num: Int): Int = product(x => x)(1,num)

factorial(5)

*/

// Generalise version of above two funcs

def generalise(f: Int => Int, make: (Int, Int) => Int, base: Int)(a: Int, b: Int):Int = {
  if(a > b) base
  else make(f(a), generalise(f, make, base)(a + 1, b))
}

def product(f: Int => Int)(a: Int, b: Int) = generalise(f, (x, y) => x * y, 1)(a, b)
product(x => x * x)(3,4)
def factorial(num: Int) = generalise(x => x, (x,y) => x * y, 1)(1, num)
factorial(5)