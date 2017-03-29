package tests;

import com.graph.TownGraph;
import com.run.TownGraphGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import sun.jvm.hotspot.utilities.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Laura on 3/26/17.
 */
class TrainsTest {
    TownGraph townGraph;

    @BeforeEach
    void setUp() {
        FileReader input = null;
        BufferedReader bufferedInput = null;

        try {
            input = new FileReader("src/com/resource/input_graph.txt");
            bufferedInput = new BufferedReader(input);

            townGraph = TownGraphGenerator.createTownGraph(bufferedInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calcDistanceTest() {
        int result = townGraph.calculateDistanceBetweenElements(new String[]{"A","B","C"});
        Assertions.assertEquals(9, result);

        result = townGraph.calculateDistanceBetweenElements(new String[]{"A","D"});
        Assertions.assertEquals(5, result);

        result = townGraph.calculateDistanceBetweenElements(new String[]{"A", "D", "C"});
        Assertions.assertEquals(13, result);

        result = townGraph.calculateDistanceBetweenElements(new String[]{"A", "E", "B", "C", "D"});
        Assertions.assertEquals(22, result);

        result = townGraph.calculateDistanceBetweenElements(new String[]{"A", "E", "D"});
        Assertions.assertEquals(-1, result);

    }

    @Test
    void calcNumTripsTest() {
        //less than or equal to 3 stops
        int result = townGraph.calculateNumTripsByNumStops("C", "C", 3);
        Assertions.assertEquals(2, result);

        // equal to 4 stops
//        result = townGraph.calculateNumTripsByNumStops("A", "C", 4);
//        Assertions.assertEquals(3, result);

        result = townGraph.calculateNumTripsByNumStops("C", "C", 9);
        Assertions.assertEquals(7, result);


    }

}