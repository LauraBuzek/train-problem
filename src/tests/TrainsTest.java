package tests;

import com.graph.TownGraph;
import com.run.TownGraphGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        String result = townGraph.calculateDistanceBetweenElements(new String[]{"A","B","C"});
        Assertions.assertEquals("9", result);

        result = townGraph.calculateDistanceBetweenElements(new String[]{"A","D"});
        Assertions.assertEquals("5", result);

        result = townGraph.calculateDistanceBetweenElements(new String[]{"A", "D", "C"});
        Assertions.assertEquals("13", result);

        result = townGraph.calculateDistanceBetweenElements(new String[]{"A", "E", "B", "C", "D"});
        Assertions.assertEquals("22", result);

        result = townGraph.calculateDistanceBetweenElements(new String[]{"A", "E", "D"});
        Assertions.assertEquals("NO SUCH ROUTE", result);

    }

    @Test
    void calcNumTripsTest() {
        //less than or equal to 3 stops
        int result = townGraph.calculateNumTripsByNumStops(false, "C", "C", 3);
        Assertions.assertEquals(2, result);

        // equal to 4 stops
        result = townGraph.calculateNumTripsByNumStops(true,"A", "C", 4);
        Assertions.assertEquals(3, result);

        result = townGraph.calculateNumTripsByNumStops(false,"C", "C", 6);
        Assertions.assertEquals(10, result);

        result = townGraph.calculateNumTripsByNumStops(false,"C", "C", 7);
        Assertions.assertEquals(15, result);

        result = townGraph.calculateNumTripsByNumStops(false,"C", "C", 10);
        Assertions.assertEquals(51, result);


    }

    @Test
    void getShortestTripTest() {
        int result = townGraph.getShortestTrip("A", "C");
        Assertions.assertEquals(9, result);

        result = townGraph.getShortestTrip("B", "B");
        Assertions.assertEquals(9, result);

        result = townGraph.getShortestTrip("A", "B");
        Assertions.assertEquals(5, result);

        result = townGraph.getShortestTrip("D", "C");
        Assertions.assertEquals(8, result);

        result = townGraph.getShortestTrip("C", "C");
        Assertions.assertEquals(9, result);
    }


}