#!/usr/bin/perl

# generates the advanced-balance-scale.arff training examples
# could also be used to generate test data following different rules

for $i (1..5) {
   for $j (1..5) {
      for $k (1..5) {
         for $l (1..5) {
            for $m (1..5) {
               for $n (1..5) {
                   print "$i,$j,$k,$l,$m,$n,";
                   $result = ($i * $j * $k) - ($l * $m * $n);
                   if ($result > 0) {
                      print "L";
                   } elsif ($result < 0) {
                      print "R";
                   } else {
                      print "B";
                   }
                   print "\n";
               }
            }
         }
      }
   } 
}

