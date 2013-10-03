public class PrintPrimes {
  int numberOfPrimes;
  int rowLength;
  int columnsAcross;
  int ORDMAX;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int rowLength, int columnsAcross,  int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowLength  = rowLength;
    this.columnsAcross  = columnsAcross;
    this.ORDMAX = ORDMAX;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }


  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimes();
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }

 /* A do/while loop determines if a number is prime by keeping track of multiples
  * and squares of the current number. When a number is known to be prime it is added 
  * to the array listOfPrimes[]
  */
  private void calculateOddPrimes() {
      boolean currentNumPrime;
      int N;
      int MULT[] = new int[ORDMAX + 1];

      int currentNumber = 1;
      int ORD = 2;
      int SQUARE = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          currentNumber = currentNumber + 2;
          if (currentNumber == SQUARE) {
            ORD = ORD + 1;
            SQUARE = listOfPrimes[ORD] * listOfPrimes[ORD];
            MULT[ORD - 1] = currentNumber;
          }
          N = 2;
          currentNumPrime = true;
          while (N < ORD && currentNumPrime) {
            while (MULT[N] < currentNumber)
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            if (MULT[N] == currentNumber)
              currentNumPrime = false;
            N = N + 1;
          }
        } while (!currentNumPrime);
        listOfPrimes[primesFoundSoFar] = currentNumber;
      }
    }
  
/* Print the calculated prime numbers in columns 50 primes with 4 columns per page.
 *  The length of the lists are defined by the input rowsAcross and the number of columns 
 *  per page is the value of columnsAcross. 
 */
    public void printPrimes() {
        int PAGENUMBER = 1;
        int PAGEOFFSET = 1;
        while (PAGEOFFSET <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes + " Prime Numbers --- Page " + PAGENUMBER);
          System.out.println("");
          for (int ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + rowLength; ROWOFFSET++) {
            for (int columnNumber = 0; columnNumber < columnsAcross;columnNumber++)
              if (ROWOFFSET + columnNumber * rowLength <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[ROWOFFSET + columnNumber * rowLength]);
            System.out.println("");
          }
          System.out.println("\f");
          PAGENUMBER = PAGENUMBER + 1;
          PAGEOFFSET = PAGEOFFSET + rowLength * columnsAcross;
        }
    }
}

					 
