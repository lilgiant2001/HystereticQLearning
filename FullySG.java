
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.util.Arrays;

public class FullySG {
    private int[][] climbing_reward1;
    private int[][] climbing_reward2;

    public FullySG() {
        this.climbing_reward1 = new int[][] { { 10, 5, 8 }, { 5, 14, 12 }, { 8, 12, 10 } };
        this.climbing_reward2 = new int[][] { { 12, -65, -8 }, { -65, 0, 0 }, { -8, 0, 0 } };
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

        FullySG climbing_game = new FullySG();
        // agent1.initializeQ
        Agent agent1 = new Agent();
        agent1.initializeQ(3);

        // agent2.initializeQ
        Agent agent2 = new Agent();
        agent2.initializeQ(3);
        int numOfOpt = 0;

        for (int j = 0; j < 500; j++) {

            climbing_game = new FullySG();
            // agent1.initializeQ
            agent1 = new Agent();
            agent1.initializeQ(3);

            // agent2.initializeQ
            agent2 = new Agent();
            agent2.initializeQ(3);

            for (int i = 0; i < 3000; i++) {
                // agent1.chooseAction()
                int action1 = agent1.chooseAction(0.1);

                // agent2.chooseAction();
                int action2 = agent2.chooseAction(0.1);

                Random rand = new Random();
                double pos = rand.nextDouble();

                int reward;

                if (pos < 0.5) {
                    reward = climbing_game.climbing_reward1[action1][action2];
                } else {
                    reward = climbing_game.climbing_reward2[action1][action2];
                }

                agent1.updateQ(reward, action1);
                agent2.updateQ(reward, action2);

                if (i == 2999) {
                    action1 = agent1.chooseGreedy();
                    action2 = agent2.chooseGreedy();
                    if (pos < 0.5) {
                        reward = climbing_game.climbing_reward1[action1][action2];
                    } else {
                        reward = climbing_game.climbing_reward2[action1][action2];
                    }
                    if (reward == 10 || reward == 12) {
                        numOfOpt++;
                    }
                }
            }
        }

        System.out.println(numOfOpt);

        System.out.println();
        for (double q : agent1.Q) {
            System.out.print(q + " ");
        }

        for (double q : agent2.Q) {
            System.out.print(q + " ");
        }

    }

}
