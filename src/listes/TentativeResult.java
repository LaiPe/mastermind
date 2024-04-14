package listes;

import entities.Result;

import java.util.ArrayList;
import java.util.List;

public class TentativeResult {
    List<Result> tentativeResult;

    TentativeResult(int tailleMax){
        tentativeResult = new ArrayList<>(tailleMax);
    }

    public void addResult(Result r){
        tentativeResult.add(r);
    }

}
