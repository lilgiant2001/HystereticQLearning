public class BoutilierGame {
    private int[] bt_reward;
    private int[][] bt_state;

    public BoutilierGame(int k) {
        // mapping between state and reward, {State1, state2, state3.....}
        this.bt_reward = new int[] { 0, 0, 0, 11, k, 7 };

        // mapping between action and state to determine the next state
        this.bt_state = new int[][] {
                // aa,ab,bb,ba
                { 1, 1, 2, 2 }, // s1
                { 3, 4, 3, 4 }, // s2
                { 5, 5, 5, 5 }, // s3
                { 3, 3, 3, 3 }, // s4
                { 4, 4, 4, 4 }, // s5
                { 5, 5, 5, 5 }, // s6
        };
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

        BoutilierGame bt_game = new BoutilierGame(-100);
        // agent1.initializeQ
        AgentBG agent1 = new AgentBG();
        agent1.initializeQ(6, 2);

        // agent2.initializeQ
        AgentBG agent2 = new AgentBG();
        agent2.initializeQ(6, 2);

        int numOfFour = 0;
        int numOfSix = 0;
        for (int j = 0; j < 100; j++) {
            bt_game = new BoutilierGame(-100);
            // agent1.initializeQ
            agent1 = new AgentBG();
            agent1.initializeQ(6, 2);

            // agent2.initializeQ
            agent2 = new AgentBG();
            agent2.initializeQ(6, 2);
            int currstate = 0;

            for (int i = 0; i < 10000; i++) {

                while (currstate != 3 && currstate != 4 && currstate != 5) {
                    // agent1.chooseAction()
                    int action1 = agent1.chooseAction(currstate);

                    // agent2.chooseAction();
                    int action2 = agent2.chooseAction(currstate);

                    // determine the next state
                    // 0->aa, 1->ab, 2->bb, 3-ba
                    int action_key;
                    if (action1 == 0 && action2 == 0) {
                        action_key = 0;
                    } else if (action1 == 0 && action2 == 1) {
                        action_key = 1;
                    } else if (action1 == 1 && action2 == 1) {
                        action_key = 2;
                    } else {
                        action_key = 3;
                    }
                    int nextstate = bt_game.bt_state[currstate][action_key];
                    int reward = bt_game.bt_reward[nextstate];

                    agent1.updateQ(reward, action1, currstate, nextstate);
                    agent2.updateQ(reward, action2, currstate, nextstate);
                    currstate = nextstate;
                }
                if (i == 9998) {
                    agent1.epsilon = 0;
                    agent2.epsilon = 0;
                }
                if (i == 9999) {
                    System.out.print(currstate + 1 + " ");
                    if (currstate + 1 == 4) {
                        numOfFour++;
                    }
                    if (currstate + 1 == 6) {
                        numOfSix++;
                    }
                }
                currstate = 0;
            }
        }
        System.out.println();
        System.out.println("number of trials that end with state 4: " + numOfFour);
        System.out.println("number of trials that end with state 6: " + numOfSix);

        System.out.println("Q table for agent 1");
        for (int i = 0; i < 6; i++) {
            System.out.print("state" + (i + 1) + ": ");
            for (int j = 0; j < 2; j++) {
                System.out.print("action" + j + ": " + agent1.Q[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Q table for agent 2");
        for (int i = 0; i < 6; i++) {
            System.out.print("state" + (i + 1) + ": ");
            for (int j = 0; j < 2; j++) {
                System.out.print("action" + j + ": " + agent2.Q[i][j] + " ");
            }
            System.out.println();
        }

    }
}
