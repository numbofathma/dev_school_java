package com.example.exercises;

import com.example.data.Transactions;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamExercisesPart2Test {

    //LEVEL1

    /**
     *  1)remove header
     *  2)parse to int
     *  3)count sum
     */
    @Test
    public void skipThenMapAndCalculateSum() throws Exception {
        Stream<String> stream = Arrays.asList("header", "7", "9", "2", "13", "5").stream();

        Integer sum = stream
//                .skip(???)
            .<Integer>map(null)
            .reduce(null, null);

        assertThat(sum).isEqualTo(36);

    }

    @Test
    /**
     * 1)remove header
     * 2)parse string
     * 3)find min and max
     * -- can you use only one stream?
     */
    public void testMinAndMaksAmount() throws Exception {
        List<String> array = Arrays.asList("header", "7", "9", "2", "13", "5");
        Stream<String> stream1 = array.stream();
        Stream<String> stream2 = array.stream();

        Optional<Integer> max = stream1.skip(1).map(Integer::parseInt).max(null);
        Optional<Integer> min = null;


        assertThat(min.orElse(0)).isEqualTo(2);
        assertThat(max.orElse(0)).isEqualTo(13);
    }

    @Test
    /**
     * 1)remove header
     * 2)parse string
     * 3) find first even number
     */
    public void findFirstEven() throws Exception {
        List<String> array = Arrays.asList("header", "7", "9", "2", "13", "5");

        //uncomment and complete
        Optional<Integer> firstEven = null;
//                array.stream()
//                .skip(???)
//                .map(???)
//                .filter(???)
//                .find???();

        assertThat(firstEven).isPresent();
        assertThat(firstEven.get()).isEqualTo(2);
    }

    @Test
    /**
     * implement "allMatch" so it checks if all amounts in transactions are greater than zero
     */
    public void areAllTransactionsPositive() throws Exception {
        boolean allGreaterThanZero = readTransactions().allMatch(null);

        assertThat(allGreaterThanZero).isTrue();
    }

    @Test
    /**
     * 1) use filter to leave only transactions with amount = 0
     * 2) use findFirst
     */
    public void tryToFindFirstTransactionWithAmountZero() throws Exception {
        Optional<Transactions.FlatTransaction> firstTransactionWithAmountZero = readTransactions()
            .filter(null)
            .findFirst();


        assertThat(firstTransactionWithAmountZero).isEmpty();
    }

    //LEVEL2

    @Test
    /**
     * 1) use readTransactions to construct stream
     * 2) find whole transaction with biggest amount
     * 3) use Comparator.comparing
     */
    public void findTransactionWithBiggestAmount() throws Exception {
        Optional<Transactions.FlatTransaction> maxTransaction = null;
//        readTransactions()
//                .max(???);   //Comparator.comparing(???)


        assertThat(maxTransaction).isPresent();
        assertThat(maxTransaction.get().id).isEqualTo(9);

    }

    @Test
    /**
     * filter stream to leave only transactions with "accountFrom" 1
     * map transaction to id
     * collect to list
     */
    public void findAllTransactionsIdsFromAccount1() throws Exception {
        List<Integer> result = readTransactions()
            .filter(null)
            .<Integer>map(null)
            .collect(Collectors.toList());

        assertThat(result).containsExactly(1,2,7,8);
    }

    @Test
    /**
     * 1) filter by account id
     * 2) filter by date
     * 3) map to amount
     * 4) sum with reduce
     */
    public void sumAllAmountsIntransactionfromAccount2on01022016() throws Exception {
        int accountId=2;
        LocalDate searchDate = LocalDate.of(2016, 2, 1);

        Integer result = readTransactions()
            .filter(t -> t.accountFrom ==   null)
            .filter(t -> t.date.isEqual(null))
            .<Integer>map(null)
            .reduce(null, null);

        assertThat(result).isEqualTo(716);

    }

    private Stream<Transactions.FlatTransaction> readTransactions() {
        return Transactions.transactions.stream()
            .skip(1)  // KATA
            .map(line -> line.split(","))
            .filter(cels->cels.length==5) // KATA
            .filter(cels->!cels[0].equals("aaa") && !cels[1].equals("bbb")) // KATA
            .filter(cels->!cels[0].isEmpty())  // KATA
            .map(Transactions.FlatTransaction::new);
    }
}
