package gumi.zxc.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

import org.eclipse.swt.widgets.DateTime;

/**
 * 查阅 https://stackoverflow.com/questions/23620360/how-to-map-to-multiple-elements-with-java-8-streams
 *
 * @author xuechao
 * @since 2017/10/24 16:07
 */
class MultiDataPoint {
    DateTime timestamp;
    Map<String, Number> keyToData;

    DateTime getTimestamp() {
        return timestamp;
    }

    Map<String, Number> getData() {
        return keyToData;
    }
}

class DataSet {
    String key;
    List<DataPoint> dataPoints;

    DataSet(String key) {
        this.key = key;
    }

    DataSet(String key, List<DataPoint> dataPoints) {
        this.key = key;
        this.dataPoints = dataPoints;
    }
}

class DataPoint {
    DateTime timeStamp;
    Number data;

    DataPoint(DateTime timeStamp, Number data) {
        this.timeStamp = timeStamp;
        this.data = data;
    }
}

public class TestStrean {
    private Collection<DataSet> convertMultiDataPointToDataSet(List<MultiDataPoint> multiDataPoints) {

        Map<String, DataSet> setMap = new HashMap<>();

        multiDataPoints.forEach(pt -> {
            Map<String, Number> data = pt.getData();
            data.entrySet().forEach(e -> {
                String seriesKey = e.getKey();
                DataSet dataSet = setMap.get(seriesKey);
                if (dataSet == null) {
                    dataSet = new DataSet(seriesKey);
                    setMap.put(seriesKey, dataSet);
                }
                dataSet.dataPoints.add(new DataPoint(pt.getTimestamp(), e.getValue()));
            });
        });

        return setMap.values();
    }

    public Collection<DataSet> convert(List<MultiDataPoint> multiDataPoints) {
        Map<String, DataSet> result = new HashMap<>();
        multiDataPoints.forEach(pt ->
            pt.keyToData
                .forEach((key, value) ->
                    result.computeIfAbsent(key, k -> new DataSet(k, new ArrayList<>()))
                        .dataPoints
                        .add(new DataPoint(pt.timestamp, value))));

        return result.values();
    }

    public List<DataSet> convert2(List<MultiDataPoint> multiDataPoints) {
        return multiDataPoints.stream()
            .flatMap(mdp -> mdp.keyToData.entrySet().stream().map(e -> new Object() {
                    String key = e.getKey();
                    DataPoint dataPoint = new DataPoint(mdp.timestamp, e.getValue());
                }
                )
            )
            .collect(
                Collectors.collectingAndThen(Collectors.groupingBy(t -> t.key,
                    Collectors.mapping(t -> t.dataPoint, Collectors.toList())
                    ),
                    m -> m.entrySet()
                        .stream()
                        .map(e -> new DataSet(e.getKey(), e.getValue()))
                        .collect(Collectors.toList())
                )
            );
    }

    public static void main(String[] args) {
        List<String> views = Arrays.asList("wsbs", "xafaswzx", "b8fw", "ad");
        Map<Integer, List<String>> res = views.stream().collect(Collectors.groupingBy(String::length));
        assert res.size() > 0;

        Map<Integer, Object> res1 = views.stream().collect(
            Collectors.groupingBy(String::length, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(String::length)),
                // 为转换函数，转换最终的数据
                Optional::get
                )
            )
        );

        views.stream().collect(
            Collectors.groupingBy(String::length)
        );
        assert res1.size() > 0;

        List<Set<String>> result =
            views.stream()
                .collect(
                    Collectors.collectingAndThen(Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(word -> word, Collectors.toSet())),
                        m -> m.entrySet()
                            .stream()
                            .filter(e -> e.getValue().contains("b8fw"))
                            .map(Map.Entry::getValue)
                            .collect(Collectors.toList())
                    )
                );

        assert result.size() > 0;

    }
}
