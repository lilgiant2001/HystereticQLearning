import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.util.Arrays;

public class PenaltyGame {

    private int[][] penalty_reward;
    private int k;

    public PenaltyGame(int k) {
        // this.climbing_reward= new int [3][3];
        // not sure what k should be
        // k = 0;
        this.penalty_reward = new int[][] { { 10, 0, k }, { 0, 2, 0 }, { k, 0, 10 } };
        this.k = k;
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

        PenaltyGame penalty_game_5 = new PenaltyGame(0);
        PenaltyGame penalty_game_10 = new PenaltyGame(-100);
        PenaltyGame penalty_game_15 = new PenaltyGame(15);
        // agent1.initializeQ
        Agent agent1 = new Agent();
        agent1.initializeQ(3);

        // agent2.initializeQ
        Agent agent2 = new Agent();
        agent2.initializeQ(3);

        // agent1.initializeQ
        Agent agent3 = new Agent();
        agent3.initializeQ(3);

        // agent2.initializeQ
        Agent agent4 = new Agent();
        agent4.initializeQ(3);

        // agent1.initializeQ
        Agent agent5 = new Agent();
        agent5.initializeQ(3);

        // agent2.initializeQ
        Agent agent6 = new Agent();
        agent6.initializeQ(3);

        int numOfOpt1 = 0;
        int numOfOpt2 = 0;
        for (int j = 0; j < 500; j++) {
            agent1.clearQtable();
            agent2.clearQtable();
            agent3.clearQtable();
            agent4.clearQtable();
            for (int i = 0; i < 3000; i++) {
                // agent1.chooseAction()
                int action1 = agent1.chooseAction(0.1);

                // agent2.chooseAction();
                int action2 = agent2.chooseAction(0.1);

                // agent1.chooseAction()
                int action3 = agent3.chooseAction(0.1);

                // agent2.chooseAction();
                int action4 = agent4.chooseAction(0.1);

                // agent1.chooseAction()
                int action5 = agent5.chooseAction(0.25);

                // agent2.chooseAction();
                int action6 = agent6.chooseAction(0.25);

                int reward_5 = penalty_game_5.penalty_reward[action1][action2];
                int reward_10 = penalty_game_10.penalty_reward[action3][action4];
                int reward_15 = penalty_game_15.penalty_reward[action5][action6];

                agent1.updateQ(reward_5, action1);
                agent2.updateQ(reward_5, action2);
                agent3.updateQ(reward_10, action3);
                agent4.updateQ(reward_10, action4);
                agent5.updateQ(reward_15, action5);
                agent6.updateQ(reward_15, action6);

                if (i == 2999) {
                    action1 = agent1.chooseGreedy();
                    action2 = agent2.chooseGreedy();

                    action3 = agent3.chooseGreedy();
                    action4 = agent4.chooseGreedy();
                    reward_5 = penalty_game_5.penalty_reward[action1][action2];
                    reward_10 = penalty_game_10.penalty_reward[action3][action4];

                    if (reward_5 == 10) {
                        numOfOpt1++;
                    }

                    if (reward_10 == 10) {
                        numOfOpt2++;
                    }
                }
            }
        }
        System.out.println(numOfOpt1 + " " + numOfOpt2);

        // System.out.println("k = 5: \nagent A action q values: ");
        // for (double q : agent1.Q) {
        // System.out.print(q + " ");
        // }

        // System.out.println("\nagent B action q values: ");
        // for (double q : agent2.Q) {
        // System.out.print(q + " ");
        // }

        // System.out.println("\nk = 10:\n agent A action q values: ");
        // for (double q : agent3.Q) {
        // System.out.print(q + " ");
        // }

        // System.out.println("\nagent B action q values: ");
        // for (double q : agent4.Q) {
        // System.out.print(q + " ");
        // }
    }

}
