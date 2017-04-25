package io.vavr;

import com.google.gwt.core.client.EntryPoint;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

import static io.vavr.API.For;

public class VavrEntryPoint implements EntryPoint {
    @Override
    public void onModuleLoad() {
        shouldGeneratePrimes();
        shouldCatchTry();
        shouldReturnOption();
        shouldBimapLeftEither();
        shouldIterateFor2();
    }

    private void shouldGeneratePrimes() {
        Stream<Integer> primes = Stream.of(2)
                .appendSelf(self -> Stream
                        .iterate(3, i -> i + 2)
                        .filter(i -> self.takeWhile(j -> j * j <= i)
                                .forAll(k -> i % k > 0)));
        echo("result1", primes.take(10).mkString("primes: ", ", ", ""));
    }

    private void shouldCatchTry() {
        Try<Void> t = Try.of(() -> {
            throw new RuntimeException();
        });
        echo("result2", t.toString());
    }

    private void shouldReturnOption() {
        Map<Integer, String> map = HashMap.of(1, "One", 2, "Two");
        Option<String> opt = map.get(2);
        echo("result3", opt.toString());
    }

    private void shouldBimapLeftEither() {
        Either<Integer, String> either = Either.<Integer, String>left(1)
                .bimap(i -> i + 1, s -> s + "1");
        echo("result4", either.toString());
    }

    private void shouldIterateFor2() {
        List<Integer> result = For(
                List.of(1, 2, 3),
                List.of(1, 2, 3)
        ).yield((i1, i2) -> i1 + i2).toList();
        echo("result5", result.toString());
    }

    private native void echo(String container, String str)/*-{
        $doc.getElementById(container).innerHTML = str;
    }-*/;
}
