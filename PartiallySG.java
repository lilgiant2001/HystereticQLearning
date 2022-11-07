
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.util.Arrays;

public class PartiallySG {
    private int[][] climbing_reward1;
    private int[][] climbing_reward2;

    public PartiallySG() {
        this.climbing_reward1 = new int[][] { { 11, -30, 0 }, { -30, 0, 6 }, { 0, 0, 5 } };
        this.climbing_reward2 = new int[][] { { 11, -30, 0 }, { -30, 14, 6 }, { 0, 0, 5 } };
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

        PartiallySG climbing_game = new PartiallySG();
        // agent1.initializeQ
        Agent agent1 = new Agent();
        agent1.initializeQ(3);

        // agent2.initializeQ
        Agent agent2 = new Agent();
        agent2.initializeQ(3);

        int numOfOpt = 0;

        int[] sums = new int[3000];

        for (int j = 0; j < 500; j++) {
            climbing_game = new PartiallySG();
            // agent1.initializeQ
            agent1 = new Agent();
            agent1.initializeQ(3);

            // agent2.initializeQ
            agent2 = new Agent();
            agent2.initializeQ(3);

            for (int i = 0; i < 3000; i++) {
                int action1 = agent1.chooseActionSoftmax(i + 1);
                int action2 = agent2.chooseActionSoftmax(i + 1);

                Random rand = new Random();
                double pos = rand.nextDouble();

                int reward;

                if (pos < 0.5) {
                    reward = climbing_game.climbing_reward1[action1][action2];
                } else {
                    reward = climbing_game.climbing_reward2[action1][action2];
                }
                sums[i] += reward;

                agent1.updateQ(reward, action1);
                agent2.updateQ(reward, action2);
                // System.out.print(reward + ",");

                if (i == 2999) {
                    action1 = agent1.chooseGreedy();
                    action2 = agent2.chooseGreedy();
                    if (pos < 0.5) {
                        reward = climbing_game.climbing_reward1[action1][action2];
                    } else {
                        reward = climbing_game.climbing_reward2[action1][action2];
                    }
                    if (action1 == 0 && action2 == 0) {
                        numOfOpt++;
                    }
                }

            }
        }

        for (int i = 0; i < 3000; i++) {
            sums[i] /= 500;
        }

        System.out.println("array: ");

        for (int i = 0; i < 3000; i++) {
            System.out.print(i + 1 + ",");
        }

        System.out.println();
        System.out.println("Number of Convege: " + numOfOpt);
        System.out.println();
        for (double q : agent1.Q) {
            System.out.print(q + " ");
        }

        for (double q : agent2.Q) {
            System.out.print(q + " ");
        }

    }

}
