import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.util.Arrays;

public class MatrixGame {
    private int[][] climbing_reward;
    // private int[][] penalty_reward;
    // private int k;

    public MatrixGame() {
        // this.climbing_reward= new int [3][3];
        this.climbing_reward = new int[][] { { 11, -30, 0 }, { -30, 7, 6 }, { 0, 0, 5 } };
        // not sure what k should be
        // k = 0;
        // this.penalty_reward = new int[][] { { 10, 0, k }, { 0, 2, 0 }, { k, 0, 10 }
        // };

    }

    public static void main(String[] args) {
        // This is how our main function look like

        // agent1.initializeQ

        // initialize reward table
        // agent1.chooseAction()
        // agent2.chooseAction();
        // getReward( from table based on actions)
        // update Q tables (agent 1 and 2)
        // loop step 4 - 7 until 10000 times

        MatrixGame climbing_game = new MatrixGame();
        // agent1.initializeQ
        Agent agent1 = new Agent();
        agent1.initializeQ(3);

        // agent2.initializeQ
        Agent agent2 = new Agent();
        agent2.initializeQ(3);

        int numOfOpt = 0;
        int action1 = 0;
        int action2 = 0;
        int reward = 0;

        int[] sums = new int[3000];

        for (int j = 0; j < 1; j++) {

            climbing_game = new MatrixGame();
            // agent1.initializeQ
            agent1 = new Agent();
            agent1.initializeQ(3);

            // agent2.initializeQ
            agent2 = new Agent();
            agent2.initializeQ(3);

            for (int i = 0; i < 3000; i++) {
                // agent1.chooseAction()
                // action1 = agent1.chooseAction(0.1);

                action1 = agent1.chooseActionSoftmax(i + 1);

                action2 = agent2.chooseActionSoftmax(i + 1);
                // agent2.chooseAction();
                // action2 = agent2.chooseAction(0.1);

                reward = climbing_game.climbing_reward[action1][action2];
                sums[i] += reward;

                // System.out.print(reward + ",");
                agent1.updateQ(reward, action1);
                agent2.updateQ(reward, action2);
                System.out.println(reward);
            }

            action1 = agent1.chooseGreedy();
            action2 = agent2.chooseGreedy();
            reward = climbing_game.climbing_reward[action1][action2];
            if (reward == 11) {
                numOfOpt++;
            }
        }

        for (int i = 0; i < 3000; i++) {
            sums[i] /= 500;
        }

        System.out.println("array: ");

        for (int i = 0; i < 3000; i++) {
            System.out.print(sums[i] + ",");
        }

        System.out.println();
        System.out.println("Number of Opts: " + numOfOpt);
        for (double q : agent1.Q) {
            System.out.print(q + " ");
        }

        for (double q : agent2.Q) {
            System.out.print(q + " ");
        }

    }

}
