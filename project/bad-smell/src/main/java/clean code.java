import java.util.*;

public class WordFrequencyGame {
    public String getResult(String inputStr) {
        try {
            String[] arr = inputStr.split("\\s+");

            if (arr.length == 1) {
                return inputStr + " 1";
            }

            List<Input> inputList = new ArrayList<>();
            for (String s : arr) {
                inputList.add(new Input(s, 1));
            }

            Map<String, List<Input>> map = getListMap(inputList);

            StringJoiner joiner = new StringJoiner("\n");
            map.entrySet().stream()
                    .map(entry -> new Input(entry.getKey(), entry.getValue().size()))
                    .sorted(Comparator.comparingInt(Input::getWordCount).reversed())
                    .forEach(w -> joiner.add(w.getValue() + " " + w.getWordCount()));

            return joiner.toString();
        } catch (NullPointerException | NumberFormatException e) {
            return "Calculate Error";
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
            map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
        }
        return map;
    }
}
